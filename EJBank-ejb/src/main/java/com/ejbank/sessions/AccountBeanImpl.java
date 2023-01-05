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
import java.util.List;

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
            var account = accountList.stream().filter(a->a.getId()==accountId).toList().get(0);
            //TODO replace account.getBalance() copy by interest in constructor and how to calculate interest and check if we need to redefine toString or build the string for customer and advisor
            String strCustomer = customer.getFirstname()+" "+customer.getLastname()+" (client)";
            String strAdvisor = customer.getAdvisor().getFirstname()+" "+customer.getAdvisor().getLastname()+" (conseillé)";
            if(account.getType().getName().equals("Courant")){
                return new AccountPayload(strCustomer, strAdvisor,account.getType().getRate(),new BigDecimal(0),account.getBalance());

            }else{
                return new AccountPayload(strCustomer, strAdvisor,account.getType().getRate(),account.getBalance(),account.getBalance());

            }
        }
        else{
            return new AccountPayload("it's an advisor");
        }
    }
}
