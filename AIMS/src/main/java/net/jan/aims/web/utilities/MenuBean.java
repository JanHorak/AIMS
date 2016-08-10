package net.jan.aimsclient.web.utilities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import net.jan.aims.aimsserver.annotations.CurrentUser;
import net.jan.aims.aimsserver.entities.AIMSMember;

/**
 *
 * @author Jan
 */
@SessionScoped
@ManagedBean(name = "menubean")
public class MenuBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @CurrentUser
    @Inject
    private AIMSMember member;

    public AIMSMember getMember() {
        return member;
    }

    public void setMember(AIMSMember member) {
        this.member = member;
    }

}
