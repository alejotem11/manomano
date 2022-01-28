package com.prueba.manomano.repository.entities;

import lombok.Data;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "CATEGORIZATION_TYPE")
@Data
public class CategorizationType {

  @Id
  private Long id;

  private String type;

  private String desc;

  @OneToMany(mappedBy = "categorizationType")
  private List<Product> products;
}
