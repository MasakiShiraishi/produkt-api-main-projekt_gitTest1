package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;
    @InjectMocks
  private ProductService underTest;

    @Captor
    ArgumentCaptor<Product> productCaptor;
    @Test
    void givenGetAllProducts_thenExactlyOneInteractionWithRepositoryMethodFindAll(){
         //when
        underTest.getAllProducts();
        //then
        verify(repository,times(1)).findAll();
        verifyNoInteractions(repository);
    }
    @Test
    void whenGetAllCategories_thenExactlyOneInteractionWithRepositoryMethodGetCategories(){
        // when
        underTest.getAllCategories();
        // then
        verify(repository,times(1)).findAllCategories();
        verifyNoInteractions(repository);
    }
    @Test
    @Disabled        // tillägg  -----28/2-------
    void givenAnExistingCategory_whenGetProductsByCategory_thenReceivesANonEmptyList() {
        // given
        ProductService underTest = mock(ProductService.class);
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
        when(underTest.getProductsByCategory("Electronics")).thenReturn(expectedProducts);
        // when
        List<Product> actualProducts = underTest.getProductsByCategory("Electronics");
        // then
        assertNotNull(actualProducts);
        assertTrue(actualProducts.size() > 0);

        // verify
        verify(underTest).getProductsByCategory("Electronics");
    }
    @Test
    void whenAddingAProduct_thenSaveMethodShouldBeCalled() {
        // given
        Product product = new Product("Rätt objekt som sparas", 4000.0, "", "", "");
        // when
        underTest.addProduct(product);
        //then
        verify(repository).save(productCaptor.capture());
        assertEquals(product, productCaptor.getValue());
    }

    @Test
    void whenAddingProductWithDuplicateTitle_thenThrowError() {
        // given
        String title = "vår test-titel";
        Product product = new Product(title, 34.0, "", "", "");
        given(repository.findByTitle(title)).willReturn(Optional.of(product));

        //then
        BadRequestException exception = assertThrows(BadRequestException.class,
                //when
                () -> underTest.addProduct(product));
        verify(repository, times(1)).findByTitle(title);
        verify(repository, never()).save(any());
        assertEquals("En produkt med titeln: vår test-titel finns redan", exception.getMessage());
    }

    @Test   // normalföde 28/2
    void getProductByIdShouldReturnProduct() {
        // given
        int productId = 123;
        Product product = new Product("Title", 35.0, "category", "description", "enUrlSträngHär");
        product.setId(productId);
        BDDMockito.given(repository.findById(productId)).willReturn(Optional.of(product));

        // when
        Product result = underTest.getProductById(productId);

        // then
        assertEquals(product, result);
    }
    @Test  //felflöde  28/2
    void getProductByIdShouldThrowExceptionWhenProductNotFound() {
        // given
        int productId = 123;
        BDDMockito.given(repository.findById(productId)).willReturn(Optional.empty());

        // when
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> underTest.getProductById(productId));

        // then
        assertEquals("Produkt med id " + productId + " hittades inte", thrown.getMessage());
    }
    @BeforeEach    // test för update & delete i 28/2
    void setUp() {
       /* repository = mock(ProductRepository.class);
        underTest = new ProductService(repository);*/
    }

    @Test
    void updateProductShouldReturnUpdatedProductWhenProductExistsInRepository() {
        // given
        int productId = 1;
        Product productToUpdate = new Product("updatedTitle", 20.0, "category", "description", "image_url");
        Product existingProduct = new Product("existingTitle", 10.0, "category", "description", "image_url");
        existingProduct.setId(productId);

        when(repository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(repository.save(existingProduct)).thenReturn(productToUpdate);

        // when
        Product result = underTest.updateProduct(productToUpdate, productId);

        // then
        assertEquals(result, productToUpdate);
        assertNotEquals(10.0, productToUpdate.getPrice());
        verify(repository, times(1)).findById(productId);
        verify(repository, times(1)).save(existingProduct);
    }

    @Test
    void updateProductShouldThrowEntityNotFoundExceptionWhenProductNotFound() {
        // given
        int productId = 1;
        Product productToUpdate = new Product("updatedTitle", 20.0, "category", "description", "image_url");

        when(repository.findById(productId)).thenReturn(Optional.empty());

        // when
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> underTest.updateProduct(productToUpdate, productId));

        // then
        assertEquals(exception.getMessage(), "Product not found with id: " + productId);
        verify(repository, times(1)).findById(productId);
        verify(repository, never()).save(any());
    }

    @Test
    void deleteProductShouldCallDeleteByIdOnRepositoryWhenProductIdExists() {
        // given
        int productId = 1;
        Product existingProduct = new Product("existingTitle", 10.0, "category", "description", "image_url");
        existingProduct.setId(productId);

        when(repository.findById(productId)).thenReturn(Optional.of(existingProduct));

        // when
        underTest.deleteProduct(productId);

        // then
        verify(repository, times(1)).findById(productId);
        verify(repository, times(1)).deleteById(productId);
    }

    @Test
    void deleteProductShouldThrowEntityNotFoundExceptionWhenProductNotFound() {
        // given
        int productId = 1;

        when(repository.findById(productId)).thenReturn(Optional.empty());

        // when
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> underTest.deleteProduct(productId));

        // then
        assertEquals(exception.getMessage(), "Product not found with id: " + productId);
        verify(repository, times(1)).findById(productId);
        verify(repository, never()).deleteById(any());
    }
}
