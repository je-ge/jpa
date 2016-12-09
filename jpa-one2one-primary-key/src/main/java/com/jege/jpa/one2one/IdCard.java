package com.jege.jpa.one2one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:关系被维护端,共享主键
 */
@Entity
@Table(name = "t_idcard")
public class IdCard {
  @Id
  @GeneratedValue(generator = "pkGenerator")
  @GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "person"))
  private Long id;
  @Column(length = 18)
  private String cardNo;
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  // 如果不加这个注解，添加t_idcard信息时，就会自动在t_idcard表中增加了一个外键person_id
  @PrimaryKeyJoinColumn
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
