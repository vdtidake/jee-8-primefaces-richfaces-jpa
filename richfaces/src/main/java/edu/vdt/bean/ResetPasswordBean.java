package edu.vdt.bean;

import edu.vdt.util.FacesUtil;
import edu.vdt.entity.User;
import edu.vdt.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ResetPasswordBean implements Serializable {

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

    public void resetPassword(){
       boolean isValidUser = userService.isValidUser(user);
       if(isValidUser){
           FacesUtil.showInfoMessage("Reset Password Email", "Email sent to registered address.");
       }else{
           FacesUtil.showInfoMessage("Username Not Exists", "You are not registered with us.");
       }
    }
}
