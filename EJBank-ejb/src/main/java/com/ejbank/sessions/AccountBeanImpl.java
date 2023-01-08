package com.ejbank.sessions;


import com.ejbank.beans.AccountBean;
import com.ejbank.entity.AccountEntity;
import com.ejbank.entity.AdvisorEntity;
import com.ejbank.entity.CustomerEntity;
import com.ejbank.entity.UserEntity;
import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.ListAccountPayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.*;

@Stateless
@LocalBean
public class AccountBeanImpl implements AccountBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    /**
     * Find the account accountId of a user define by userId. Returns :
     * - Error payload if it's not one of our account or attached accounts
     * - Returns a payload with account info composed of user firstname and lastName, advisor firstName and lastName, rate, computed interest of the year and balance of the account.
     * @param accountId
     * @param userId
     * @return
     */
    public AccountPayload getAccount(Integer accountId, Integer userId) {//Done with find
        UserEntity user = em.find(UserEntity.class,userId);
        AccountEntity accountFound = em.find(AccountEntity.class,accountId);
        String strAdvisor = null;
        String strCustomer = accountFound.getCustomer().getFirstname()+" "+accountFound.getCustomer().getLastname()+" (client)";
        //TODO createQuery get transactions from Account found since the first month of this year
        Date currentDate = Calendar.getInstance().getTime(); //reference to know the year

        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            List<AccountEntity> accountList = customer.getAccounts();
            var accountMatchedList = accountList.stream().filter(a->a.getId()==accountId).toList();
            if(accountMatchedList.isEmpty()){
                return new AccountPayload("it's not one of your account");
            }else{
                if(accountFound.getType().getName().equals("Courant")){
                    return computeInfos(customer,accountFound);

                }else{
                    AdvisorEntity myAdvisor =customer.getAdvisor();
                    strAdvisor = myAdvisor.getFirstname()+" "+myAdvisor.getLastname()+" (conseillé)";
                    //TODO replace account.getBalance() copy by interest in constructor and how to calculate interest and check if we need to redefine toString or build the string for customer and advisor
                    return new AccountPayload(strCustomer, strAdvisor,accountFound.getType().getRate(),accountFound.getBalance(),accountFound.getBalance());
                }
            }
        }
        else{
            AdvisorEntity advisor = (AdvisorEntity) user;
            AccountEntity account = advisor.getCustomers().stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).filter(a -> Objects.equals(a.getId(), accountId)).findFirst().orElse(null);
            if(account == null){
                return new AccountPayload("it's not one of your account");
            }
            else{
                if(accountFound.getType().getName().equals("Courant")){
                    return computeInfos(advisor,accountFound);

                }else{
                    strAdvisor = user.getFirstname()+" "+user.getLastname()+" (conseillé)";
                    //TODO replace account.getBalance() copy by interest in constructor and how to calculate interest and check if we need to redefine toString or build the string for customer and advisor
                    return new AccountPayload(strCustomer, strAdvisor,accountFound.getType().getRate(),accountFound.getBalance(),accountFound.getBalance());
                }
            }
        }
    }

    /**
     * Compute the payload description of an account.
     * @param user
     * @param account
     * @return AccountPayload
     */
    private static AccountPayload computeInfos(UserEntity user, AccountEntity account){
        String strCustomer = account.getCustomer().getFirstname()+" "+account.getCustomer().getLastname()+" (client)";
        String strAdvisor = null;
        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            AdvisorEntity myAdvisor =customer.getAdvisor();
            strAdvisor = myAdvisor.getFirstname()+" "+myAdvisor.getLastname()+" (conseillé)";
        }else{
            strAdvisor = user.getFirstname()+" "+user.getLastname()+" (conseillé)";
        }

        return new AccountPayload(strCustomer, strAdvisor,account.getType().getRate(),new BigDecimal(0),account.getBalance());
    }
}
