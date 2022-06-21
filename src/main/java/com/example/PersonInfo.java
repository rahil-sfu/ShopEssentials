package com.example;

import java.util.Set;

public class PersonInfo {
   public String FirstName;
   public String LastName; 
   public String Email; 
   public String PhNumber; 
    
    public String getEmail() {
        return Email;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getPhNumber() {
        return PhNumber;
    }
   
    public void setEmail(String email) {
        Email = email;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public void setPhNumber(String phNumber) {
        PhNumber = phNumber;
    }

}
