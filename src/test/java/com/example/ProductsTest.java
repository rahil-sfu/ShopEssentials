package com.example;

//import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class ProductsTest {
    static Products p = new Products(); 
    @BeforeAll
    static void setUp()
    {
        long a = 1 ; 
        p = new Products();
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
    }

    @Test
    public void ProductTesting()
    {
        long a = 1;
        assertEquals("author", p.getAuthor());
        assertEquals("category", p.getCategory());
        assertEquals("color", p.getColor());
        assertEquals("description", p.getDescription());
        assertEquals(a, p.getId());
        assertEquals("language", p.getLanguage());
        assertEquals("link1", p.getLink1());
        assertEquals("link2", p.getLink2());
        assertEquals("name", p.getName());
        assertEquals(a, p.getPages());
        assertEquals(a, p.getPrice());
        assertEquals("quantity", p.getQuantity());
        assertEquals(a, p.getStock());
        assertEquals("title", p.getTitle());
        assertEquals("usernameofperson", p.getUsernameofperson());
        assertEquals(a, p.getYear());
    }

}
