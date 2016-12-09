package com.jege.jpa.one2one;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:关系维护端
 */
@Entity
@Table(name = "t_person")
public class Person {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  // mappedBy配置映射关系:当前对象IdCard属于哪个person对象
  @OneToOne(optional = false, mappedBy = "person", fetch = FetchType.LAZY)
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
