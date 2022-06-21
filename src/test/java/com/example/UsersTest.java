package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.Users;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UsersTest {
    static Users u; 
    @BeforeAll
    static void setUp(){
        u = new Users();
        u.setemail("TestEmail");
        u.setpassword("TestPassword");
        u.setconfirmpassword("TestConfirmPassword");
        u.setSQ1("TestSQ1");
        u.setSQ2("TestSQ2");
        u.setans1("TestAns1");
        u.setans2("TestAns2");
        u.setid(1);
    }
    @Test
    public void userTesting()
    {
        
       assertEquals("TestEmail", u.getemail());
        assertEquals("TestPassword", u.getpassword());
        assertEquals("TestConfirmPassword", u.getconfirmpassword());
        assertEquals("TestSQ1", u.getSQ1());
        assertEquals("TestSQ2", u.getSQ2());
        assertEquals("TestAns1", u.getans1());
        assertEquals("TestAns2", u.getans2());
        assertEquals(1, u.getid());
    
   }
}
