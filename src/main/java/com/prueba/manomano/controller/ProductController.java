package com.prueba.manomano.controller;

import com.prueba.manomano.api.ProductsApi;
import com.prueba.manomano.controller.dto.GetProductsDtoReq;
import com.prueba.manomano.model.ProductResponseList;
import com.prueba.manomano.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ProductController implements ProductsApi {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private final ProductService productService;

  @Override
  public ResponseEntity<ProductResponseList> getProducts(final String discountExpDate,
                                                         final Long categoryId,
                                                         final Double minPrice) {
    final GetProductsDtoReq dto = getProductsDtoReq(discountExpDate, categoryId, minPrice);
    return ResponseEntity.ok(productService.getProducts(dto));
  }

  private GetProductsDtoReq getProductsDtoReq(final String discountExpDate,
                                              final Long categoryId,
                                              final Double minPrice) {
    return GetProductsDtoReq.builder()
        .discountExpDate(LocalDateTime.parse(discountExpDate, FORMATTER))
        .categoryTypeId(categoryId)
        .minPrice(Optional.ofNullable(minPrice).orElse(0.0))
        .build();
  }
}

