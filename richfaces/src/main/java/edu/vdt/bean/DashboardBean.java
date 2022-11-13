package edu.vdt.bean;

import edu.vdt.entity.User;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

@Named
@ViewScoped
public class DashboardBean implements Serializable {

    User loggedInUser;

    @PostConstruct
    public void init() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        if(Objects.isNull(loggedInUser)){
            try {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                externalContext.redirect(externalContext.getApplicationContextPath()+"/faces/index.xhtml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loggedInUser");
        return "/index.xhtml?faces-redirect=true";
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
