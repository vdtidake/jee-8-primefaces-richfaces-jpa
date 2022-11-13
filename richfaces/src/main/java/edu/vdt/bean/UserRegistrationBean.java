package edu.vdt.bean;

import edu.vdt.util.FacesUtil;
import edu.vdt.entity.User;
import edu.vdt.service.UserService;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Named
@ViewScoped
public class UserRegistrationBean implements Serializable {

    Logger logger = Logger.getLogger(UserRegistrationBean.class);

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

    public void register(){
        try{
            UUID uuid = userService.createUser(user);
            if(Objects.nonNull(uuid)){
                FacesUtil.showInfoMessage("Registration Success", "Successfully registered user.");
                return;
            }
        }catch (Exception e){
            logger.error("Error while registering the user!!", e);
        }
        FacesUtil.showErrorMessage("Registration Error", "Error white registration.");
    }

    public void clear(){
        user = new User();
    }
}
