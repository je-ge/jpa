package com.jege.jpa.one2one;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:关系维护端：产生一个中间关联表
 */
@Entity
@Table(name = "t_person")
public class Person {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "t_person_idcard", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "idcard_id", unique = true))
  private IdCard idCard;

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

  public IdCard getIdCard() {
    return idCard;
  }

  public void setIdCard(IdCard idCard) {
    this.idCard = idCard;
  }

}
