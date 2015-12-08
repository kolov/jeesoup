package com.akolov.jeesoup.rest;

import com.akolov.jeesoup.dao.MemberDao;
import com.akolov.jeesoup.model.Member;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/members")
@RequestScoped
public class MemberResourceRESTService {
   @Inject
   private EntityManager em;

   @Inject
   private MemberDao mebberDao;

   @GET
   @Produces("text/xml")
   public List<Member> listAllMembers() {


      return mebberDao.getAll();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("text/xml")
   public Member lookupMemberById(@PathParam("id") long id) {
      return em.find(Member.class, id);
   }
}
