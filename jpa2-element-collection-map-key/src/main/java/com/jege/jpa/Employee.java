package com.jege.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
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
  // 生成的表的主键Map.key+EmployeeMap_id
  @CollectionTable(name = "t_employee_map")
  @MapKeyColumn(name = "emp_key")
  @Column(name = "emp_value")
  private Map<String, String> others = new HashMap<String, String>();

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

  public Map<String, String> getOthers() {
    return others;
  }

  public void setOthers(Map<String, String> others) {
    this.others = others;
  }

  

}
