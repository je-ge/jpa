package com.jege.jpa.single.table;

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
 * @description:单表CRUD Test
 */
public class SingleTableTest {
  private static EntityManagerFactory entityManagerFactory = null;
  private EntityManager entityManager = null;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    entityManagerFactory = Persistence.createEntityManagerFactory("com.jege.jpa");
  }

  @Before
  public void persist() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    User user = new User("je-ge", 22);

    System.out.println("保存之前：" + user);
    entityManager.persist(user);// JPA会自动把保存后的主键放到当前对象的id里面
    System.out.println("保存之后：" + user);
    System.out.println("+++++++++++++++");
  }

  @Test
  public void modify() throws Exception {
    User user = entityManager.find(User.class, 1L);
    System.out.println("修改前：" + user);
    user.setName("JE-GE");
    user.setAge(18);
    entityManager.merge(user);
    user = entityManager.find(User.class, 1L);
    System.out.println("修改后：" + user);
  }

  @Test
  public void find() throws Exception {
    User user = entityManager.find(User.class, 1L);
    System.out.println(user);
  }

  @Test
  public void remove() throws Exception {
    User user = entityManager.find(User.class, 1L);
    System.out.println("删除前：" + user);
    entityManager.remove(user);
    user = entityManager.find(User.class, 1L);
    System.out.println("删除后：" + user);
  }

  @After
  public void tearDown() throws Exception {
    entityManager.getTransaction().commit();
    if (entityManager != null && entityManager.isOpen())
      entityManager.close();
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    if (entityManagerFactory != null && entityManagerFactory.isOpen())
      entityManagerFactory.close();
  }

}
