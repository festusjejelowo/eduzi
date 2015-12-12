package com.ocularminds.eduzi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ocularminds.eduzi.vao.Feed;

public class DbFactory {

  private static final String PERSISTENCE_UNIT_NAME = "EduziPU";
  private static EntityManagerFactory factory;

  public static DbFactory newInstance() {

	  return null;

   /* factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    Query q = em.createQuery("select t from Todo t");
    List<Todo> todoList = q.getResultList();
    for (Todo todo : todoList) {
      System.out.println(todo);
    }
    System.out.println("Size: " + todoList.size());

    // Create new todo
    em.getTransaction().begin();
    Todo todo = new Todo();
    todo.setSummary("This is a test");
    todo.setDescription("This is a test");
    em.persist(todo);
    em.getTransaction().commit();

    em.close();
  }


  	/**
  	Properties props = new Properties();
  	props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
  	props.setProperty("dataSource.user", "test");
  	props.setProperty("dataSource.password", "test");
  	props.setProperty("dataSource.databaseName", "mydb");
  	props.put("dataSource.logWriter", new PrintWriter(System.out));

  	HikariConfig config = new HikariConfig(props);
      HikariDataSource ds = new HikariDataSource(config);
    */
}
}