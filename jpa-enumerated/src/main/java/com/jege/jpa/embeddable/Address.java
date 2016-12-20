package com.jege.jpa.embeddable;

import javax.persistence.Embeddable;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:实现自定义类型，在hibernate里面需要实现UserType接口
 */
@Embeddable
public class Address {
  private String city;
  private String street;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

}
