package com.jege.jpa.many2many;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:单向
 */
@Entity
@Table(name = "t_student")
public class Student {
  @Id
  @GeneratedValue
  private Long id;
  private String sname;

  public Student() {

  }

  public Student(String sname) {
    this.sname = sname;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSname() {
    return sname;
  }

  public void setSname(String sname) {
    this.sname = sname;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", sname=" + sname + "]";
  }

}
