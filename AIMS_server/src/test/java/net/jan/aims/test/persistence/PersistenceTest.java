/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.test.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.enums.EnumGender;
import net.jan.aims.aimsserver.enums.EnumPost;
import net.jan.aims.aimsserver.enums.EnumRank;
import net.jan.aims.aimsserver.persistence.AIMS_UserManagement;
import net.jan.aims.aimsserver.persistence.UserManager;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Jan
 */
@Ignore
public class PersistenceTest {

    private EntityManager em;

    private EntityManagerFactory emf;

    AIMS_UserManagement usermanager;

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory("test");
        em = emf.createEntityManager();
    }

    @Test
    public void shouldTestLoginFailedScenarios() {

        AIMSMember member = new AIMSMember();
        member.setEmail("test@test.de");
        member.setPassword("testpass");

        usermanager = new UserManager();
        usermanager.setEntityManager(em);

        Assert.assertThat(usermanager.isUserExisting(member), is(false));

        member.setEmail("clark@aims.de");
        Assert.assertThat(usermanager.isUserExisting(member), is(true));
    }

    @Test
    public void shouldDeleteTestUserByMail() {
        List<AIMSMember> applicantsBefore = new ArrayList<>();
        List<AIMSMember> applicantsAfter = new ArrayList<>();
        usermanager = new UserManager();
        em.getTransaction().begin();
        
        usermanager.setEntityManager(em);
        applicantsBefore = usermanager.getAllApplicants();
        AIMSMember s = applicantsBefore.get(0);
        usermanager.rejectUser(s);
        
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        usermanager.deleteUserByMail(s);
        em.getTransaction().commit();
        
        applicantsAfter = usermanager.getAllApplicants();
        em.close();
        
        assertThat("User is still there.",applicantsAfter.size(), is(applicantsAfter.size()-1));
        
    }

    @Test
    public void shouldUpdateTestUsers(){
        usermanager = new UserManager();
        em.getTransaction().begin();
        usermanager.setEntityManager(em);
        
        AIMSMember clark = new AIMSMember();
        clark.setId(2);
        clark.setAdminrights(true);
        clark.setAvatar(new byte[3]);
        clark.setEmail("clark@aims.de");
        clark.setForename("changed");
        clark.setLastname("Kämmt");
        clark.setGender(EnumGender.Male);
        clark.setRank(EnumRank.Admiral);
        clark.setPost(EnumPost.RSKB);
        clark.setApplicant(false);
        clark.setPassword("jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");
        
        usermanager.updateUser(clark);
        em.getTransaction().commit();
        em.close();
        
        em = emf.createEntityManager();
        usermanager.setEntityManager(em);
        em.getTransaction().begin();
        clark = new AIMSMember();
        clark = (AIMSMember) em.createNamedQuery("AIMSMEMBER_findByMail").setParameter("mail", "clark@aims.de").getSingleResult();
        Assert.assertThat(clark.getForename(), is("changed"));
        clark.setForename("Clark");
        usermanager.updateUser(clark);
        em.getTransaction().commit();
        em.close();
        assertThat("Name was unexpected", clark.getForename(), is("Clark"));
    }
    
}
