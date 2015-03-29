package net.jan.aims.test.datageneration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.jan.aims.aims_server.entities.AIMSMember;
import net.jan.aims.aims_server.entities.Rank;
import net.jan.aims.aims_server.enums.EnumGroup;
import net.jan.aims.aims_server.enums.EnumRank;
import net.jan.aims_server.utilities.Utilities;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan
 */
public class DataGeneration {

    public DataGeneration() {
    }
    
    @Test
    public void loadRankImages(){
        final String DIRpath = getClass().getClassLoader().getResource("images/ranks").getFile();
        final URL files = getClass().getClassLoader().getResource("images/ranks");
        List<Rank> ranks = new ArrayList<>();
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
            assertTrue(!r.getName().isEmpty());
            System.out.println(r.toString());
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
        clark.setRank(EnumRank.Admiral);
        clark.setApplicant(false);
        clark.setPassword("jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");
        
        Set<String> group = new HashSet<>();
        group.add(EnumGroup.Admin.name());
        clark.setSystemGroup(group);
        
        // Bad-man
        AIMSMember badMan = new AIMSMember();
        badMan.setAdminrights(false);
        badMan.setAvatar(badAvatarStream);
        badMan.setEmail("bad@aims.de");
        badMan.setForename("Bad");
        badMan.setLastname("Man");
        badMan.setRank(EnumRank.Cadet);
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
        m2.setRank(EnumRank.Cadet);
        m2.setApplicant(true);
        m2.setPassword("jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");
        
        Set<String> group3 = new HashSet<>();
        group3.add(EnumGroup.Member.name());

        m2.setSystemGroup(group3);
        
        
        em.persist(m2);
        em.persist(clark);
        em.persist(badMan);
        em.getTransaction().commit();
        em.close();
    }

}
