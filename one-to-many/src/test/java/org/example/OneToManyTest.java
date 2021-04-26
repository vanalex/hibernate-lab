package org.example;

import org.example.entity.Author;
import org.example.entity.Book;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OneToManyTest {
    @Test
    public void testOneToOneBidirectionalWithMapsid() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager em = emf.createEntityManager();

        Author author = new Author();
        author.setName("Alicia Tom");
        author.setAge(38);
        author.setGenre("Anthology");

        Book book = new Book();
        book.setIsbn("001-AT");
        book.setTitle("The book of swords");

        Book book2 = new Book();
        book2.setIsbn("002-BA");
        book2.setTitle("The fantastic book");

        author.addBooks(book, book2); // use addBook() helper

        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();

        em.getTransaction().begin();
        author = em.find(Author.class, 1L);
        em.getTransaction().commit();
        assertNotNull(author);
        assertEquals(author.getBooks().size(), 2);
    }
}
