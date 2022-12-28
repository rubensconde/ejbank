package com.ejbank.sessions;

import com.ejbank.entity.UserEntity;
import com.ejbank.payload.UserPayload;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserBeanImpl implements UserBean {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    public UserPayload getUser(Integer id) {
        var user = em.find(UserEntity.class, id);
        return new UserPayload(user.getFirstname(),user.getLastname());
    }
}
