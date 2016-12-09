package com.jege.jpa.many2one;

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
 * @description:单向多对一Test
 */
public class Many2OneTest {
  private static EntityManagerFactory entityManagerFactory = null;
  private EntityManager entityManager = null;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    entityManagerFactory = Persistence.createEntityManagerFactory("com.jege.jpa");
  }

  // 要求：一次性保存1个产品类型，保存2个产品
  // 单向多对一保存的时候必须先保存一方，否则会出现多余的update语句，从而影响性能
  @Before
  public void persist() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    ProductType type = new ProductType();
    type.setName("产品类型1");

    // 传入type的本质是处理数据库product表的type_id外键
    Product product1 = new Product("产品1", null);
    Product product2 = new Product("产品2", type);

    System.out.println("保存之前：" + type);
    entityManager.persist(type);// JPA会自动把保存后的主键放到当前对象的id里面
    System.out.println("保存之后：" + type);
    entityManager.persist(product1);
    entityManager.persist(product2);
    System.out.println("++++++++++++++++++++");
  }

  // 可以通过多方Product获取一方ProductType是否为null，来判断是否有产品产品类型
  @Test
  public void find() throws Exception {
    Product product = entityManager.find(Product.class, 2L);
    System.out.println(product);
    ProductType type = product.getType();
    if (type == null) {
      System.out.println("当前产品是没有产品类型的");
    } else {
      System.out.println("当前产品是有产品类型的");
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
