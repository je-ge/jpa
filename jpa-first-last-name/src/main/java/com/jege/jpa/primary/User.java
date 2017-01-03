package com.jege.jpa.primary;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:自动把firstName+lastName合并为name
 */
@Entity
@Table(name = "t_user")
@Access(AccessType.FIELD)
public class User {
  @Id
  @GeneratedValue
  private Long id;
  // <property name="name" access="field">
//  @Access(value = AccessType.FIELD)
//  @Column(name = "name", unique = true)
  private String name;
  private Date birthday;
  @Transient
  private String firstName;
  @Transient
  private String lastName;

  @PostLoad
  private void load() {
    if (name != null) {
      String[] names = name.split(",");
      firstName = names[0];
      lastName = names[1];
    }
  }

  @PrePersist
  @PreUpdate
  private void save() {
    if (firstName != null && !"".equals(firstName)) {
      name = firstName + ",";
    }
    if (lastName != null && !"".equals(lastName)) {
      name += lastName;
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + ", firstName=" + firstName + ", lastName="
	+ lastName + "]";
  }

}
