package net.jan.aims.test.datageneration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.entities.Abilities;
import net.jan.aims.aimsserver.entities.LogBook;
import net.jan.aims.aimsserver.entities.LogBookEntry;
import net.jan.aims.aimsserver.entities.Rank;
import net.jan.aims.aimsserver.entities.Skill;
import net.jan.aims.aimsserver.entities.Talent;
import net.jan.aims.aimsserver.enums.EnumGender;
import net.jan.aims.aimsserver.enums.EnumGroup;
import net.jan.aims.aimsserver.enums.EnumPost;
import net.jan.aims.aimsserver.enums.EnumRank;
import net.jan.aims.aimsserver.enums.SecurityLevel;
import net.jan.aims.aimsserver.utilities.Utilities;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Jan
 */
public class DataGeneration {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory("test");
        em = emf.createEntityManager();
    }

    @After
    public void shutDown() {
        em.close();
        emf.close();
    }

    @Test
    public void loadRankImages() {
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
        for (String path : pathes) {
            r = new Rank();
            r.setName(path);
            r.setEnumRank(EnumRank.getEnumByFileName(path));
            r.setFileData(Utilities.getBytesOfFile(DIRpath.concat("/").concat(path)));
            assertFalse("Name of the Rank was empty.", r.getName().isEmpty());
        }
    }

    @Test
    public void loadTestMember() {

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
        clark.setAbilities(createDefaultAbilities());
        clark.setSecurityLevel(SecurityLevel.SL13);
        clark.setLogBookList(createLogBookList());
        clark.getLogBookList().get(0).setOwner(clark);

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
        badMan.setAbilities(createDefaultAbilities());
        badMan.setSecurityLevel(SecurityLevel.SL6);

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
        m2.setAbilities(createDefaultAbilities());

        Set<String> group3 = new HashSet<>();
        group3.add(EnumGroup.Applicant.name());

        m2.setSystemGroup(group3);

        em.persist(m2);
        em.persist(clark);
        em.persist(badMan);
        em.getTransaction().commit();

        int numberOfUsers = em.createQuery("SELECT u FROM AIMSMember u").getResultList().size();

        assertThat("Other Result is expected.", numberOfUsers, is(3));
    }

    private Abilities createDefaultAbilities() {
        return new Abilities();
    }

    @Test
    public void loadTalentsAnsSkills() {
        List<Talent> talentList = new ArrayList<>();
        List<String> talentsFromFile = getLinesFromFile(getClass().getResource("/talents/Talents").getFile());
        assertThat("Unexptected size", talentsFromFile.size(), is(20));
        for (String line : talentsFromFile) {
            Talent talent = new Talent();
            String[] splittedLine = line.split("#");
            talent.setName(splittedLine[0].trim());
            talent.setDescription(splittedLine[1].trim());
            talentList.add(talent);
        }
        talentsFromFile.clear();

        List<Skill> skillList = new ArrayList<>();
        talentsFromFile = getLinesFromFile(getClass().getResource("/talents/Skills").getFile());
        assertThat("Unexptected size", talentsFromFile.size(), is(20));
        for (String line : talentsFromFile) {
            Skill skill = new Skill();
            String[] splittedLine = line.split("#");
            skill.setName(splittedLine[0].trim());
            skill.setDescription(splittedLine[1].trim());
            skill.setInitiativeValue(Integer.parseInt(splittedLine[2].trim()));
            skill.setUpgradeCosts(Integer.parseInt(splittedLine[3].trim()));
            skillList.add(skill);
        }

        em.getTransaction().begin();
        skillList.forEach((s) -> {
            em.persist(s);
        });
        talentList.forEach((t) -> {
            em.persist(t);
        });

        em.getTransaction().commit();

    }

    private List<String> getLinesFromFile(String path) {
        String line = "";
        List<String> resultList = new ArrayList<>();
        try {
            InputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            do {
                line = br.readLine();
                if (line != null) {
                    resultList.add(line);
                }
            } while (line != null);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataGeneration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataGeneration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultList;
    }

    private List<LogBook> createLogBookList() {
        List<LogBook> resultList = new ArrayList<>();

        LogBook logBook = new LogBook();
        logBook.setSecurityLevel(SecurityLevel.SL7);
        Set<EnumPost> tagList = new HashSet<>();
        tagList.add(EnumPost.SSTFLegeArtis);
        logBook.setTagList(tagList);
        logBook.setTitle("Mission IIV of Lege Artis");

        List<LogBookEntry> enties = new ArrayList<>();
        LogBookEntry e1 = new LogBookEntry();
        e1.setTopic("Start of Mission");
        e1.setSubTopic("Saddle up, lock and load!");
        e1.setContent("blablabla");
        e1.setTimeOfEntry(LocalDateTime.of(2015, 3, 22, 15, 15));
        e1.setOwner(logBook);
        LogBookEntry e2 = new LogBookEntry();
        e2.setTopic("Important question");
        e2.setSubTopic("Who the hell are we to determine the next course of evolution for these people?");
        e2.setContent("blablabla222");
        e2.setTimeOfEntry(LocalDateTime.of(2015, 12, 29, 15, 15));
        e2.setOwner(logBook);
        enties.add(e1);
        enties.add(e2);

        logBook.setEntries(enties);
        resultList.add(logBook);

        return resultList;
    }

}
