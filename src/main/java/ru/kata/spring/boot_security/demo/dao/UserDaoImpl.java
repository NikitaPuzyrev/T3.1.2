package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select  u from User u", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
        return entityManager.createQuery("select user from User user where user.id =: idParam", User.class)
                .setParameter("idParam", id).getSingleResult();
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(int id) {
        User userToBeDeleted = findById(id);
        entityManager.remove(userToBeDeleted);
    }

    @Override
    public User findByUsername(String userName) {
        return entityManager.createQuery("select user from User user where user.username =: usernameParam", User.class)
                .setParameter("usernameParam", userName).getSingleResult();
    }
}


