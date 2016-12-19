package com.jege.jpa.embedded;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:复合主键-@EmbeddedId
 */
@Entity
@Table(name = "t_airline")
public class Airline {
  @EmbeddedId
  private AirlinePK pk;
  private String name;

  public Airline() {

  }

  public Airline(AirlinePK pk, String name) {
    this.pk = pk;
    this.name = name;
  }

  public Airline(String startCity, String endCity, String name) {
    pk = new AirlinePK(startCity, endCity);
    this.name = name;
  }

  public AirlinePK getPk() {
    return pk;
  }

  public void setPk(AirlinePK pk) {
    this.pk = pk;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Airline [pk=" + pk + ", name=" + name + "]";
  }

}
