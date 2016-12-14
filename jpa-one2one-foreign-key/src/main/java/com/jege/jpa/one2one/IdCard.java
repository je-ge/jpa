package com.jege.jpa.one2one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:关系被维护端,多表(唯一unique = true)
 */
@Entity
@Table(name = "t_idcard")
public class IdCard {
  @Id
  @GeneratedValue
  private Long id;
  @Column(length = 18)
  private String cardNo;
  // 默认值optional = true表示idcard_id可以为空;反之。。。
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", unique = true)
  // unique=true确保了一对一关系
  private Person person;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

}
