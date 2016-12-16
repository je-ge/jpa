package com.jege.jpa.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:复合主键-2个@Id
 */
@Entity
@Table(name = "t_airline")
public class Airline implements Serializable {
  private static final long serialVersionUID = -906357110051689484L;
  @Id
  @Column(length = 3)
  private String startCity;
  @Id
  @Column(length = 3)
  private String endCity;
  private String name;

  public String getStartCity() {
    return startCity;
  }

  public void setStartCity(String startCity) {
    this.startCity = startCity;
  }

  public String getEndCity() {
    return endCity;
  }

  public void setEndCity(String endCity) {
    this.endCity = endCity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Airline [startCity=" + startCity + ", endCity=" + endCity + ", name=" + name + "]";
  }

}
