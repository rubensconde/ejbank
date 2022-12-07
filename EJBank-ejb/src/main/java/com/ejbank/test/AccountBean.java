package com.ejbank.test;


import com.ejbank.entity.AccountEntity;
import com.ejbank.entity.TransactionEntity;
import com.ejbank.entity.UserEntity;
import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.PeoplePayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class AccountBean implements TestAccountBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;


    public PeoplePayload getUser(Integer id) {
        var user = em.find(UserEntity.class, id);
        return new PeoplePayload(user.getFirstname(),user.getLastname());
    }


    public List<AccountPayload> getAccounts(Integer id) {
        var account = em.find(AccountEntity.class, id);
        var accountList = new ArrayList<AccountPayload>();
        accountList.add(new AccountPayload(account.getId(),account.getType(),account.getBalance()));
        return accountList;
    }

    public Integer getNbCurrentTransactions(Integer id) {
        var transactions = em.find(TransactionEntity.class,id);
        return transactions.le;
    }
}
