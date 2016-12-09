package com.jege.jpa.many2many;

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
 * @description:单向多对多Test
 */
public class Many2ManyTest {
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

  // t1老师教2个学生s1,s2
  // t2老师教3个学生s1,s2,s3
  // 总共10条insert
  @Test
  public void persist() throws Exception {
    entityManager.getTransaction().begin();

    Teacher t1 = new Teacher("t1");
    Teacher t2 = new Teacher("t2");

    Student s1 = new Student("s1");
    Student s2 = new Student("s2");
    Student s3 = new Student("s3");

    entityManager.persist(t1);
    entityManager.persist(t2);

    entityManager.persist(s1);
    entityManager.persist(s2);
    entityManager.persist(s3);// 全部发出5条insert单表

    // 添加中间表
    // t1老师教2个学生s1,s2
    // t2老师教3个学生s1,s2,s3
    t1.getStudents().add(s1);
    t1.getStudents().add(s2);

    t2.getStudents().add(s1);
    t2.getStudents().add(s2);
    t2.getStudents().add(s3);

    entityManager.getTransaction().commit();// 发出5条insert中间表
    entityManager.close();
  }

  // t1老师教2个学生s1,s2
  // 修改为 教2个学生s1,s3:先删除在添加
  @Test
  public void update() throws Exception {
    persist();

    entityManager.getTransaction().begin();

    Teacher t1 = entityManager.find(Teacher.class, 1L);
    Student s2 = entityManager.find(Student.class, 2L);
    Student s3 = entityManager.find(Student.class, 3L);
    // 删除
    t1.getStudents().remove(s2);
    // 添加
    t1.getStudents().add(s3);

    entityManager.getTransaction().commit();
  }

  // 删除t1的教的所有学生
  @Test
  public void delete() throws Exception {
    persist();

    entityManager.getTransaction().begin();

    Teacher t1 = entityManager.find(Teacher.class, 1L);
    t1.getStudents().clear();

    entityManager.getTransaction().commit();
  }

  // insert 3条，单表插入,中间表无反应
  // 因为Student是关系被维护端:不能操作中间表
  @Test
  public void persist2() throws Exception {
    Teacher teacher = new Teacher("t1");

    Student student1 = new Student("s1");
    Student student2 = new Student("s2");

    student1.getTeachers().add(teacher);// 插入中间表
    student2.getTeachers().add(teacher);

    entityManager.getTransaction().begin();
    entityManager.persist(teacher);
    entityManager.persist(student1);
    entityManager.persist(student2);
    entityManager.getTransaction().commit();
  }

  @Test
  public void find() throws Exception {
    persist();
    
    entityManager = entityManagerFactory.createEntityManager();
    Teacher teacher = entityManager.find(Teacher.class, 1L);
    System.out.println(teacher.getTname());
    System.out.println("------------------");
    System.out.println(teacher.getStudents());

  }

  @Test
  public void find2() throws Exception {
    persist();
    
    entityManager = entityManagerFactory.createEntityManager();
    Student student = entityManager.find(Student.class, 1L);
    System.out.println(student.getSname());
    System.out.println("------------------");
    System.out.println(student.getTeachers());

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
