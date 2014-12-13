package org.agoncal.training.javaee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    // =             Attributes             =
    // ======================================

    private static ValidatorFactory vf;
    private static Validator validator;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initValidator() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void closeContainer() {
        if (vf != null) {
            vf.close();
        }
    }

    // ======================================
    // =             Unit tests             =
    // ======================================

    @Test
    public void shouldRaiseNoConstraintViolation() {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Validate the book
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        assertEquals(0, constraints.size());
    }

    @Test
    public void shouldRaiseConstraintsViolation() {

        // Creates a book
        Book book = new Book();

        // Validate the book
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        assertEquals(1, constraints.size());
    }

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
    public void shouldFindAllBooks() {

        // Finds all books
        assertEquals("Should have no book", 0, em.createNamedQuery("findAllBooks", Book.class).getResultList().size());

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        tx.begin();
        em.persist(book);
        tx.commit();

        // Finds all books
        assertEquals("Should have one book", 1, em.createNamedQuery("findAllBooks", Book.class).getResultList().size());

        // Deletes the book
        tx.begin();
        em.remove(book);
        tx.commit();

        // Finds all books
        assertEquals("Should have no book", 0, em.createNamedQuery("findAllBooks", Book.class).getResultList().size());
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