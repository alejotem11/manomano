package com.prueba.manomano.service;

import com.prueba.manomano.controller.dto.GetProductsDtoReq;
import com.prueba.manomano.model.ProductResponseList;
import com.prueba.manomano.repository.ProductRepository;
import com.prueba.manomano.repository.entities.Product;
import com.prueba.manomano.service.mapper.ProductServiceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final ProductServiceMapper productServiceMapper;

  @Override
  public ProductResponseList getProducts(final GetProductsDtoReq request) {
    final LocalDateTime discountExpDate = request.getDiscountExpDate();
    final List<Product> products = productRepository
        .findAllByCategorizationType_IdAndPriceGreaterThanAndStartDateLessThanAndEndDateGreaterThan(
            request.getCategoryTypeId(),
            request.getMinPrice(),
            discountExpDate,
            discountExpDate);

    return new ProductResponseList()
        .products(productServiceMapper.getListEntityToProductResponseList(products));
  }
}
