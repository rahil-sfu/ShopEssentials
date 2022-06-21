package com;

public class Users {
    private String email ; 
    private String password ; 
    private String confirmpassword;
    public int id ; 
    public String SQ1; 
    public String ans1; 
    public String SQ2; 
    public String ans2;
    public String status; 

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getemail(){
        return email ; 
    }
    public String getpassword() 
    {
        return password; 
    }
    public String getconfirmpassword() 
    {
        return confirmpassword; 
    }
    public int getid()
    {
        return id;
    }
    public String getSQ1()
    {
        return SQ1;
    }   
    public String getans1()
    {
        return ans1;
    } 
    public void setSQ1(String a )
    {
        SQ1 = a ;
    }
    public void setans1(String a)
    {
        ans1 = a; 
    }
    public String getSQ2()
    {
        return SQ2;
    }   
    public String getans2()
    {
        return ans2;
    } 
    public void setSQ2(String a )
    {
        SQ2 = a ;
    }
    public void setans2(String a)
    {
        ans2 = a; 
    }
    public void setemail(String a)
    {
        this.email = a;
    }
    public void setpassword(String a)
    {
        this.password = a; 
    }
    public void setconfirmpassword(String a)
    {
        this.confirmpassword = a; 
    }
    public void setid(int i)
    {
        this.id = i ; 
    }
}
