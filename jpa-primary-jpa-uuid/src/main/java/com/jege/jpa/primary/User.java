package com.jege.jpa.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:jpa-uuid主键生成策略
 */
@Entity
@Table(name = "t_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User {
  @Id
  @GeneratedValue(generator = "jpa-uuid")
  @Column(length = 32)
  private String id;
  private String name;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + "]";
  }

}
