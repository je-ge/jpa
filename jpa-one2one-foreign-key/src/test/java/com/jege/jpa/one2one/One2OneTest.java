package com.jege.jpa.one2one;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:一对一CRUD Test
 */
public class One2OneTest {
  private static EntityManagerFactory entityManagerFactory = null;
  private EntityManager entityManager = null;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    entityManagerFactory = Persistence.createEntityManagerFactory("com.jege.jpa");
  }

  @Before
  public void setUp() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
  }

  // 级联保存
  @Test
  public void persist() throws Exception {
    Person person = new Person();
    person.setName("jege");

    IdCard idCard = new IdCard();
    idCard.setCardNo("123456789123456789");

    person.setIdCard(idCard);
    idCard.setPerson(person);

    entityManager.getTransaction().begin();
    entityManager.persist(person);
    entityManager.getTransaction().commit();
  }

  @Test
  public void find() throws Exception {
    persist();
    entityManager.clear();
    Person person = entityManager.find(Person.class, 1L);
    System.out.println(person.getName());
    System.out.println("-----------------");
    System.out.println(person.getIdCard().getCardNo());
  }

  @Test
  public void find1() throws Exception {
    persist();
    entityManager.clear();
    IdCard idCard = entityManager.find(IdCard.class, 1L);
    System.out.println(idCard.getCardNo());
    System.out.println("-----------------");
    System.out.println(idCard.getPerson().getName());
  }

  @After
  public void tearDown() throws Exception {
    if (entityManager != null && entityManager.isOpen())
      entityManager.close();
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    if (entityManagerFactory != null && entityManagerFactory.isOpen())
      entityManagerFactory.close();
  }

}
