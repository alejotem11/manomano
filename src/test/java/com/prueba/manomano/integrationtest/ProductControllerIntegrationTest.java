package com.prueba.manomano.integrationtest;

import com.prueba.manomano.ManomanoApplication;
import com.prueba.manomano.api.ProductsApi;
import com.prueba.manomano.model.ProductResponseList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ManomanoApplication.class)
@Slf4j
class ProductControllerIntegrationTest {

  @Autowired
  private ProductsApi productsApi;

  @Test
  void getProducts() {
    final String discountExpDate = "2021-04-14 00:00:00";
    final long categoryId = 1L;
    final double minPrice = 50.0;

    final ResponseEntity<ProductResponseList> actual = productsApi.getProducts(discountExpDate, categoryId, minPrice);

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    log.info("Body response {}", actual.getBody());
  }
}
