package com.jege.jpa.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:复合主键-2个@Id+@IdClass
 */
@Entity
@Table(name = "t_airline")
@IdClass(Airline.AirlinePK.class)
public class Airline {
  @Id
  private String startCity;
  @Id
  private String endCity;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  @Override
  public String toString() {
    return "Airline [startCity=" + startCity + ", endCity=" + endCity + ", name=" + name + "]";
  }

  // 必须static的class
  public static class AirlinePK implements Serializable {
    private static final long serialVersionUID = -7189167162738318201L;
    @Column(length = 3, nullable = false)
    private String startCity;
    @Column(length = 3, nullable = false)
    private String endCity;

    public AirlinePK() {
    }

    public AirlinePK(String startCity, String endCity) {
      this.startCity = startCity;
      this.endCity = endCity;
    }

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

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((endCity == null) ? 0 : endCity.hashCode());
      result = prime * result + ((startCity == null) ? 0 : startCity.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
	return true;
      if (obj == null)
	return false;
      if (getClass() != obj.getClass())
	return false;
      AirlinePK other = (AirlinePK) obj;
      if (endCity == null) {
	if (other.endCity != null)
	  return false;
      } else if (!endCity.equals(other.endCity))
	return false;
      if (startCity == null) {
	if (other.startCity != null)
	  return false;
      } else if (!startCity.equals(other.startCity))
	return false;
      return true;
    }

    @Override
    public String toString() {
      return "AirlinePK [startCity=" + startCity + ", endCity=" + endCity + "]";
    }

  }

}
