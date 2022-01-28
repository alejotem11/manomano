package com.prueba.manomano.service;

import com.prueba.manomano.controller.dto.GetProductsDtoReq;
import com.prueba.manomano.model.ProductResponseList;

public interface ProductService {

  ProductResponseList getProducts(GetProductsDtoReq request);
}
