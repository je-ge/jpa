package com.jege.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JPA2Test {

  private static EntityManagerFactory entityManagerFactory = null;
  private EntityManager entityManager = null;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    entityManagerFactory = Persistence.createEntityManagerFactory("com.jege.jpa");
  }

  @Before
  public void setUp() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();// Session
  }

  @Test
  public void persist() throws Exception {
    Employee employee = new Employee();
    employee.setName("je-ge");

    employee.getOthers().put("home", "beijing");
    employee.getOthers().put("work", "shanghai");
    entityManager.getTransaction().begin();
    entityManager.persist(employee);
    entityManager.getTransaction().commit();
  }

  @Test
  public void find() throws Exception {
    persist();
    entityManager.clear();

    Employee employee = entityManager.find(Employee.class, 1L);
    System.out.println(employee.getName());
    System.out.println(employee.getOthers());
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
