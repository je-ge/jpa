package com.jege.jpa.composite;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.Test;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:复合主键-2个@Id测试
 */
public class MainTest {
  @Test
  public void crud() {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("com.jege.jpa");
      entityManager = entityManagerFactory.createEntityManager();

      Airline airline = new Airline();
      airline.setStartCity("PEK");
      airline.setEndCity("SHA");
      airline.setName("bj to sh");// 新建状态

      entityManager.getTransaction().begin();
      entityManager.persist(airline);// 托管状态

      airline.setName("北京飞上海");

      entityManager.getTransaction().commit();

      Airline airline1 = new Airline();
      airline1.setStartCity("PEK");
      airline1.setEndCity("SHA");

      // hibernate:瞬时状态，持久状态(托管)，游离(脱管)状态

      airline1 = entityManager.find(Airline.class, airline1);
      System.out.println("name:" + airline1.getName());

      entityManager.getTransaction().begin();
      entityManager.remove(airline1);
      entityManager.getTransaction().commit();
      // airline1变为删除状态

      entityManager.getTransaction().begin();
      entityManager.persist(airline1);
      entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
      e.printStackTrace();
      entityManager.getTransaction().rollback();
      throw e;
    } finally {
      if (entityManager != null && entityManager.isOpen())
	entityManager.close();// 游离状态
      if (entityManagerFactory != null && entityManagerFactory.isOpen())
	entityManagerFactory.close();
    }
  }

}
