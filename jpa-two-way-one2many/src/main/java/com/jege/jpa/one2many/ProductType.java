package com.jege.jpa.one2many;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:双向：一个产品类型下面有n个产品
 */
@Entity
@Table(name = "t_product_type")
public class ProductType {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  // 一对多:集合Set
  @OneToMany(mappedBy = "type", orphanRemoval = true)
  private Set<Product> products = new HashSet<Product>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    return "ProductType [id=" + id + ", name=" + name + "]";
  }

}
