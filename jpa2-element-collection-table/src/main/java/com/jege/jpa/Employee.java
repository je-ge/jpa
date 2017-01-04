package com.jege.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:pojo模型
 */
@Entity
@Table(name = "t_employee")
public class Employee {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @ElementCollection
  // 一对多集合,如果是JPA1.0必须再写一个Pojo类
  @CollectionTable(name = "t_colors", joinColumns = @JoinColumn(name = "employee_id"))
  @Column(name = "color")
  private List<String> colors = new ArrayList<String>();

  public Employee() {

  }

  public Employee(String name) {
    this.name = name;
  }

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

  public List<String> getColors() {
    return colors;
  }

  public void setColors(List<String> colors) {
    this.colors = colors;
  }

}
