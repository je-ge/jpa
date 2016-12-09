package com.jege.jpa.many2many;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:双向：关系被维护端:不能操作中间表
 */
@Entity
@Table(name = "t_student")
public class Student {
  @Id
  @GeneratedValue
  private Long id;
  private String sname;
  @ManyToMany(mappedBy = "students")
  private Set<Teacher> teachers = new HashSet<Teacher>();

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

  public Set<Teacher> getTeachers() {
    return teachers;
  }

  public void setTeachers(Set<Teacher> teachers) {
    this.teachers = teachers;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", sname=" + sname + "]";
  }

}
