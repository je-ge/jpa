package com.jege.jpa.composite;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jege.jpa.composite.Airline.AirlinePK;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:复合主键-2个@Id+@IdClass测试
 */
public class MainTest {
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

  @Test
  public void persist() {
    Airline airline = new Airline();
    airline.setEndCity("SHA");
    airline.setStartCity("PEK");
    airline.setName("北京飞上海");

    entityManager.getTransaction().begin();
    entityManager.persist(airline);
    entityManager.getTransaction().commit();
  }

  @Test
  public void find() {
    persist();

    AirlinePK pk = new AirlinePK("PEK", "SHA");
    Airline airline = entityManager.find(Airline.class, pk);
    System.out.println(airline);
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
