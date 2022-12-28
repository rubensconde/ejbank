package com.ejbank.sessions;


import com.ejbank.entity.AccountEntity;
import com.ejbank.entity.UserEntity;
import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.UserPayload;
import com.ejbank.test.TestAccountBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class AccountBean implements TestAccountBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;





    public List<AccountPayload> getAccounts(Integer id) {
        var account = em.find(AccountEntity.class, id);
        var accountList = new ArrayList<AccountPayload>();
        accountList.add(new AccountPayload(account.getId(),account.getType().getName(),account.getBalance()));
        return accountList;
    }

//    public Integer getNbCurrentTransactions(Integer id) {
//        var transactions = em.find(TransactionEntity.class,id);
//        return transactions;
//    }
}
