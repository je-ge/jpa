package com.jege.jpa.embeddable;

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
 * @description:自定义类型-@Embedded+@Embeddable测试
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
    Address address = new Address();
    address.setCity("beijing");
    address.setStreet("王府井大街");

    Employee employee = new Employee();
    employee.setAddress(address);
    employee.setName("jege");

    entityManager.getTransaction().begin();
    entityManager.persist(employee);
    entityManager.getTransaction().commit();
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
