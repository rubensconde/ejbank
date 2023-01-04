package com.ejbank.sessions;


import com.ejbank.entity.AccountEntity;
import com.ejbank.payload.AccountPayload;
import com.ejbank.beans.TestAccountBean;

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





    public List<AccountPayload> getAccounts(Integer id) {
        String query ="SELECT * FROM ejbank_account WHERE id = "+id;
        List<AccountEntity> accountList = em.createQuery(query).getResultList();
        List<AccountPayload> payloadList = new ArrayList<>();
        accountList.stream().forEach(a->payloadList.add(new AccountPayload(a.getId(),a.getType().getName(),a.getBalance())));
        return payloadList;
    }

//    public Integer getNbCurrentTransactions(Integer id) {
//        var transactions = em.find(TransactionEntity.class,id);
//        return transactions;
//    }
}
