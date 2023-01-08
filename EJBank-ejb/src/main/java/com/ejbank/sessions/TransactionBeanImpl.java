package com.ejbank.sessions;

import com.ejbank.beans.TransactionBean;
import com.ejbank.entity.*;
import com.ejbank.payload.ApplyPayload;
import com.ejbank.payload.TransactionPayload;
import com.ejbank.payload.PreviewPayload;
import com.ejbank.payload.ValidationPayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Stateless
@LocalBean
public class TransactionBeanImpl implements TransactionBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    /**
     * Find not applied transactions (from and to) and return the number of not applied for the user define by id . If the id correspond to an advisor, returns the total number of not applied transactions of it's attached clients. If it's a customer returns 0.
     * @param id
     * @return Integer
     */
    public Integer getNotAppliedTransactions(Integer id) {
        em.getEntityManagerFactory().getCache().evictAll();
        UserEntity user = em.find(UserEntity.class,id);
        System.out.println(user.getType());
        int nbNotAppliedTransactionsFrom =0;
        if(user.getType().equals("customer")){
            return nbNotAppliedTransactionsFrom;
        }
        else{
            AdvisorEntity advisor = (AdvisorEntity) user;
            var customers = advisor.getCustomers();

            for(var customer:customers){
                nbNotAppliedTransactionsFrom += customer.getAccounts().stream().map(a->a.getTransactionsFrom().stream().filter(t->!t.getApplied()).toList().size()).reduce(0,Integer::sum);
            }
            return nbNotAppliedTransactionsFrom;
        }
    }

    /**
     * Return infos of the consequences of the parameter transaction.
     *  -Return a payload for an error if it's not one of our account or attached account.
     *  -Return a payload with result false if the amount of transaction is too high and reach the overdraft permits for the user
     *  -Return a payload with result true if amount is correct
     * @param transaction
     * @return ValidationPayload
     */
    public PreviewPayload transactionDetails(TransactionPayload transaction) {
        var idUser = transaction.getAuthor();
        UserEntity user = em.find(UserEntity.class,idUser);

        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            List<AccountEntity> accountList = customer.getAccounts();
            var accountSourceList = accountList.stream().filter(a->a.getId().equals(transaction.getSource())).toList();
            var accountDestinationList = accountList.stream().filter(a->a.getId().equals(transaction.getDestination())).toList();

            if(accountSourceList.isEmpty()){
                return new PreviewPayload("it's not one of your account");
            }else{
                AccountEntity source =accountSourceList.get(0);
                AccountEntity destination =accountDestinationList.get(0);
                var oldBalance = source.getBalance();
                return payloadPreview(source,destination,transaction,oldBalance);
            }
        }
        else{
            AdvisorEntity advisor = (AdvisorEntity) user;
            List<CustomerEntity> customerList = advisor.getCustomers();
            AccountEntity source = customerList.stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).filter(acc -> Objects.equals(acc.getId(), transaction.getSource())).findFirst().orElse(null);
            AccountEntity destination = advisor.getCustomers().stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).filter(acc -> Objects.equals(acc.getId(), transaction.getDestination())).findFirst().orElse(null);

            if(source == null || destination == null){
                return new PreviewPayload("it's not one of your attached account");
            }
            else{
                var oldBalance = source.getBalance();
                return payloadPreview(source,destination,transaction,oldBalance);
            }
        }
    }

    /**
     * Post the status of insert transactions and insert transaction. I has different behaviour :
     * - if the user is a customer:
     *      - return error if it's not one of our account or if the preview is not correct (checked before in transactionInfos)
     *      - insert transaction with applied false if amount>1000 and applied true if amount<=1000. Returns a payload that indicate that the transaction is inserted.
     * - if the user is an advisor :
     *      - return error if it's not one of our attached account or if the preview is not correct (checked before in transactionInfos)
     *      - insert transaction with applied true and return a payload that indicates that the transaction is inserted.
     * @param transaction
     * @return ValidationPayload
     */
    @Transactional
    public ApplyPayload applyTransaction(TransactionPayload transaction){
        var idUser = transaction.getAuthor();
        UserEntity user = em.find(UserEntity.class,idUser);
        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            List<AccountEntity> accountList = customer.getAccounts();
            var accountSourceList = accountList.stream().filter(a->a.getId().equals(transaction.getSource())).toList();
            var accountDestinationList = accountList.stream().filter(a->a.getId().equals(transaction.getDestination())).toList();

            if(accountSourceList.isEmpty()){
                return new ApplyPayload("it's not one of your account");
            }else{
                AccountEntity source =accountSourceList.get(0);
                AccountEntity destination =accountDestinationList.get(0);
                var oldBalance = source.getBalance();
                if(isCorrectPreview(source,transaction,oldBalance)){
                    if(transaction.getAmount().compareTo(new BigDecimal(1000))==1){
                        TransactionEntity toInsert = new TransactionEntity(source,destination,user,transaction.getAmount(),transaction.getComment(),false, new Date());
                        em.persist(toInsert);
                        em.merge(toInsert);
                        em.merge(source);
                        em.merge(destination);
                        return new ApplyPayload(true,"transaction > 1000 need to be checked by advisor");
                    }else{
                        TransactionEntity toInsert = new TransactionEntity(source,destination,user,transaction.getAmount(),transaction.getComment(),true,new Date());
                        source.setBalance(oldBalance.subtract(transaction.getAmount()));
                        destination.setBalance(destination.getBalance().add(transaction.getAmount()));
                        em.persist(toInsert);
                        em.merge(toInsert);
                        em.merge(source);
                        em.merge(destination);
                        return new ApplyPayload(true,"transaction valid and applied");
                    }
                }
                else{
                    return new ApplyPayload(false, "transaction not valid");
                }

            }
        }
        else{
            AdvisorEntity advisor = (AdvisorEntity) user;
            List<CustomerEntity> customerList = advisor.getCustomers();
            AccountEntity source = customerList.stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).filter(acc -> Objects.equals(acc.getId(), transaction.getSource())).findFirst().orElse(null);
            AccountEntity destination = advisor.getCustomers().stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).filter(acc -> Objects.equals(acc.getId(), transaction.getDestination())).findFirst().orElse(null);
            if(source == null || destination == null){
                return new ApplyPayload("it's not one of your attached account");
            }
            else{
                var oldBalance = source.getBalance();
                if(isCorrectPreview(source,transaction,oldBalance)){

                    TransactionEntity toInsert = new TransactionEntity(source,destination,user,transaction.getAmount(),transaction.getComment(),true, new Date());
                    source.setBalance(oldBalance.subtract(transaction.getAmount()));
                    destination.setBalance(destination.getBalance().add(transaction.getAmount()));

                    em.persist(toInsert);
                    em.merge(source);
                    em.merge(destination);
                    return new ApplyPayload(true,"transaction applied by advisor");
                }
                else{
                    return new ApplyPayload(false, "transaction not valid");
                }

            }
        }


    }

    /**
     * Find user and source and destination of the transaction and check if the transaction can be applied (depends on the balance of source). If yes, the transaction is applied and return a ApplyPayload with true, else it returns with false. If user is a customer, validation is cancelled.
     * @param validation
     * @return
     */
    @Override
    public ApplyPayload validateTransaction(ValidationPayload validation) {//Made with many em.find() and not with just one because we didn't know some specific things with the JoinColumn and the complexity of our methods. So it's one clean example that we should apply for the other BeanImpl.
        UserEntity user = em.find(UserEntity.class, validation.getAuthor());
        TransactionEntity transaction = em.find(TransactionEntity.class,validation.getTransaction());
        AccountEntity source = em.find(AccountEntity.class,transaction.getAccountFrom());
        AccountEntity destination = em.find(AccountEntity.class,transaction.getAccountTo());
        if(user.getType().equals("advisor")){ //In our logic, a customer
            if(validation.getApprove()){
            var overdraft = new BigDecimal(source.getType().getOverdraft());
            var maxValue = source.getBalance().add(overdraft);
                if(maxValue.compareTo(transaction.getAmount())==-1){
                    return new ApplyPayload(false,"Echec de la transaction pour cause de solde insuffisant",null);
                }
                else{
                    transaction.setApplied(true);
                    source.setBalance(source.getBalance().subtract(transaction.getAmount()));
                    destination.setBalance(destination.getBalance().add(transaction.getAmount()));
                    em.flush(); //Optimistic Locking, avoid data-race, could be done in the other side (customer side) when we apply a correct transaction that does not imply validation by an advisor
                    return new ApplyPayload(true,"Transaction réussie",null);
                }
            }else{
                em.remove(transaction);
                return new ApplyPayload(true,"transaction has been removed",null);
            }
        }else{
            return new ApplyPayload(false,null,"Un customer ne peut pas valider de transaction");
        }
    }

    /**
     * Check if transaction's new balance respect overdraft
     * @param source
     * @param transaction
     * @param oldBalance
     * @return true if amount is lower than overdraft+balance, else false
     */
    static boolean isCorrectPreview(AccountEntity source, TransactionPayload transaction, BigDecimal oldBalance){
        var overdraft = new BigDecimal(source.getType().getOverdraft());
        var maxValue = oldBalance.add(overdraft);
        if(maxValue.compareTo(transaction.getAmount()) == -1){
            return false;
        }
        else{
            return true;
        }
    }
    static PreviewPayload payloadPreview(AccountEntity source, AccountEntity destination, TransactionPayload transaction, BigDecimal oldBalance){
        if(isCorrectPreview(source,transaction,oldBalance)){
            return new PreviewPayload(true,oldBalance.subtract(transaction.getAmount()),destination.getBalance().add(transaction.getAmount()),"transaction valid",null);
        }
        else{
            return new PreviewPayload(false,oldBalance.subtract(transaction.getAmount()),destination.getBalance().add(transaction.getAmount()), "transaction not valid",null);
        }
    }


}
