package com.ejbank.sessions;

import com.ejbank.beans.TransactionBean;
import com.ejbank.entity.*;
import com.ejbank.payload.TransactionPayload;
import com.ejbank.payload.ValidationPayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Stateless
@LocalBean
public class TransactionBeanImpl implements TransactionBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public Integer getNotAppliedTransactions(Integer id) {
        UserEntity user = em.find(UserEntity.class,id);
        System.out.println(user.getType());
        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            int nbNotAppliedTransactionsTo = customer.getAccounts().stream().map(AccountEntity::getTransactionsTo).flatMap(Collection::stream).filter(t->!t.getApplied()).toList().size();
            int nbNotAppliedTransactionsFrom = customer.getAccounts().stream().map(AccountEntity::getTransactionsFrom).flatMap(Collection::stream).filter(t->!t.getApplied()).toList().size();
            return nbNotAppliedTransactionsTo+nbNotAppliedTransactionsFrom;
        }
        else{
            //TODO Correct this part and check with the API that it's good, do the same changes in AccountBeanImpl for getAccount()
            AdvisorEntity advisor = (AdvisorEntity) user;
            int nbNotAppliedTransactionsTo = advisor.getCustomers().stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).map(AccountEntity::getTransactionsTo).flatMap(Collection::stream).filter(t->!t.getApplied()).toList().size();
            int nbNotAppliedTransactionsFrom = advisor.getCustomers().stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).map(AccountEntity::getTransactionsFrom).flatMap(Collection::stream).filter(t->!t.getApplied()).toList().size();
            return nbNotAppliedTransactionsTo+nbNotAppliedTransactionsFrom;
        }
    }


    public ValidationPayload transactionDetails(TransactionPayload transaction) {
        var idUser = transaction.getAuthor();
        UserEntity user = em.find(UserEntity.class,idUser);

        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            List<AccountEntity> accountList = customer.getAccounts();
            var accountSourceList = accountList.stream().filter(a->a.getId().equals(transaction.getSource())).toList();
            var accountDestinationList = accountList.stream().filter(a->a.getId().equals(transaction.getDestination())).toList();

            if(accountSourceList.isEmpty()){
                return new ValidationPayload("it's not one of your account");
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
                return new ValidationPayload("it's not one of your attached account");
            }
            else{
                var oldBalance = source.getBalance();
                return payloadPreview(source,destination,transaction,oldBalance);
            }
        }
    }

    public ValidationPayload applyTransaction(TransactionPayload transaction){
        var idUser = transaction.getAuthor();
        UserEntity user = em.find(UserEntity.class,idUser);
        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            List<AccountEntity> accountList = customer.getAccounts();
            var accountSourceList = accountList.stream().filter(a->a.getId().equals(transaction.getSource())).toList();
            var accountDestinationList = accountList.stream().filter(a->a.getId().equals(transaction.getDestination())).toList();

            if(accountSourceList.isEmpty()){
                return new ValidationPayload("it's not one of your account");
            }else{
                AccountEntity source =accountSourceList.get(0);
                AccountEntity destination =accountDestinationList.get(0);
                var oldBalance = source.getBalance();
                if(isCorrectPreview(source,transaction,oldBalance)){
                    //TODO Apply transaction INSERT IN DATABASE
                    return new ValidationPayload(true,"transaction valid");
                }
                else{
                    return new ValidationPayload(false, "transaction not valid");
                }

            }
        }
        else{
            AdvisorEntity advisor = (AdvisorEntity) user;
            List<CustomerEntity> customerList = advisor.getCustomers();
            AccountEntity source = customerList.stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).filter(acc -> Objects.equals(acc.getId(), transaction.getSource())).findFirst().orElse(null);
            AccountEntity destination = advisor.getCustomers().stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).filter(acc -> Objects.equals(acc.getId(), transaction.getDestination())).findFirst().orElse(null);
            if(source == null || destination == null){
                return new ValidationPayload("it's not one of your attached account");
            }
            else{
                var oldBalance = source.getBalance();
                if(isCorrectPreview(source,transaction,oldBalance)){
                    return new ValidationPayload(true,"transaction valid");
                }
                else{
                    return new ValidationPayload(false, "transaction not valid");
                }

            }
        }


    }
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
    static ValidationPayload payloadPreview(AccountEntity source, AccountEntity destination, TransactionPayload transaction, BigDecimal oldBalance){
        if(isCorrectPreview(source,transaction,oldBalance)){
            return new ValidationPayload(true,oldBalance.subtract(transaction.getAmount()),destination.getBalance().add(transaction.getAmount()),"transaction valid",null);
        }
        else{
            return new ValidationPayload(false,oldBalance.subtract(transaction.getAmount()),destination.getBalance().add(transaction.getAmount()), "transaction not valid",null);
        }
    }
    static ValidationPayload payloadApply(AccountEntity source, TransactionPayload transaction, BigDecimal oldBalance){
        if(isCorrectPreview(source,transaction,oldBalance)){
            return new ValidationPayload(true,"transaction valid");
        }
        else{
            return new ValidationPayload(false, "transaction not valid");
        }
    }

}
