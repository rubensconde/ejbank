package com.ejbank.sessions;

import com.ejbank.beans.TransactionBean;
import com.ejbank.entity.AdvisorEntity;
import com.ejbank.entity.CustomerEntity;
import com.ejbank.entity.TransactionEntity;
import com.ejbank.entity.UserEntity;
import com.ejbank.payload.AccountPayload;
import com.ejbank.payload.ListAccountPayload;
import com.ejbank.payload.TransactionPayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
