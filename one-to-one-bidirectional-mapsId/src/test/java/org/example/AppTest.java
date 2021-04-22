package org.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.example.entity.Post;
import org.example.entity.PostDetails;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppTest {
    @Test
    public void testOneToOneBidirectionalWithMapsid() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager em = emf.createEntityManager();
        Post post = new Post("First post");
        PostDetails details = new PostDetails("John Doe");
        post.setDetails(details);
        em.persist(post);

        post = em.find(Post.class, 1L);

        assertNotNull(post);

        post.setDetails(null);
    }
}
