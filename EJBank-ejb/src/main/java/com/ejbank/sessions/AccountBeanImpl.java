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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Stateless
@LocalBean
public class AccountBeanImpl implements AccountBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public AccountPayload getAccount(Integer accountId, Integer userId) {
        UserEntity user = em.find(UserEntity.class,userId);
        System.out.println(user.getType());
        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            List<AccountEntity> accountList = customer.getAccounts();
            var accountMatchedList = accountList.stream().filter(a->a.getId()==accountId).toList();
            if(accountMatchedList.isEmpty()){
                return new AccountPayload("it's not one of your account");
            }else{
                AccountEntity account =accountMatchedList.get(0);
                return computeInfos(customer,account);
            }
        }
        else{
            AdvisorEntity advisor = (AdvisorEntity) user;
            AccountEntity account = advisor.getCustomers().stream().map(CustomerEntity::getAccounts).flatMap(Collection::stream).filter(a -> Objects.equals(a.getId(), accountId)).findFirst().orElse(null);
            if(account == null){
                return new AccountPayload("it's not one of your account");
            }
            else{
                return computeInfos(advisor,account);
            }
        }
    }

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

        if(account.getType().getName().equals("Courant")){
            return new AccountPayload(strCustomer, strAdvisor,account.getType().getRate(),new BigDecimal(0),account.getBalance());

        }else{
            //TODO replace account.getBalance() copy by interest in constructor and how to calculate interest and check if we need to redefine toString or build the string for customer and advisor
            return new AccountPayload(strCustomer, strAdvisor,account.getType().getRate(),account.getBalance(),account.getBalance());
        }
    }
}
