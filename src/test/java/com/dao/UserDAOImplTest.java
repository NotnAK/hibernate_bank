package com.dao;

import com.entity.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAOImplTest extends TestCase {

   private UserDAOImpl userDAO;
    private User user;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAOImpl();
        user = new User();
        user.setName("TestName");
        user.setAddress("TestAddress");
    }
    @AfterEach
    public void tearDown() {
        userDAO.close();
    }
    @Test
    public void testAddUser() {
        userDAO.addUser(user);
        User retrievedUser = userDAO.getUserById(user.getId());
        assertNotNull(retrievedUser);
        assertEquals("TestName", retrievedUser.getName());
        assertEquals("TestAddress", retrievedUser.getAddress());
    }
    @Test
    public void testDeleteUser() {
        userDAO.addUser(user);
        assertNotNull(userDAO.getUserById(user.getId()));

        userDAO.deleteUser(user);
        assertNull(userDAO.getUserById(user.getId()));



    }
    @Test
    public void testUpdateUser() {
        userDAO.addUser(user);
        assertNotNull(userDAO.getUserById(user.getId()));

        user.setName("NewName");
        userDAO.updateUser(user);
        User retrievedUser = userDAO.getUserById(user.getId());
        assertEquals("NewName", retrievedUser.getName());
    }
}