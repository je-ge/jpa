package com.jege.jpa.extend;

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
 * @description:继承测试
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
    
    Person person = new Person();
    person.setName("jege");

    Teacher teacher = new Teacher();
    teacher.setName("仓老师");
    teacher.setAddress("北京");

    Student student = new Student();
    student.setName("机械师");
    student.setSchool("上海");

    entityManager.getTransaction().begin();
    entityManager.persist(student);
    entityManager.persist(teacher);
    entityManager.persist(person);
    entityManager.getTransaction().commit();
  }

  @Test
  public void find() {
    entityManager.clear();
    Student student = entityManager.find(Student.class, 1L);
    System.out.println(student.getSchool());
  }
  
  @Test
  public void find2() {
    entityManager.clear();
    Teacher teacher = entityManager.find(Teacher.class, 2L);
    System.out.println(teacher.getAddress());
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
