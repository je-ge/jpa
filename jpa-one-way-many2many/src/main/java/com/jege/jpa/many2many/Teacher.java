package com.jege.jpa.many2many;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:单向:一个老师教多个学生
 */
@Entity
@Table(name = "t_teacher")
public class Teacher {
  @Id
  @GeneratedValue
  private Long id;
  private String tname;
  // @ManyToMany注释表示Teacher是多对多关系的一端。
  // @JoinTable描述了多对多关系的数据表关系。name属性指定中间表名称，joinColumns定义中间表与Teacher表的外键关系。
  // 中间表Teacher_Student的Teacher_ID列是Teacher表的主键列对应的外键列，inverseJoinColumns属性定义了中间表与另外一端(Student)的外键关系。
  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinTable(name = "t_teacher_student", joinColumns = { @JoinColumn(name = "teacher_id") }, inverseJoinColumns = {
      @JoinColumn(name = "student_id") })
  private Set<Student> students = new HashSet<Student>();

  public Teacher() {

  }

  public Teacher(String tname) {
    this.tname = tname;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTname() {
    return tname;
  }

  public void setTname(String tname) {
    this.tname = tname;
  }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }

  @Override
  public String toString() {
    return "Teacher [id=" + id + ", tname=" + tname + "]";
  }

}
