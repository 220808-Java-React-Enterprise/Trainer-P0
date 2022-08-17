package com.revature.yolp.services;

import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidUserException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    /*
        Common JUnit annotations:
            - @Test (marks a method as a test case)
            - @Ignore (tells JUnit to skip this test case)
            - @Before (logic that runs once before every test case)
            - @After (logic that runs once after every test case)
            - @BeforeClass (logic that runs only once before all test cases)
            - @AfterClass (logic that runs only once after all test cases)
     */

    private UserService sut; // sut = system under test
    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        sut = new UserService(mockUserDao);
    }

    @After
    public void cleanup() {
        reset(mockUserDao);
    }

    @Test
    public void test_isValidUsername_givenCorrectUsername() {
        // Arrange
        String validUsername = "bduong0929";

        // Act
        boolean flag = sut.isValidUsername(validUsername);

        //Assert
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isValidUsername_throwsInvalidUserException_givenWrongUsername() {
        // Arrange
        String invalidUsername = "invalid";

        // Act
        boolean flag = sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_login_throwsInvalidUserException_givenUnknownUserCredentials() {
        // Arrange
        String invalidUsername = "invalid";
        String invalidPassword = "invalid";
        when(mockUserDao.getUserByUsernameAndPassword(invalidUsername, invalidPassword)).thenReturn(null);

        // Act
        sut.login(invalidUsername, invalidPassword);
    }

    @Test
    public void test_login_returnNonNullUser_givenValidAndKnownCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);

        String validUsername = "bduong0929";
        String validPassword = "Passw0rd";
        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(mockUserDao.getUserByUsernameAndPassword(validUsername, validPassword)).thenReturn(new User());

        // Act
        User user = spiedSut.login(validUsername, validPassword);

        // Assert
        Assert.assertNotNull(user);
        verify(mockUserDao, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
    }


}