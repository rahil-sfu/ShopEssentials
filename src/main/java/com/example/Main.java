/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.Users;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yaml.snakeyaml.events.Event.ID;

import ch.qos.logback.core.joran.conditional.ElseAction;
import ch.qos.logback.core.net.SyslogOutputStream;
import io.micrometer.core.ipc.http.HttpSender.Request;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.print.event.PrintJobListener;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Main extends HttpServlet{

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String display(Map<String, Object> model) throws Exception {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
      Users user = new Users();
      model.put("user", user);
      return "loginpage";
  }
  } 

  public static final String Admin = "ShopEssentials5";
  public static final String Adminpass = "ShopEssentials5";
  public static String AdminloginStatus = "";
/*
  public long ID = -1;
  public String username = "";
  public String Login_Status = "out";
*/
  public boolean IsloggedIn( HttpServletRequest request, HttpServletResponse response)
{
  HttpSession session = request.getSession();
  String Login = (String) session.getAttribute("Login_Status");
  String user = (String) session.getAttribute("username");
  Integer id = (Integer) session.getAttribute("ID");
  
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
    ResultSet rs =  stmt.executeQuery("SELECT * FROM list WHERE id=" + id);
    rs.next();
    String status = rs.getString("loginstatus");
    System.out.println(status + "its the login status" );
    if(status.equals("out"))
    return false ; 
  } catch (Exception e) {
    System.out.println(e.getMessage()+ "its the error");
    System.out.println("its giving error each time");
  }

  if(Login == "out")
  return false; 
  else if(user =="" || user == null)
  return false; 
  else if( !user.equals(Admin) && id == -1)
  return false; 

  return true; 
}
public int checkUser(Users user , HttpServletRequest request, HttpServletResponse response ){
    try ( Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
      ResultSet rs = stmt.executeQuery("SELECT * FROM list");
      HttpSession session = request.getSession();
      while(rs.next())
      {
        if(rs.getString("email").equals(user.getemail()))
        {
          if(rs.getString("password").equals(user.getpassword()))
          {
            session.setAttribute("username", rs.getString("email"));
            return rs.getInt("id"); 

          }
          return -1; 
      } 
    }
    return -1;
  }
    catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        return -1;
      }
}
public boolean checkUsername(Users user){
  if(user.getemail().equals(Admin))
  return true;
  try ( Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100)  )");
    ResultSet rs = stmt.executeQuery("SELECT * FROM list");
    while(rs.next())
    {
      if(rs.getString("email").equals(user.getemail()))
      {
        return true; 
    } 
  }
  return false;
  }
  catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      return false;
    }
}
@GetMapping(
  path = "/explore"
)
public String start(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response)
{
   if(!IsloggedIn( request, response))
   return "error";
   HttpSession session = request.getSession();
   String user = (String)session.getAttribute("username");
  model.put("name", user);
  return "explore";
}


@GetMapping(path = "/allproductspage/{pid}")
public String product_page(Map<String, Object> model , @PathVariable int pid , HttpServletRequest request, HttpServletResponse response)
{ 
  if(!IsloggedIn( request, response))
   return "error";
  String product_type = "";
    if(pid ==1 )
    product_type="Books";
    else if (pid==2)
    product_type="Clothing";
    else if(pid ==3)
    product_type= "Sports";
    else if(pid==4)
    product_type="Stationary";
    else if(pid == 5)
    product_type="Furniture";
    else 
    {
      product_type= "All Products";
    }  
    System.out.println(product_type);

    HttpSession session = request.getSession();
    String user = (String) session.getAttribute("username");  

  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (id serial, name varchar(300), color varchar(300) , category varchar(300) , description varchar(300) , imagelink1 varchar(300) , imagelink2 varchar(300) , price varchar(300) , author varchar(300) , language varchar(300) , title varchar(300) , pages varchar(300) , year varchar(300) )");
    ResultSet rs = stmt.executeQuery("SELECT * FROM products");
    Pagedata ProductsList = new Pagedata(); 
    if(product_type != "All Products")
    while(rs.next())
    {
      if(rs.getString("category").equals(product_type))
      {
        Products p1 = new Products();
        p1.setId(rs.getLong("id"));
        p1.setName(rs.getString("name"));
        p1.setColor(rs.getString("color"));
        p1.setCategory(rs.getString("category"));
        p1.setDescription(rs.getString("description"));
        p1.setLink1(rs.getString("imagelink1"));
        p1.setLink2(rs.getString("imagelink2"));
        p1.setPrice(rs.getLong("price"));
        p1.setAuthor(rs.getString("author"));
        p1.setTitle(rs.getString("title"));
        p1.setPages(rs.getLong("pages"));
        p1.setYear(rs.getLong("year"));
        p1.setLanguage(rs.getString("language"));
        ProductsList.getProduct().add(p1);
        System.out.println(p1.getLink1());
      }
    }
    else
    while(rs.next())
    {
      Products p1 = new Products();
      p1.setId(rs.getLong("id"));
      p1.setName(rs.getString("name"));
      p1.setColor(rs.getString("color"));
      p1.setCategory(rs.getString("category"));
      p1.setDescription(rs.getString("description"));
      p1.setLink1(rs.getString("imagelink1"));
      p1.setLink2(rs.getString("imagelink2"));
      p1.setPrice(rs.getLong("price"));
      p1.setAuthor(rs.getString("author"));
      p1.setTitle(rs.getString("title"));
      p1.setPages(rs.getLong("pages"));
      p1.setYear(rs.getLong("year"));
      p1.setLanguage(rs.getString("language"));
      ProductsList.getProduct().add(p1);
      System.out.println(p1.getLink1());
    }
    ProductsList.setUsername(user);

    String value = product_type; 
    model.put("title", value);

    session.setAttribute("Display_Products", ProductsList);
    model.put("allproducts", ProductsList);
    filter fil = new filter();
    model.put("filter", fil);
    return "allproductspage";
  } catch (Exception e) {
    return "error";
  }
}

@GetMapping(path = "/singleproduct/{pid}")
public String Viewing_product(Map<String, Object> model , @PathVariable int pid , HttpServletRequest request, HttpServletResponse response)
{
  if(!IsloggedIn( request, response))
   return "error";

   HttpSession session = request.getSession();
   String user = (String) session.getAttribute("username");
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (id serial, name varchar(300), color varchar(300) , category varchar(300) , description varchar(300) , imagelink1 varchar(300) , imagelink2 varchar(300) , price varchar(300) , author varchar(300) , language varchar(300) , title varchar(300) , pages varchar(300) , year varchar(300) )");
    ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE id=" + pid);
    rs.next(); 
    System.out.println("it is here 1");
    //Pagedata data = new Pagedata();   
    Products p1 = new Products();
        p1.setId(rs.getLong("id"));
        p1.setName(rs.getString("name"));
        p1.setColor(rs.getString("color"));
        p1.setCategory(rs.getString("category"));
        p1.setDescription(rs.getString("description"));
        p1.setLink1(rs.getString("imagelink1"));
        p1.setLink2(rs.getString("imagelink2"));
        p1.setPrice(rs.getLong("price"));
        p1.setAuthor(rs.getString("author"));
        p1.setTitle(rs.getString("title"));
        p1.setPages(rs.getLong("pages"));
        p1.setYear(rs.getLong("year"));
        p1.setLanguage(rs.getString("language"));
        p1.setUsernameofperson(user);
    model.put("product", p1);
    System.out.println("it is here 1");
    return "SingleProduct";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }
}

@PostMapping(path = "/filter")
public String filteredProducts(Map<String,Object> model , filter fil ,HttpServletRequest request, HttpServletResponse response)
{ 
  if(!IsloggedIn( request, response))
   return "error";
   if(fil.getMax_price() < fil.getMin_price())
   {
     model.put("message", "Maximum price is smaller than Minimum");
     return "error";
   }
  System.out.println(fil.getMax_price() );
  System.out.println(fil.getMin_price());
  HttpSession session = request.getSession();
  Pagedata ProductsList = (Pagedata)session.getAttribute("Display_Products");
  Pagedata Filtered_ProductsList = new Pagedata(); 
  for(int i = 0 ; i < ProductsList.getProduct().size() ; i++)
  {
    if( fil.getMin_price() <= ProductsList.getProduct().get(i).getPrice() && fil.getMax_price() >= ProductsList.getProduct().get(i).getPrice())
    Filtered_ProductsList.getProduct().add(ProductsList.getProduct().get(i));
  }  
  Filtered_ProductsList.setUsername(ProductsList.getUsername());
    model.put("allproducts", Filtered_ProductsList);
    return "allproductspage";
}



@GetMapping(
  path = "/cartPage"
)
public String Cart(Map<String,Object>model,HttpServletRequest request, HttpServletResponse response)
{
  if(!IsloggedIn( request, response))
  return "error";
  
  HttpSession session = request.getSession();
  Integer userid = (Integer)session.getAttribute("ID");
  String user = (String) session.getAttribute("username");
  
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cart (id serial, userid serial, productid varchar(100) , quantity varchar(100) )");
  
    ResultSet p = stmt.executeQuery("SELECT * FROM cart WHERE userid=" + userid);
  
    
    ArrayList<Integer> temp = new ArrayList<Integer>(); 
    ArrayList<String> tempquantity = new ArrayList<String>(); 
    ArrayList<Integer> tempquantity_int = new ArrayList<Integer>(); 
   // ArrayList<Pagedata> array = new ArrayList<Pagedata>();
  
    while(p.next())
    {
      temp.add(p.getInt("productid"));
      tempquantity.add(p.getString("quantity"));
      tempquantity_int.add(p.getInt("quantity"));
    }
  
    Pagedata data = new Pagedata();
    Purchase cart_details = new Purchase(); 
    float subtotal = 0 ; 
    cart_details.setSubtotal(0);
    int i= 0; 
    for(Integer id : temp) 
    {   
      //int id = rs.getInt("productid");
      p = stmt.executeQuery("SELECT * FROM products WHERE id="+ id );
      System.out.println("319");
      p.next();
      Products p1 = new Products();
      p1.setId(p.getLong("id"));
      p1.setName(p.getString("name"));
      p1.setColor(p.getString("color"));
      p1.setCategory(p.getString("category"));
      p1.setDescription(p.getString("description"));
      p1.setLink1(p.getString("imagelink1"));
      p1.setLink2(p.getString("imagelink2"));
      p1.setPrice(p.getLong("price"));
      p1.setAuthor(p.getString("author"));
      p1.setTitle(p.getString("title"));
      p1.setPages(p.getLong("pages"));
      p1.setYear(p.getLong("year"));
      p1.setLanguage(p.getString("language"));
      p1.setQuantity(tempquantity.get(i));
      System.out.println("262");
      subtotal = subtotal + p.getInt("price")* (tempquantity_int.get(i)) ;
      i++;
      data.getProduct().add(p1);
      
      //array.add(data);
    }
    session.setAttribute("subtotal", subtotal);
    double tax1 = subtotal * (0.12); 
    double tax = Math.round(tax1 * 100.0) / 100.0 ; 
    session.setAttribute("tax", tax);
    double discount = 0 ; 
    session.setAttribute("discount", discount );
    double totalprice = subtotal + tax; 
    session.setAttribute("CostwithoutShipping",totalprice );
    data.setUsername(user);
    model.put("products", data);
    return "cartPage";
  } 
  catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }
}

@GetMapping(
  path = "/shipping"
)
public String ship(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response){
  System.out.println("here 404");
  if(!IsloggedIn( request, response))
  return "error";
  HttpSession session = request.getSession();
  String name = (String)session.getAttribute("username");
  model.put("user", name);
  float subtotal_float = (float)session.getAttribute("subtotal");
  model.put("subtotal", subtotal_float);
  Double tax1 = subtotal_float*(0.12);
  double tax = (double)Math.round(tax1 * (100.0)) / (100.0) ;
  System.out.println(tax);
  model.put("tax", tax);  
  System.out.println("it is here 407");
  return "shipping";
}

@PostMapping(
  path = "/login"
)
public String logging_in(Map<String,Object> model , Users user ,HttpServletRequest request, HttpServletResponse response)
{
  if(user.getemail().equals(Admin) && user.getpassword().equals(Adminpass))
  {
    HttpSession session = request.getSession();
    session.setAttribute("username", user.getemail());
    session.setAttribute("Login_Status", "in");
    AdminloginStatus = "in";
    return "admin";
  }
  int a = checkUser(user , request, response);
  if(  a != -1 )
  {
        try (Connection connection = dataSource.getConnection()) {
          Statement stmt = connection.createStatement();
          stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
          String sql = "UPDATE list SET loginstatus = 'in' WHERE id ="+ a;
          stmt.executeUpdate(sql);
        } catch (Exception e) {
          model.put("message", e.getMessage());
          return "error";
        }
    HttpSession session = request.getSession();
    session.setAttribute("ID", a);
    session.setAttribute("username", user.getemail());
    session.setAttribute("Login_Status", "in");
    
     /* ID = a ;
      Login_Status = "in"; */
      return "redirect:/homepage";
  } 
  return "error";  
}
@GetMapping(
  path = "/register"
)
public String Registerationpage(Map<String,Object> model)
{
  Users user = new Users();
  model.put("user", user);
  return "register";

}
@PostMapping(
  path = "/register"
)
public String Register(Map<String,Object> model , Users user)
{
  if(user.getpassword().equals(user.getconfirmpassword()))
  {
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
    if(!checkUsername(user))
    {
           String sql = "INSERT INTO list (email , password , SQ1 , ans1 , SQ2 , ans2 ) VALUES ('" + user.getemail() + "' , '" + user.getpassword() + "' , '" + user.getSQ1() + "' , '" + user.getans1() + "' , '" + user.getSQ2() + "' , '" + user.getans2() + "' )";
     stmt.executeUpdate(sql);
         }
    else{
      String obj = "Account with this name already exist or wrong information";
      model.put("message" , obj  );

      return "error";
    }
    model.put("user", user);
    return "redirect:/";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }
}
else
{
  model.put("message", "Password doesn't match with confirm password");
  return "error";
}
}
@GetMapping(
  path = "/homepage"
)
public String HOME_PAGE(Map<String,Object> model ,HttpServletRequest request, HttpServletResponse response)
{
  if(!IsloggedIn(request, response))
  {
    return "error";
  }
  
  HttpSession session = request.getSession();
  String name = (String)session.getAttribute("username");
  Users user = new Users();
  user.setemail(name);
  model.put("user", user);
  return "homepage";
}
@GetMapping(
  path = "/forgotpassword"
)
public String Forgot_Page(Map<String,Object> model)
{
  Users user = new Users();
  model.put("user", user);
  return "forgotpassword";
}

@PostMapping(
  path = "/forgotpassword"
)
public String Forget_Password(Map<String,Object> model , Users userForgot)
{
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
    ResultSet rs = stmt.executeQuery("SELECT * FROM list");
    while(rs.next())
    {
      if(rs.getString("email").equals(userForgot.getemail()))
      {
        userForgot.setSQ1(rs.getString("SQ1"));
        userForgot.setSQ2(rs.getString("SQ2"));
        userForgot.setans1(rs.getString("ans1"));
        userForgot.setans2(rs.getString("ans2"));
        userForgot.setid(rs.getInt("id"));
      
      }
    }
    model.put("user",userForgot);
    return "answerquestions";
  } catch (Exception e) {
   model.put("message", e.getMessage());
   System.out.println("it goes here");
    return "error";
  }
}
@PostMapping(
  path = "/resetform/{pid}"
)
public String ResetForm(Map<String, Object> model , Users user , @PathVariable int pid)
{
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100)  )");
    ResultSet rs = stmt.executeQuery("SELECT * FROM list WHERE id =" + pid);
    rs.next();
    // System.out.println(pid);
    // System.out.println(user.getSQ1());
    if(rs.getString("ans1").equals(user.getans1()) && rs.getString("ans2").equals(user.getans2()))
    {
      user.setid(pid);
      model.put("user", user);
      return "resetpassword";
    }
    else
    {
      model.put("message", "Answers not right");
      return "error";
    }
  
  } catch (Exception e) {
   model.put("message", e.getMessage());
   System.out.println("it goes in reset error");
    return "error";
  }
}
@PostMapping(
  path = "/resetpassword/{id}"
)
public String Reset_done(Map<String, Object> model , Users user ,  @PathVariable int id , HttpServletRequest request, HttpServletResponse response )
{
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
    if(user.getpassword().equals(user.getconfirmpassword()))
    {
      System.out.println("line 264" + id);
    stmt.executeUpdate("UPDATE list SET password ='"+ user.getpassword()  + "' WHERE id = " + id );
    }
    else
    {
      model.put("message", " Confirm password does not match ");
      return "error";
    }

    HttpSession session = request.getSession();
    Integer a = (Integer)session.getAttribute("ID");
  
    String sql = "UPDATE list SET loginstatus = 'out' WHERE id ="+ a;
    stmt.executeUpdate(sql);
    session.setAttribute("Login_Status" , "out");
    return "redirect:/";
  } catch (Exception e) {
   model.put("message", e.getMessage());
    return "error";
  } 
}
@GetMapping(
  path = "/logout"
)
public String logoutProcess(Map<String,Object> model , HttpServletRequest request, HttpServletResponse response)
{ 
  if(!IsloggedIn(request, response))
  return "error";
  
  HttpSession session = request.getSession();
  Integer a = (Integer)session.getAttribute("ID");

  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
    String sql = "UPDATE list SET loginstatus = 'out' WHERE id =" + a;
    stmt.executeUpdate(sql);
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }


 
  session.setAttribute("username", "");
  session.setAttribute("Login_Status", "out");
  int temp = -1; 
  session.setAttribute("ID", temp );



  return "redirect:/";

}
  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

@GetMapping(
  path = "/UsersList"
)
public String Database(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response)
{
  HttpSession session = request.getSession();
  String user = (String)session.getAttribute("username");

  if(!user.equals(Admin))
  return "error";
  if(!IsloggedIn( request, response))
  return "error";
 
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100)  )");
    ResultSet rs = stmt.executeQuery("SELECT * FROM list");
    ArrayList<Users> userList = new ArrayList<Users>(); 
    while(rs.next())
    {
      Users u1 = new Users();
      u1.setid(rs.getInt("id"));
      u1.setemail(rs.getString("email"));
      u1.setpassword(rs.getString("password"));
      u1.setSQ1(rs.getString("SQ1"));
      u1.setans1(rs.getString("ans1"));
      u1.setSQ2(rs.getString("SQ2"));
      u1.setans2(rs.getString("ans2"));
      u1.setStatus(rs.getString("loginstatus"));
      userList.add(u1);
    }
    model.put("allusers", userList);
    return "displayUsers";
  } catch (Exception e) {
    return "error";
  }

}

@GetMapping(
  path = "/ProductsList"
)
public String DatabaseProducts(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response)
{
  HttpSession session = request.getSession();
  String user = (String)session.getAttribute("username");

  if(!user.equals(Admin))
  return "error";
  if(!IsloggedIn( request, response))
  return "error";

  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (id serial, name varchar(300), color varchar(300) , category varchar(300) , description varchar(300) , imagelink1 varchar(300) , imagelink2 varchar(300) , price varchar(300) , author varchar(300) , language varchar(300) , title varchar(300) , pages varchar(300) , year varchar(300) )");
    ResultSet rs = stmt.executeQuery("SELECT * FROM products");
    ArrayList<Products> ProductsList = new ArrayList<Products>(); 
    while(rs.next())
    {
      Products p1 = new Products();
      p1.setId(rs.getLong("id"));
      p1.setName(rs.getString("name"));
      p1.setColor(rs.getString("color"));
      p1.setCategory(rs.getString("category"));
      p1.setDescription(rs.getString("description"));
      p1.setLink1(rs.getString("imagelink1"));
      p1.setLink2(rs.getString("imagelink2"));
      p1.setPrice(rs.getLong("price"));
      p1.setAuthor(rs.getString("author"));
      p1.setTitle(rs.getString("title"));
      p1.setPages(rs.getLong("pages"));
      p1.setYear(rs.getLong("year"));
      p1.setLanguage(rs.getString("language"));
      ProductsList.add(p1);
    }
    model.put("allproducts", ProductsList);
    return "displayProducts";
  } catch (Exception e) {
    return "error";
  }

}






@GetMapping("/removeuser/{pid}")
public String getRemoved(Map<String,Object> model ,@PathVariable int pid,HttpServletRequest request, HttpServletResponse response ){
  
  HttpSession session = request.getSession();
  String user = (String)session.getAttribute("username");

  if(!user.equals(Admin))
  return "error";
  if(!IsloggedIn( request, response))
  return "error";

  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    //System.out.println("it is in getRemoved"+id);
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS list (id serial, email varchar(100), password varchar(100) , SQ1 varchar(100) , ans1 varchar(100) ,SQ2 varchar(100) , ans2 varchar(100) , loginstatus varchar(100) )");
    stmt.executeUpdate("DELETE FROM list WHERE id =" + pid);
    return "redirect:/admin";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }
}
@GetMapping("/removeproduct/{pid}")
public String getproductRemoved(Map<String,Object> model ,@PathVariable int pid ,HttpServletRequest request, HttpServletResponse response){
 
  HttpSession session = request.getSession();
  String user = (String)session.getAttribute("username");

  if(!user.equals(Admin))
  return "error";
  if(!IsloggedIn( request, response))
  return "error";
 
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    //System.out.println("it is in getRemoved"+id);
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (id serial, name varchar(300), color varchar(300) , category varchar(300) , description varchar(300) , imagelink1 varchar(300) , imagelink2 varchar(300) , price varchar(300) , author varchar(300) , language varchar(300) , title varchar(300) , pages varchar(300) , year varchar(300) )");
    stmt.executeUpdate("DELETE FROM products WHERE id =" + pid);
    return "redirect:/ProductsList";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }
}

@GetMapping(
  path = "/AddProducts"
)
public String CreateProducts(Map<String,Object> model , HttpServletRequest request, HttpServletResponse response)
{
  HttpSession session = request.getSession();
  String user = (String)session.getAttribute("username");

  if(!user.equals(Admin))
  return "error";
  if(!IsloggedIn( request, response))
  return "error";

  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (id serial, name varchar(300), color varchar(300) , category varchar(300) , description varchar(300) , imagelink1 varchar(300) , imagelink2 varchar(300) , price varchar(300) , author varchar(300) , language varchar(300) , title varchar(300) , pages varchar(300) , year varchar(300) )" );
    Products product = new Products();    
    model.put("product", product);
  return "AddProductsForm";
  } catch (Exception e) {
    return "error";
  }
}
@PostMapping(path = "/AddProducts")
public String AddingProduct(Map<String,Object> model, Products product ,HttpServletRequest request, HttpServletResponse response)
{
  HttpSession session = request.getSession();
  String user = (String)session.getAttribute("username");

  if(!user.equals(Admin))
  return "error";
  if(!IsloggedIn( request, response))
  return "error";

  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
   
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (id serial, name varchar(300), color varchar(300) , category varchar(300) , description varchar(300) , imagelink1 varchar(300) , imagelink2 varchar(300) , price varchar(300) , author varchar(300) , language varchar(300) , title varchar(300) , pages varchar(300) , year varchar(300) )");
    String sql = "INSERT INTO products (name, color , category , description, imagelink1, imagelink2 , price , author , language , title , pages , year ) VALUES ('" + product.getName() + "','" + product.getColor() + "' , '" + product.getCategory() + "' ,'" + product.getDescription() + "','" + product.getLink1() + "','" + product.getLink2() + "','" + product.getPrice() + "','" + product.getAuthor() + "','" + product.getLanguage() + "','" + product.getTitle() + "','" + product.getPages() + "','" + product.getYear() + "')";
   System.out.println("it gets here");     
    stmt.executeUpdate(sql);
    System.out.println("it gets added");     
   
    return "redirect:/ProductsList";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }

  
}
@PostMapping(
  path = "/CartPage/{pid}"
)
public String AddingtoCart(Map<String,Object> model, @PathVariable Integer pid , Products product ,HttpServletRequest request, HttpServletResponse response)
{
  if(!IsloggedIn( request, response))
  return "error";

  HttpSession session = request.getSession();
  Integer userid = (Integer)session.getAttribute("ID");
  
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cart (id serial, userid serial, productid varchar(100) , quantity varchar(100) )" );
  ResultSet rs = stmt.executeQuery("SELECT * FROM cart WHERE userid="+ userid);
  int cartid = -1 ; 
  while(rs.next())
  {
    if(rs.getInt("productid") == pid)
    {
      cartid = rs.getInt("id");
      break ; 
    }
  }
  String sql = "";
  
  if(cartid == -1)
  sql = "INSERT INTO cart (userid , productid , quantity) VALUES ('" + userid + "','" + pid + "','" + product.getQuantity() + "')";
  else
  {
    sql = "UPDATE cart SET quantity = '" + product.getQuantity() + "' WHERE  id=" + cartid ;   
  }
  //String sql = "INSERT INTO cart (userid, productid, quantity) VALUES ('" + ID + "','" + pid + "','" + product.getQuantity() + "')";
 
   //String sql = "INSERT INTO cart (userid, productid, quantity) VALUES ('" + ID + "','" + pid + "','" + product.getQuantity() + "')";
    stmt.executeUpdate(sql);  
  return "redirect:/cartPage";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }
}
 @GetMapping("/removeCart/{pid}")
 public String getcartRemoved(Map<String,Object> model ,@PathVariable String pid ,HttpServletRequest request, HttpServletResponse response){
   
  if(!IsloggedIn( request, response))
   {
    model.put("message", "not logged in ");
    return "error";
   }
   HttpSession session = request.getSession();
   Integer userid = (Integer)session.getAttribute("ID");
   try (Connection connection = dataSource.getConnection()) {
     Statement stmt = connection.createStatement();
      //System.out.println("it is in getRemoved"+id);
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cart (id serial, userid serial, productid varchar(100) , quantity varchar(100) )" );
      ResultSet rs = stmt.executeQuery("SELECT * FROM cart WHERE userid="+ userid);
      int cartid = -1; 
      System.out.println(" its  " + cartid);
      while(rs.next())
      {
        if(rs.getString("productid").equals(pid))
        {
          cartid = rs.getInt("id");
          break ;
        }
      }
      System.out.println(" its the value of cartid to be removed " + cartid);
      stmt.executeUpdate("DELETE FROM cart WHERE id=" + cartid);
   return "redirect:/cartPage";
   } 
   catch (Exception e) {
     model.put("message", e.getMessage());
     return "error";
   }
 }


/*
public String AddingtoCart(Map<String,Object> model, @PathVariable int pid , Products product)
{
  System.out.println(pid + " its pid");
System.out.println(product.getQuantity() + " its quantity");
System.out.println(ID);
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cart (id serial, userid serial, productid varchar(100) , quantity varchar(100) )" );
    ResultSet rs = stmt.executeQuery("SELECT * FROM cart WHERE productid="+ pid);
    String sql = ""; 
    if(rs == null)
    {
       sql = "INSERT INTO cart (userid, productid, quantity) VALUES ('" + ID + "','" + pid + "','" + product.getQuantity() + "')";
    }
   else
   {
     sql = "INSERT INTO cart (userid, productid, quantity) Values ('" + ID + "','" + pid + "','" + product.getQuantity() + "') WHERE productid=" + pid;
   }
    stmt.executeUpdate(sql); 
  return "redirect:/cartPage";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
*/
/*
@GetMapping( path = "/droptables")
public String Drop(Map<String, Object> model)
{
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
   String sql = "DROP TABLE products";  
  stmt.executeUpdate(sql);  
  return "redirect:/cartPage";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }

}*/
/*
@GetMapping(path= "/edit")
public String edit(Map<String, Object> model)
{
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
   String sql = "UPDATE products SET imagelink1 = 'https://shopessentials.s3.filebase.com/products/Furniture/Furniture 5.1.jpg' WHERE price = 50";  
  stmt.executeUpdate(sql);  
  return "redirect:/cartPage";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
  }

}

*//*

@GetMapping(path = "/altertable")
public String done(Map<String, Object> model){


  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
   String sql = "DROP TABLE cart";  
  stmt.executeUpdate(sql);  
  return "redirect:/";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error"; 
  }
}
*/
@PostMapping(
  path = "/search"
)
public String searchProducts(Map<String, Object> model, Search search, HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "keyword") String keyword){
  
  if(!IsloggedIn( request, response))
   {
    model.put("message", "not logged in ");
    return "error";
   }
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE to_tsvector(name||' '||title||' '||color||' '||description||' '||author||' '||category) @@ to_tsquery('" + search.getKeyword() + "')");
    System.out.println("inside search function");
    System.out.println(keyword);
    System.out.println(search.getKeyword());

    HttpSession session = request.getSession();
    String user = (String) session.getAttribute("username");  

    Pagedata ProductsList = new Pagedata(); 
    while(rs.next())
    {
        Products p1 = new Products();
        p1.setId(rs.getLong("id"));
        p1.setName(rs.getString("name"));
        p1.setColor(rs.getString("color"));
        p1.setCategory(rs.getString("category"));
        p1.setDescription(rs.getString("description"));
        p1.setLink1(rs.getString("imagelink1"));
        p1.setLink2(rs.getString("imagelink2"));
        p1.setPrice(rs.getLong("price"));
        p1.setAuthor(rs.getString("author"));
        p1.setTitle(rs.getString("title"));
        p1.setPages(rs.getLong("pages"));
        p1.setYear(rs.getLong("year"));
        p1.setLanguage(rs.getString("language"));
        ProductsList.getProduct().add(p1);
        System.out.println(p1.getLink1());
    }
    ProductsList.setUsername(user);
    session.setAttribute("Display_Products", ProductsList);
    
    String value = "Search Results for " + keyword; 
    model.put("title", value);

    model.put("allproducts", ProductsList);

    filter fil = new filter();
    model.put("filter", fil);
    return "allproductspage";
  } catch (Exception e) {
    model.put("message", e.getMessage());
    return "error";
    }
  }
@ResponseBody
  @GetMapping(path = "/success")
  public String SuccessOrder( HttpServletRequest request, HttpServletResponse response)
  {
    System.out.println("1050");
    return "success";
  }

  @GetMapping(path = "/info")
  public String PersonalInformation( Map<String,Object>model, HttpServletRequest request, HttpServletResponse response)
  {
    if(!IsloggedIn( request, response))
   {
    model.put("message", "not logged in ");
    return "error";
   }
   if(CartEmpty(request,response))
   {
     model.put("message","Cart is Empty");
     return "error";
   }
   HttpSession session = request.getSession(); 
   String user = (String)session.getAttribute("username");
   model.put("user", user);
   PersonInfo person = new PersonInfo();
   model.put("person", person);
   return "PersonInfo";
  }

  @PostMapping(path = "/info")
  public String InfoStored(Map<String,Object>model, HttpServletRequest request, HttpServletResponse response , PersonInfo person)
  {
    if(!IsloggedIn( request, response))
   {
    model.put("message", "not logged in ");
    return "error";
   }
 
   try (Connection connection = dataSource.getConnection()) {
     Statement stmt = connection.createStatement();
     HttpSession session = request.getSession();
     int userid = (Integer)session.getAttribute("ID");

     stmt.executeUpdate("CREATE TABLE IF NOT EXISTS personinfo (id serial, firstname varchar(200), lastname varchar(200), email varchar(200) , phonenumber varchar(200) , userid serial)");
    ResultSet rs = stmt.executeQuery("SELECT * FROM personinfo WHERE userid="+userid);
    if(rs.next())
    {
      stmt.executeUpdate("DELETE FROM personinfo WHERE userid="+userid);
    }

    String sql = "INSERT INTO personinfo (firstname , lastname , email , phonenumber, userid ) VALUES ('" + person.getFirstName() + "' , '" + person.getLastName() + "' , '" + person.getEmail() + "' , '" + person.getPhNumber() + "' , '" + userid + "' )";
    stmt.executeUpdate(sql);
     String name = (String)session.getAttribute("username");
     model.put("user", name);
   
 
    return "redirect:/shipping";
   } catch (Exception e) {
     model.put("message", e.getMessage());
     return "error";
   }

  }

  @PostMapping(path = "/Addressinfo")
  public String OrderDetails(Map<String,Object>model, HttpServletRequest request, HttpServletResponse response , String address , String distance  , String time , Double charges)
  {
    if(!IsloggedIn( request, response))
    {
     model.put("message", "not logged in ");
     return "error";
    }
    if(CartEmpty(request,response))
    {
      model.put("message","Cart is Empty");
      return "error";
    }
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      HttpSession session = request.getSession();
      time = time + " after Shipping";
      int userid = (Integer)session.getAttribute("ID");
      float subtotal = (float)session.getAttribute("subtotal");
      Double tax = (Double)session.getAttribute("tax");
      Double cost_double = tax + subtotal + charges; 
      stmt.executeUpdate("DELETE FROM cart WHERE userid=" + userid ); 
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Orderinfo (id serial, address varchar(200), totalcost varchar(200) , distance varchar(200), time varchar(200) , ordertime timestamp, userid serial)");      
     String sql = "INSERT INTO Orderinfo (address, totalcost, distance , time , ordertime, userid ) VALUES ('" + address + "' , '" + cost_double + "' , '" + distance + "' , '" + time + "' , now(),  '" + userid + "' )";
     stmt.executeUpdate(sql);
      System.out.println("1125");
      Order order = new Order();
      order.setAddress(address);
      System.out.println(charges);
      String charge = String.valueOf(charges);
      order.setCharges(charge);
      order.setDistance(distance);
      System.out.println(time);
      order.setTime(time);
      System.out.println("1130");
    
      String sub = String.valueOf(subtotal);
      System.out.println(subtotal);
     order.setSubtotal(sub);
    
    
     System.out.println(tax);
     String tax_String = String.valueOf(tax);
     order.setTax(tax_String);
     System.out.println("1134");
     order.setDiscounts("0");
     String cost = String.valueOf(cost_double);
     order.setTotalCost(cost);
     System.out.println("1138");
     String name = (String)session.getAttribute("username");
      model.put("user", name);
      model.put("order", order);
      System.out.println("1140");
     return "OrderDetails";
    } catch (Exception e) {
      System.out.println("1145");
      model.put("message", "urre aunda");
      return "error";
    }
  }

  @GetMapping(path = "/Orders")
  public String OrdersbyUser(Map<String,Object>model, HttpServletRequest request, HttpServletResponse response )
  {
    if(!IsloggedIn( request, response))
    {
     model.put("message", "not logged in ");
     return "error";
    }
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement(); 
      HttpSession session = request.getSession();
      Integer userid = (Integer)session.getAttribute("ID");
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS personinfo (id serial, firstname varchar(200), lastname varchar(200), email varchar(200) , phonenumber varchar(200) , userid serial)");
      ResultSet pr = stmt.executeQuery("SELECT * FROM personinfo WHERE userid="+userid); 
     pr.next();
      PersonInfo person = new PersonInfo(); 
      person.setFirstName(pr.getString("firstname"));
      person.setLastName(pr.getString("lastname"));
      person.setEmail(pr.getString("email"));
      person.setPhNumber(pr.getString("phonenumber"));
  //    details.get(i).setPerson_detail(person);
  
      
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Orderinfo (id serial, address varchar(200), totalcost varchar(200) , distance varchar(200), time varchar(200) , ordertime timestamp, userid serial)");    
    ResultSet rs =  stmt.executeQuery("SELECT * FROM Orderinfo WHERE userid=" + userid);
     ArrayList<DisplayOrderDetails> details = new ArrayList<DisplayOrderDetails>();
   int i = 0 ; 
  rs.next();
     while(rs.next())
   {
     Order order = new Order();
     details.add(new DisplayOrderDetails());
    order.setAddress(rs.getString("address"));
      order.setDistance(rs.getString("distance"));
      order.setTime(rs.getString("time"));
     // details.get(rs.getString("ordertime"));
     
     details.get(i).setOrdertime(rs.getString("ordertime"));
     order.setTotalCost(rs.getString("totalcost"));
     details.get(i).setOrder_detail(order);
     details.get(i).setPerson_detail(person);
     i++;
   }
    //System.out.println(details.get(0).getPerson_detail().getEmail());
     System.out.println("1138");
     String name = (String)session.getAttribute("username");
      model.put("user", name);
      model.put("details", details);
      System.out.println("1140");
     return "TotalOrders";
    } catch (Exception e) {
      System.out.println("1145");
      model.put("message", e.getMessage());
      return "error";
    }
  }


  public boolean CartEmpty(HttpServletRequest request, HttpServletResponse response)
  {
      HttpSession session = request.getSession();
      Integer userid = (Integer)session.getAttribute("ID");
      
      try (Connection connection = dataSource.getConnection()) {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cart (id serial, userid serial, productid varchar(100) , quantity varchar(100) )" );
      ResultSet rs = stmt.executeQuery("SELECT * FROM cart WHERE userid="+ userid);
      if(rs.next())
      return false; 
      return true; 
    } 
    catch (Exception e) {
        return true;
      }
  }
  
}