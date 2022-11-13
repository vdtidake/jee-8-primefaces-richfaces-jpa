package edu.vdt.repository;

import edu.vdt.bean.LoginBean;
import edu.vdt.entity.User;
import edu.vdt.exception.UserNotFoundException;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;
import java.util.UUID;

@ApplicationScoped
public class UserRepository {

    Logger logger = Logger.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "UserMgmtPU")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    public UUID createUser(User user){
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (NotSupportedException | HeuristicMixedException | RollbackException | HeuristicRollbackException |
                 SystemException e) {
            throw new RuntimeException(e);
        }
        return user.getId();
    }


    public User getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        TypedQuery<User> typedQuery = entityManager.createNamedQuery("fetchByUsernameAndPassword", User.class);
        typedQuery.setParameter("username", username);
        typedQuery.setParameter("password", password);
        try {
            return typedQuery.getSingleResult();
        }catch (NoResultException nre){
            logger.infof("No record found for %s ", username);
            throw new UserNotFoundException(String.format("No record found for %s ", username), nre);
        }
    }
}
