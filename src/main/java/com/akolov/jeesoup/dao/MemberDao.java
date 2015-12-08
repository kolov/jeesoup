package com.akolov.jeesoup.dao;


import com.akolov.jeesoup.model.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MemberDao {

    @PersistenceContext
    private EntityManager em;

    public List<Member> getAll() {
        return em.createQuery("select m from Member m order by m.name desc").getResultList();
    }
}
