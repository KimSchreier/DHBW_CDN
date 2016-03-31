package de.dhbw.ds.cdn;

import de.dhbw.ds.cdn.data.User;
import de.dhbw.ds.cdn.data.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopApplication.class)
@WebAppConfiguration

public class UserTests {


    @Test
    public void createUserTest(){
        User testuser = new User();

        org.junit.Assert.assertNotNull(testuser);
    }

    @Test
    public void setUserIdTest(){

    }

    @Test
    public void setUsernameTest(){
        String username = "Mustermann";

        User testuser = new User();
        testuser.setName(username);

        org.junit.Assert.assertEquals(username, testuser.getName());
    }

    @Test
    public void setPasswordTest(){
        String password = "Musterpw";

        User testuser = new User();
        testuser.setPassword(password);

        org.junit.Assert.assertEquals(password, testuser.getPassword());
    }

    @Test
    public void setRoleTest(){

    }

    @Test
    public void setUserTest(){
        String password = "Musterpw";
        String username = "Mustermann";
        User testuser = new User();

        testuser.setPassword(password);
        testuser.setName(username);

        User user = new User(testuser);
        System.out.println(user);

        org.junit.Assert.assertEquals(password, user.getPassword());
        org.junit.Assert.assertEquals(username, user.getName());

    }
}
