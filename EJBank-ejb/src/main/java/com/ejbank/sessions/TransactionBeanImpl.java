package com.ejbank.sessions;

import com.ejbank.beans.TransactionBean;
import com.ejbank.entity.*;
import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.ListAccountPayload;
import com.ejbank.payload.ListTransactionPayload;
import com.ejbank.payload.TransactionPayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
            List<TransactionEntity> transactions = customer.getTransactions();
            var nbNotApplied=transactions.stream().filter(t->!t.getApplied()).toList().size(); //emptyList ornull?
            System.out.println(nbNotApplied);
            return nbNotApplied;
        }
        else{
            //TODO Correct this part and check with the API that it's good, do the same changes in AccountBeanImpl for getAccount()
            AdvisorEntity advisor = (AdvisorEntity) user;
            int nbNotApplied = advisor.getCustomers().stream().mapToInt(c -> c.getTransactions().stream().filter(t -> !t.getApplied()).toList().size()).sum();
            System.out.println(nbNotApplied);
            return nbNotApplied;
        }
    }

    @Override
    public ListTransactionPayload getTransactions(Integer accountId, Integer offset, Integer userId) {
        var nbTransactionsPage = 3;

        var query = em.createQuery("SELECT t FROM TransactionEntity t WHERE t.accountFrom.id = :accountId OR t.accountTo.id = :accountId ORDER BY t.date DESC");
        query.setParameter("accountId",accountId);
        query.setFirstResult(offset);
        query.setMaxResults(nbTransactionsPage);
        List<TransactionEntity> transactions = query.getResultList();

        List<TransactionPayload> payloadList = new ArrayList<>();
        transactions.forEach(t-> {
                payloadList.add(new TransactionPayload(
                        t.getId(),
                        t.getDate().toString(),
                        t.getAccountFrom().getId(),
                        t.getAccountTo().getId(),
                        t.getAccountTo().getCustomer().getFirstname(),
                        t.getAccountFrom().getCustomer().getFirstname(),
                        t.getAmount(),
                        t.getAuthor().getFirstname()+t.getAuthor().getLastname(),
                        t.getComment(),
                        t.getApplied()));
        });

        return new ListTransactionPayload(payloadList);
    }
}
