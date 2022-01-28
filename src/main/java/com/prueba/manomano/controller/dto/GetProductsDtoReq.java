package com.prueba.manomano.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class GetProductsDtoReq {

  LocalDateTime discountExpDate;

  long categoryTypeId;

  double minPrice;
}
