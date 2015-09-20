/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.helper;

import javax.inject.Inject;
import net.jan.aims.aimsserver.annotations.CurrentUser;
import net.jan.aims.aimsserver.entities.AIMSMember;

/**
 *
 * @author Jan
 */
public class ProxyHelper {

    @Inject
    @CurrentUser
    private AIMSMember member;

    public AIMSMember getUser() {
        AIMSMember result = new AIMSMember();
        result.setId(member.getId());
        result.setAbilities(member.getAbilities());
        result.setAdminrights(member.isAdminrights());
        result.setApplicant(member.isApplicant());
        result.setAvatar(member.getAvatar());
        result.setEmail(member.getEmail());
        result.setForename(member.getForename());
        result.setLastname(member.getLastname());
        result.setGender(member.getGender());
        result.setLogBookList(member.getLogBookList());
        result.setPassword(member.getPassword());
        result.setPost(member.getPost());
        result.setRank(member.getRank());
        result.setSecurityLevel(member.getSecurityLevel());
        result.setSystemGroup(member.getSystemGroup());

        return result;
    }

}
