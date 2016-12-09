package com.jege.jpa.one2many;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
  // 多对一保存的时候必须先保存一方，否则会出现多余的update语句，从而影响性能
  @Before
  public void persist() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    ProductType type = new ProductType();
    type.setName("类型1");

    Product product1 = new Product("产品1", type);
    Product product2 = new Product("产品2", type);

    entityManager.persist(type);// 持久化状态 类型1
    entityManager.persist(product1);// 持久化状态
    entityManager.persist(product2);// 持久化状态

    entityManager.getTransaction().commit();
    System.out.println("++++++++++++++++++++");
  }

  // 级联保存
  // 需要先修改ProductType.java类
  // @OneToMany(mappedBy = "type",cascade=CascadeType.PERSIST)
  @Test
  public void persist2() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    ProductType type = new ProductType();
    type.setName("类型1");

    // 建立多方到一方的关系
    Product product1 = new Product("产品1", type);
    Product product2 = new Product("产品2", type);

    // 从一方建立到多方的关系
    type.getProducts().add(product1);
    type.getProducts().add(product2);// 临时状态

    entityManager.persist(type);// 持久化状态

    entityManager.getTransaction().commit();
  }

  // 级联删除
  // 需要先修改ProductType.java类
  // @OneToMany(mappedBy = "type",cascade=CascadeType.REMOVE)
  @Test
  public void remove() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    ProductType type = entityManager.find(ProductType.class, 1L);
    entityManager.remove(type);

    entityManager.getTransaction().commit();
  }

  // 只删除一方，不能删除一方里面的多方
  // 需要先修改ProductType.java类
  // @OneToMany(mappedBy = "type")
  @Test
  public void remove2() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    ProductType type = entityManager.find(ProductType.class, 1L);
    String jpql = "update Product set type=null where type=?";
    Query query = entityManager.createQuery(jpql);
    query.setParameter(1, type);
    int update = query.executeUpdate();
    System.out.println("修改成功多少条："+update);

    jpql = "delete from ProductType where id=?";
    query = entityManager.createQuery(jpql);
    query.setParameter(1, type.getId());
    update = query.executeUpdate();
    System.out.println("删除成功多少条："+update);

    entityManager.getTransaction().commit();
  }

  // 获取多方，然后删除一条多方
  @Test
  public void remove3() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    Product product = entityManager.find(Product.class, 1L);
    entityManager.remove(product);

    entityManager.getTransaction().commit();
  }

  // 获取一方，通过一方来删除一条多方
//需要先修改ProductType.java类
  // @OneToMany(mappedBy = "type", orphanRemoval = true)
  @Test
  public void remove4() throws Exception {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    Product product = entityManager.find(Product.class, 1L);
    ProductType type = entityManager.find(ProductType.class, 1L);
    type.getProducts().remove(product);

    entityManager.getTransaction().commit();
  }

  @Test
  public void find() throws Exception {
    // 不能通过一方获取多方集合是否为null，来判断是否一方是否有多方的数据，只能通过一方获取多方集合.size()来判断
    ProductType type = entityManager.find(ProductType.class, 1L);
    System.out.println(type);
    Set<Product> products = type.getProducts();
    if (products.size() > 0) {
      System.out.println("当前类型下面有产品");
    } else {
      System.out.println("当前类型下面没有产品");
    }
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
