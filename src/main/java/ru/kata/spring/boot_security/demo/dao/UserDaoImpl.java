package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    private List<User> users;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select  u from User u", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
        users = entityManager.createQuery("select  u from User u", User.class).getResultList();
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void saveUser(User user) {
        List<Role> list = user.getRoles();
        System.out.println(list);
        entityManager.merge(user);
    }

    public void updateUser(int id, User updatedUser) {
        User userToBeUpdated = findById(id);
        entityManager.merge(updatedUser);
    }

    @Override
    public void deleteById(int id) {
        users = entityManager.createQuery("select  u from User u", User.class).getResultList();
        User userToBeDeleted = findById(id);
        entityManager.remove(userToBeDeleted);
    }

    @Override
    public User findByUsername(String username) {
        users = entityManager.createQuery("select  u from User u", User.class).getResultList();
        return users.stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }
}
