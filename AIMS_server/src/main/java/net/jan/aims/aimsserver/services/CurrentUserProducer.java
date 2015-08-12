/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jan.aims.aimsserver.services;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import net.jan.aims.aimsserver.annotations.CurrentUser;
import net.jan.aims.aimsserver.entities.AIMSMember;
import net.jan.aims.aimsserver.persistence.AIMS_UserManagement;

/**
 *
 * @author Jan
 */
@Stateless
public class CurrentUserProducer {
    
    @Resource
    private SessionContext context;
    
    @EJB
    private AIMS_UserManagement manager;
    
    @Produces
    @CurrentUser
    @RequestScoped
    public AIMSMember initCurrentUser(){
        return (AIMSMember) manager.findUserByMail(context.getCallerPrincipal().getName());
    }
    
    
}
