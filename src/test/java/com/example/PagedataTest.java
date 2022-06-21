package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class PagedataTest {
    static Pagedata data = new Pagedata() ; 





    @BeforeAll
    static void setUp()
    {
        data = new Pagedata();
        ArrayList<Products> productlist = new ArrayList<Products>(); 
        long a = 1 ;
        int loop = 1; 
        while(loop<=10)
        {
        Products p = new Products(); 
    
        p.setAuthor("author"+a);
        p.setCategory("category"+a);
        p.setColor("color"+a);
        p.setDescription("description"+a);
        p.setId(a);
        p.setLanguage("language"+a);
        p.setLink1("link1"+a);
        p.setLink2("link2"+a);
        p.setName("name"+a);
        p.setPages(a);
        p.setPrice(a);
        p.setQuantity("quantity"+a);
        p.setStock(a);
        p.setTitle("title"+a);
        p.setUsernameofperson("usernameofperson"+a);
        p.setYear(a);
        a++;
        loop++;
        productlist.add(p);
        }
        data.setProduct(productlist);
        data.setUsername("username");


    }
    
    @Test
    public void PagedataTesting()
    {
        long a = 1;
        int loop = 1 ;  
        while(loop <= 10)
        {
        assertEquals("author"+a, data.getProduct().get(loop-1).getAuthor());
        assertEquals("category"+a, data.getProduct().get(loop-1).getCategory());
        assertEquals("color"+a, data.getProduct().get(loop-1).getColor());
        assertEquals("description"+a, data.getProduct().get(loop-1).getDescription());
        assertEquals(a, data.getProduct().get(loop-1).getId());
        assertEquals("language"+a, data.getProduct().get(loop-1).getLanguage());
        assertEquals("link1"+a, data.getProduct().get(loop-1).getLink1());
        assertEquals("link2"+a, data.getProduct().get(loop-1).getLink2());
        assertEquals("name"+a, data.getProduct().get(loop-1).getName());
        assertEquals(a, data.getProduct().get(loop-1).getPages());
        assertEquals(a, data.getProduct().get(loop-1).getPrice());
        assertEquals("quantity"+a, data.getProduct().get(loop-1).getQuantity());
        assertEquals(a, data.getProduct().get(loop-1).getStock());
        assertEquals("title"+a, data.getProduct().get(loop-1).getTitle());
        assertEquals("usernameofperson"+a, data.getProduct().get(loop-1).getUsernameofperson());
        assertEquals(a, data.getProduct().get(loop-1).getYear());
        loop++; 
        a++ ; 
        }
       assertEquals("username", data.getUsername());
      }
}
