package org.agoncal.training.javaee.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class BookTest extends AbstractPersistentTest {

    // ======================================
    // =             Unit tests             =
    // ======================================

    @Test
    public void shouldCreateABook() {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
        Long id = book.getId();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals("H2G2", book.getTitle());

        // Updates the book
        tx.begin();
        book.setTitle("Hitchhiker's Guide");
        tx.commit();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals("Hitchhiker's Guide", book.getTitle());

        // Deletes the book
        tx.begin();
        em.remove(book);
        tx.commit();

        // Checks the book has been deleted
        assertNull("Book should has been deleted", em.find(Book.class, id));
    }

    @Test
    public void shouldCreateABookWithTags() {

        // Creates a book with tags
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);
        List<String> tags = new ArrayList<>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
        Long id = book.getId();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals("H2G2", book.getTitle());

        // Checks the number of tags
        assertEquals(2, book.getTags().size());

        // Deletes the book
        tx.begin();
        em.remove(book);
        tx.commit();

        // Checks the book has been deleted
        assertNull("Book should has been deleted", em.find(Book.class, id));
    }

    @Test
    public void shouldCreateABookWithChapters() {

        // Creates a book with tags
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);
        List<String> tags = new ArrayList<>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Adds chapters to the book
        Chapter chapter1 = new Chapter("Arriving on earth", "blah blah blah blah blah");
        Chapter chapter2 = new Chapter("Restaurant of the universe", "Forty two");
        List<Chapter> chapters = new ArrayList<>();
        chapters.add(chapter1);
        chapters.add(chapter2);
        book.setChapters(chapters);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
        Long id = book.getId();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals("H2G2", book.getTitle());

        // Checks the number of tags
        assertEquals(2, book.getTags().size());

        // Finds the chapter by primary key
        chapter1 = em.find(Chapter.class, chapter1.getId());
        assertEquals("Arriving on earth", chapter1.getTitle());

        // Checks the number of chapters
        assertEquals(2, book.getChapters().size());

        // Deletes the book
        tx.begin();
        em.remove(book);
        tx.commit();

        // Checks the book has been deleted
        assertNull("Book should has been deleted", em.find(Book.class, id));
        assertNull("Chapter should has been deleted", em.find(Chapter.class, chapter1.getId()));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateABookWithANullTitle() {

        // Creates a book with null title
        Book book = new Book(null, 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
    }

    @Test
    public void shouldUpdateTheBookLanguage() {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();
        Long id = book.getId();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(Language.ENGLISH, book.getContentLanguage());

        // Updates the book
        tx.begin();
        book.setContentLanguage(Language.FRENCH);
        tx.commit();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(Language.FRENCH, book.getContentLanguage());

        // Deletes the book
        tx.begin();
        em.remove(book);
        tx.commit();

        assertNull("Book should has been deleted", em.find(Book.class, id));
    }
}