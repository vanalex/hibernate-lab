package org.example;

import org.example.entity.Author;
import org.example.entity.Book;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

import static org.example.TestFixture.buildBook;

public class OneToOneTest {

    @Test
    public void test() throws Exception {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();
        nativeQuery(em, "SHOW TABLES");
        nativeQuery(em, "SHOW COLUMNS from BOOK");
        nativeQuery(em, "SHOW COLUMNS from AUTHOR");
        persistEntity(emf, buildBook());
        Book book = findEntityById(emf, Book.class, 1L);
        System.out.println(book);
        Author author = book.getAuthor();
        System.out.println(author);

    }

    public static void nativeQuery(EntityManager em, String s) {
        System.out.printf("'%s'%n", s);
        Query query = em.createNativeQuery(s);
        List list = query.getResultList();
        for (Object o : list) {
            if (o instanceof Object[]) {
                System.out.println(Arrays.toString((Object[]) o));
            } else {
                System.out.println(o);
            }
        }
    }

    private static void persistEntity(EntityManagerFactory emf,
                                      Book book) throws Exception {
        System.out.println("-- Persisting entity --");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.close();
    }

    private <T> T findEntityById(EntityManagerFactory entityManagerFactory,
                                 Class<T> clazz,
                                 Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(clazz, id);
    }
}
