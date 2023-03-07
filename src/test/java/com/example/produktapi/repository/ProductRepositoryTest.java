package com.example.produktapi.repository;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.model.Product;
//import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// static metod så att behöver inte skriva Assertions värje
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository underTest;
     @Test
     void simpleTest(){
    List<Product> products = underTest.findAll();
    assertFalse(products.isEmpty());
}
   @Test
    void whenSearchingForAnExistingTitle_thenReturnThatProduct(){
         //given
       String title = "En dator";
       Product product = new Product(title,
                     25000.0,
               "Elekronik",
               "Bra o ha",
               "urlTillBild");
       underTest.save(product);

       Optional<Product> optionalProduct = underTest.findByTitle(title);

       //then
       // 1 sätt att skriva 3 tester
       assertTrue(optionalProduct.isPresent());
       assertFalse(optionalProduct.isEmpty());
       assertEquals(title, optionalProduct.get().getTitle());

       // 1 annat sätt att skriva samma 3 tester
       Assertions.assertAll(
               () ->assertTrue(optionalProduct.isPresent()),
               () ->assertFalse(optionalProduct.isEmpty()),
               () -> assertEquals(title, optionalProduct.get().getTitle())
       );
     }
     @Test
    void whenSearchingForAnNoExistingTitle_thenReturnEmptyOptional() {
         //given
         String title = "En titel som absolut inte finns ";
         Product product = new Product("",
                 23.0,
                 "",
                 "",
                 "");
         underTest.save(product);
         // when
         Optional<Product> optionalProduct = underTest.findByTitle(title);
         // then
         Assertions.assertAll(
                 () -> assertFalse(optionalProduct.isPresent()),
                 () -> assertTrue(optionalProduct.isEmpty()),
                 () -> assertThrows(NoSuchElementException.class, () -> optionalProduct.get()
                 ,"Hej här är ett meddelande som inte syns")

                 );
     }
    @Test
    void whenSearchingForAnExistingCategory_thenReturnProductList() {
        //given
        String category = "Elektronik";
        Product product1 = new Product("Dator 1", 25000.0, category, "Bra o ha", "urlTillBild1");
        Product product2 = new Product("Dator 2", 30000.0, category, "Bra o ha", "urlTillBild2");
        underTest.save(product1);
        underTest.save(product2);

        //when
        List<Product> listProduct = underTest.findByCategory(category);

        //then
        Assertions.assertAll(
                () -> assertNotNull(listProduct),
                () -> assertFalse(listProduct.isEmpty())

        );
        assertEquals(2, listProduct.size());
        assertTrue(listProduct.contains(product1));
        assertTrue(listProduct.contains(product2));
    }
        //then
        /*assertNotNull(category);
        assertEquals(2, product.spliterator().getExactSizeIfKnown());
        assertTrue(category.listProduct.hasNext());
        assertTrue(products.iterator().next().equals(product1) || products.iterator().next().equals(product2));*/


    @Test
    void whenSearchingForANonExistingCategory_thenReturnEmptyProductList() {
        //given
        String category = "Elektronik";
        Product product1 = new Product("Dator 1", 25000.0, category, "Bra o ha", "urlTillBild1");
        underTest.save(product1);

        //when
        List<Product> listProduct = underTest.findByCategory("Non-existing category");

        //then
        //assertNotNull(listProduct);
        assertTrue(listProduct != null ? listProduct.isEmpty() : false);
    }

    @Test
    void whenSearchingForAnExistingId_thenReturnThatProduct() {
        //given
        Product product = new Product("En dator", 25000.0, "Elektronik", "Bra o ha", "urlTillBild");
        underTest.save(product);

        //when
        Optional<Product> optionalProduct = underTest.findById(product.getId());

        //then
        assertTrue(optionalProduct.isPresent());
        assertFalse(optionalProduct.isEmpty());
        assertEquals(product.getId(), optionalProduct.get().getId());
    }

    @Test
    void testFindAllCategories() {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product("Product 1",20.0,"Category 1","","" ),
                new Product("Product 2",40.0,"Category 2","","" ),
                new Product("Product 3",60.0,"Category 3","","" ),
                new Product("Product 4",80.0,"Category 1","","" )
        );
        underTest.saveAll(products);

        List<String> expectedCategories = Arrays.asList("Category 1", "Category 2", "Category 3");

        // Act
        List<String> actualCategories = underTest.findAllCategories();

        // Assert
        assertEquals(expectedCategories.size(), 3, "Number of categories should match");
        assertTrue(actualCategories.containsAll(expectedCategories), "All expected categories should be present");
        assertFalse(hasDuplicates(actualCategories), "No duplicates should be present");
    }
    private boolean hasDuplicates(List<String> list) {
        return list.size() != list.stream().distinct().count();
    }



}