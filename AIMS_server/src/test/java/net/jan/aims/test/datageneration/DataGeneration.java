package net.jan.aims.test.datageneration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.entities.Rank;
import net.jan.aims.aimsserver.enums.EnumGender;
import net.jan.aims.aimsserver.enums.EnumGroup;
import net.jan.aims.aimsserver.enums.EnumPost;
import net.jan.aims.aimsserver.enums.EnumRank;
import net.jan.aims.aimsserver.utilities.Utilities;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan
 */
public class DataGeneration {
    
    @Test
    public void loadRankImages(){
        final String DIRpath = getClass().getClassLoader().getResource("images/ranks").getFile();
        final URL files = getClass().getClassLoader().getResource("images/ranks");
        File f = null;
        try {
            f = new File(files.toURI()).getAbsoluteFile();
        } catch (URISyntaxException ex) {
            Logger.getLogger(DataGeneration.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] pathes = f.list();
        Rank r = null;
        for (String path : pathes){
            r = new Rank();
            r.setName(path);
            r.setEnumRank(EnumRank.getEnumByFileName(path));
            r.setFileData(Utilities.getBytesOfFile(DIRpath.concat("/").concat(path)));
            assertFalse("Name of the Rank was empty." ,r.getName().isEmpty());
        }
        
    }
    
    @Test
    public void loadTestMember(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em  = emf.createEntityManager();
        
        // Load images
        String clarkPath = getClass().getClassLoader().getResource("images/common/CK.png").getFile();
        byte[] clarkAvatarStream = Utilities.getBytesOfFile(clarkPath);
        
        String badPath = getClass().getClassLoader().getResource("images/common/BM.png").getFile();
        byte[] badAvatarStream = Utilities.getBytesOfFile(badPath);
        
        
        String defaultPath = getClass().getClassLoader().getResource("images/common/profile-icon.png").getFile();
        byte[] defaultAvatarStream = Utilities.getBytesOfFile(defaultPath);
        
        
        em.getTransaction().begin();
        
        // Clark kaemmt
        AIMSMember clark = new AIMSMember();
        clark.setAdminrights(true);
        clark.setAvatar(clarkAvatarStream);
        clark.setEmail("clark@aims.de");
        clark.setForename("Clark");
        clark.setLastname("Kämmt");
        clark.setGender(EnumGender.Male);
        clark.setRank(EnumRank.Admiral);
        clark.setPost(EnumPost.RSKB);
        clark.setApplicant(false);
        clark.setPassword("jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");
        
        Set<String> group = new HashSet<>();
        group.add(EnumGroup.Admin.name());
        clark.setSystemGroup(group);
        
        // Bad-man
        AIMSMember badMan = new AIMSMember();
        badMan.setAdminrights(false);
        badMan.setAvatar(badAvatarStream);
        badMan.setGender(EnumGender.Male);
        badMan.setEmail("bad@aims.de");
        badMan.setForename("Bad");
        badMan.setLastname("Man");
        badMan.setRank(EnumRank.Cadet);
        badMan.setPost(EnumPost.SSTFExVoto);
        badMan.setApplicant(false);
        badMan.setPassword("jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");
        
        Set<String> group2 = new HashSet<>();
        group2.add(EnumGroup.Member.name());

        badMan.setSystemGroup(group2);
        
        // Applicant
        AIMSMember m2 = new AIMSMember();
        m2.setAdminrights(false);
        m2.setAvatar(defaultAvatarStream);
        m2.setEmail("app@web.de");
        m2.setForename("MyName");
        m2.setLastname("MyOtherName");
        m2.setGender(EnumGender.Female);
        m2.setApplicant(true);
        m2.setPassword("jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");
        
        Set<String> group3 = new HashSet<>();
        group3.add(EnumGroup.Applicant.name());

        m2.setSystemGroup(group3);
        
        
        em.persist(m2);
        em.persist(clark);
        em.persist(badMan);
        em.getTransaction().commit();
        
        int numberOfUsers = em.createQuery("SELECT u FROM AIMSMember u").getResultList().size();
        
        em.close();
        
        assertThat("Other Result is expected.", numberOfUsers, is(3));
    }

}
