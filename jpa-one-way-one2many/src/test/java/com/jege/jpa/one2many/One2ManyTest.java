package com.jege.jpa.one2many;

import java.util.Set;

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
 * @description:单向一对多Test
 */
public class One2ManyTest {
  private static EntityManagerFactory entityManagerFactory = null;
  private EntityManager entityManager = null;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    entityManagerFactory = Persistence.createEntityManagerFactory("com.jege.jpa");
  }

  // 一次性保存1一个类型，保存这个类型下面的2个产品
  // 无论怎样调整保存一方，多方的顺序，都会出现多余的update语句，从而影响性能
  @Before
  public void persist() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    ProductType type = new ProductType();
    type.setName("类型1");

    Product product1 = new Product("产品1");
    Product product2 = new Product("产品2");

    // 只能从一方建立到多方的关系
    type.getProducts().add(product1);
    type.getProducts().add(product2);// 临时状态

    entityManager.persist(type);// 持久状态 1 类型1
    entityManager.persist(product1);// 持久状态,只保存了name属性,type_id没有保存:1 产品1 null
    entityManager.persist(product2);// 持久状态,只保存了name属性,type_id没有保存:2 产品2 null

    // type.getProducts(产品1的id=1，产品2的id=2)出现脏数据
    // 脏数据在事务范围内出现，2条更新语句
  }

  @Test
  public void find() throws Exception {
    System.out.println("---------------------");
    // 不能通过一方获取多方集合是否为null，来判断是否一方是否有多方的数据，只能通过一方获取多方集合.size()来判断
    ProductType type = entityManager.find(ProductType.class, 1L);
    System.out.println(type);
    Set<Product> products = type.getProducts();
    if (products.size() > 0) {
      System.out.println("当前类型下面是有产品的");
    } else {
      System.out.println("当前类型下面是没有产品的");
    }
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
