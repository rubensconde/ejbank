package com.ejbank.sessions;


import com.ejbank.entity.AccountEntity;
import com.ejbank.entity.CustomerEntity;
import com.ejbank.entity.UserEntity;
import com.ejbank.payload.AccountPayload;
import com.ejbank.beans.TestAccountBean;
import com.ejbank.payload.ListAccountPayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class AccountBeanImpl implements TestAccountBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public ListAccountPayload getAccounts(Integer id) {
        UserEntity user = em.find(UserEntity.class,id);
        System.out.println(user.getType());
        if(user.getType().equals("customer")){
            CustomerEntity customer = (CustomerEntity) user;
            List<AccountPayload> payloadList = new ArrayList<>();
            customer.getAccounts().stream().forEach(a->payloadList.add(new AccountPayload(a.getId(),a.getType().getName(),a.getBalance())));
            return new ListAccountPayload(payloadList);
        }
        else{
            return new ListAccountPayload("Id correspond to an advisor");
        }
    }

//    public Integer getNbCurrentTransactions(Integer id) {
//        var transactions = em.find(TransactionEntity.class,id);
//        return transactions;
//    }
}
