package edu.vdt.bean;

import edu.vdt.util.FacesUtil;
import edu.vdt.entity.User;
import edu.vdt.service.UserService;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    Logger logger = Logger.getLogger(LoginBean.class);

    User user;

    @Inject
    UserService userService;

    @PostConstruct
    public void init(){
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String performLogin(){
        logger.infof("logging in: %s", user);
        if(userService.isValidUser(user)){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", user);
            return "pages/dashboard.xhtml?faces-redirect=true";
        }else{
            FacesUtil.showErrorMessage("Login Failed", "Invalid user credentials.");
        }
        return null;
    }

}
