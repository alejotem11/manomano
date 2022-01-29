package com.prueba.manomano.unittest;

import com.prueba.manomano.controller.ProductController;
import com.prueba.manomano.service.ProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@MockBeans(@MockBean(ProductService.class))
@WebMvcTest(ProductController.class)
class ProductControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @ParameterizedTest
  @MethodSource("paramsForMissingParamsTest")
  void getProducts_WhenMissingParams_ShouldReturnBadRequest(MockHttpServletRequestBuilder request) throws Exception {
    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  private static Stream<Arguments> paramsForMissingParamsTest() {
    return Stream.of(
        Arguments.of(missingDateReq()),
        Arguments.of(missingCategoryId()),
        Arguments.of(missingMinPrice()));
  }

  private static MockHttpServletRequestBuilder missingDateReq() {
    return get("/products")
        .param("categoryId", "1")
        .param("minPrice", "50.0");
  }

  private static MockHttpServletRequestBuilder missingCategoryId() {
    return get("/products")
        .param("discountExpDate", "2021-04-14 00:00:00")
        .param("minPrice", "50.0");
  }

  private static MockHttpServletRequestBuilder missingMinPrice() {
    return get("/products")
        .param("discountExpDate", "2021-04-14 00:00:00")
        .param("categoryId", "1");
  }
}
