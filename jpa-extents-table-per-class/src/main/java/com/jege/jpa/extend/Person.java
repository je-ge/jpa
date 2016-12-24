package com.jege.jpa.extend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:父类
 */
@Entity
@Table(name = "t_person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
  @Id
  // 主键生成方式不能是IDENTITY
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;
  private String name;

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

}
