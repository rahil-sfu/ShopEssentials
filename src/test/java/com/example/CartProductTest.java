package com.example;

//import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CartProductTest{
    static CartProduct c; 

    @BeforeAll
    static void setUp()
    {
        c = new CartProduct();
        long a = 1 ;
        Products p = new Products();    
        p.setAuthor("author");
        p.setCategory("category");
        p.setColor("color");
        p.setDescription("description");
        p.setId(a);
        p.setLanguage("language");
        p.setLink1("link1");
        p.setLink2("link2");
        p.setName("name");
        p.setPages(a);
        p.setPrice(a);
        p.setQuantity("quantity");
        p.setStock(a);
        p.setTitle("title");
        p.setUsernameofperson("usernameofperson");
        p.setYear(a);
        c.setQuantity("1");
        c.setProduct(p);
    }
    @Test
    public void cartTesting(){
        long a = 1;
        assertEquals("author", c.getProduct().getAuthor());
        assertEquals("category", c.getProduct().getCategory());
        assertEquals("color", c.getProduct().getColor());
        assertEquals("description", c.getProduct().getDescription());
        assertEquals(a, c.getProduct().getId());
        assertEquals("language", c.getProduct().getLanguage());
        assertEquals("link1", c.getProduct().getLink1());
        assertEquals("link2", c.getProduct().getLink2());
        assertEquals("name", c.getProduct().getName());
        assertEquals(a, c.getProduct().getPages());
        assertEquals(a, c.getProduct().getPrice());
        assertEquals("quantity", c.getProduct().getQuantity());
        assertEquals(a, c.getProduct().getStock());
        assertEquals("title", c.getProduct().getTitle());
        assertEquals("usernameofperson", c.getProduct().getUsernameofperson());
        assertEquals(a, c.getProduct().getYear());
        assertEquals("1", c.getQuantity());
        
    }
}

