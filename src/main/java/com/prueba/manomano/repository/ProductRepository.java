package com.prueba.manomano.repository;

import com.prueba.manomano.repository.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByCategorizationType_IdAndPriceGreaterThanAndStartDateLessThanAndEndDateGreaterThan(
      Long categorizationTypeId,
      Double minPrice,
      LocalDateTime startDate,
      LocalDateTime endDate);
}
