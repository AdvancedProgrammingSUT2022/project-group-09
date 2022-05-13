package com.civilization.Controller;

import java.util.regex.Matcher;

import com.civilization.Model.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginTest {

    @Mock
    User user;

    @Mock
    Matcher matcher;

    @BeforeAll
    public static void init() {
    }

    @Test
    public void logoutUnsuccesfull() {
        LoginMenuController loginMenuController = new LoginMenuController();
        Assertions.assertEquals("useri login nakarde hanooz", loginMenuController.logout());
    }

    @Test
    public void logoutSuccessfull() {
        LoginMenuController loginMenuController = new LoginMenuController();
        UserDatabase.setCurrentUser(user);
        Assertions.assertEquals("logged out", loginMenuController.logout());
    }

    // try (MockedStatic<ProgramController> theMock = Mockito.mockStatic(ProgramController.class)) {
    //     theMock.when(ProgramController::getLoggedInUser)
    //             .thenReturn(new User("tst", "tst", "tst"));
    //     String result = loginMenuController.menuEnter(args);
    //     Assertions.assertEquals(result, "invalid navigation!");
    // }

    // @Test
    // public void wrongUsername() throws Throwable {
    //     try (MockedStatic<UserDatabase> theMock = Mockito.mockStatic(UserDatabase.class)) {
    //     // whenNew(User.class).withAnyArguments().thenReturn(user);
    //     // when(user.getUsername()).thenReturn("");
    //     LoginMenuController loginMenuController = new LoginMenuController();
    //     theMock.when(() -> UserDatabase.isUsernameDuplicate(user)).thenReturn(true);
    //     // when(UserDatabase.isUsernameDuplicate(user.getUsername())).thenReturn(false);
    //     // PowerMockito.when(UserDatabase.isUsernameDuplicate(user)).thenReturn(true);
    //     // PowerMockito.when(UserDatabase.isUsernameAndPasswordTrue(user)).thenReturn(false);
    //     // when(UserDatabase.isUsernameDuplicate("")).thenReturn(true);
    //     when(matcher.group("username")).thenReturn("");
    //     when(matcher.group("password")).thenReturn("");

    //     assertEquals("Username and Password didn't match!", loginMenuController.login(matcher));
    //     }
    // }

    // @Test
    // public void wrongPassword() throws Throwable {
    // whenNew(User.class).withAnyArguments().thenReturn(user);
    // LoginMenuController loginMenuController = new LoginMenuController();
    // PowerMockito.when(UserDatabase.isUsernameDuplicate(user)).thenReturn(true);
    // PowerMockito.when(UserDatabase.isUsernameAndPasswordTrue(user)).thenReturn(false);
    // when(matcher.group("username")).thenReturn("");
    // when(matcher.group("password")).thenReturn("");
    // assertEquals("Username and Password didn't match!",
    // loginMenuController.login(matcher));
    // }

    // @Test
    // public void findBug() throws Throwable {
    // whenNew(User.class).withAnyArguments().thenReturn(user);
    // LoginMenuController loginMenuController = new LoginMenuController();
    // PowerMockito.when(UserDatabase.isUsernameDuplicate(user)).thenReturn(true);
    // PowerMockito.when(UserDatabase.isUsernameAndPasswordTrue(user)).thenReturn(true);
    // when(matcher.group("username")).thenReturn("");
    // when(matcher.group("password")).thenReturn("");
    // PowerMockito.when(UserDatabase.getUserFromUsers(user)).thenReturn(null);
    // assertEquals("BUG!", loginMenuController.login(matcher));
    // }

    // @Test
    // public void loginSuccesfull() throws Throwable {
    // User user = new User("username", "password", "nickname");
    // LoginMenuController loginMenuController = new LoginMenuController();
    // PowerMockito.when(UserDatabase.isUsernameDuplicate(user)).thenReturn(true);
    // PowerMockito.when(UserDatabase.isUsernameAndPasswordTrue(user)).thenReturn(true);
    // when(matcher.group("username")).thenReturn("");
    // when(matcher.group("password")).thenReturn("");
    // PowerMockito.when(UserDatabase.getUserFromUsers(user)).thenReturn(null);
    // assertEquals("user logged in successfully!",
    // loginMenuController.login(matcher));
    // }

}
