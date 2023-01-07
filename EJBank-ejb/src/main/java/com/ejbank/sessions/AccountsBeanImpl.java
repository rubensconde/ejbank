package com.ejbank.sessions;


import com.ejbank.entity.AdvisorEntity;
import com.ejbank.entity.CustomerEntity;
import com.ejbank.entity.UserEntity;
import com.ejbank.payload.AccountPayload;
import com.ejbank.beans.AccountsBean;
import com.ejbank.payload.ListAccountPayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class AccountsBeanImpl implements AccountsBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    /**
     * Find user define by userId and return a payload composed with a list of his accounts. An advisor has no accounts.
     * @param id
     * @return ListAccountPayload
     */
    public ListAccountPayload getAccounts(Integer id) {
        UserEntity user = em.find(UserEntity.class,id);
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

    /**
     * Find user define by userId and return a payload composed with a list of his attachedaccounts. A customers has no attachedaccounts.
     * @param id
     * @return ListAccountPayload
     */
    public ListAccountPayload getAttachedAccounts(Integer id) {
        UserEntity user = em.find(UserEntity.class,id);
        System.out.println(user.getType());
        if(user.getType().equals("advisor")){
            AdvisorEntity advisor = (AdvisorEntity) user;
            List<AccountPayload> payloadList = new ArrayList<>();
            advisor.getCustomers().forEach(c->{
                c.getAccounts().stream().forEach(a->{
                    int nbNotAppliedFrom =a.getTransactionsFrom().stream().filter(t-> !t.getApplied()).toList().size();
                    CustomerEntity owner = a.getCustomer();
                    String strOwner = owner.getFirstname()+" "+owner.getLastname();
                    payloadList.add(new AccountPayload(a.getId(),strOwner,a.getType().getName(),a.getBalance(),nbNotAppliedFrom));
                });
            }); //TODO add validation field
            return new ListAccountPayload(payloadList);
        }
        else{
            return new ListAccountPayload("Id correspond to a customer");
        }
    }

    /**
     * Find user define by userId and return a payload composed with a list of his accounts or attachedaccounts.
     * @param id
     * @return ListAccountPayload
     */
    public ListAccountPayload getAllAccounts(Integer id) {
        UserEntity user = em.find(UserEntity.class,id);
        if(user.getType().equals("advisor")){
            AdvisorEntity advisor = (AdvisorEntity) user;
            List<AccountPayload> payloadList = new ArrayList<>();
            advisor.getCustomers().forEach(c->{
                c.getAccounts().stream().forEach(a->{
                    CustomerEntity owner = a.getCustomer();
                    String strOwner = owner.getFirstname()+" "+owner.getLastname();
                    payloadList.add(new AccountPayload(a.getId(),strOwner,a.getType().getName(),a.getBalance()));
                });
            });
            return new ListAccountPayload(payloadList);
        }
        else{
            CustomerEntity customer = (CustomerEntity) user;
            String strOwner = user.getFirstname()+" "+user.getLastname();
            List<AccountPayload> payloadList = new ArrayList<>();
            customer.getAccounts().stream().forEach(a->payloadList.add(new AccountPayload(a.getId(),strOwner,a.getType().getName(),a.getBalance())));
            return new ListAccountPayload(payloadList);
        }
    }
}
