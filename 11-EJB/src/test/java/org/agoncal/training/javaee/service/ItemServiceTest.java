package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.model.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ItemServiceTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static EJBContainer ec;
    private static Context ctx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initEJBContainer() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File[]{new File("target/classes"), new File("target/test-classes")});
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();
    }

    @AfterClass
    public static void closeContainer() {
        if (ec != null) {
            ec.close();
        }
    }

    // ======================================
    // =             Unit tests             =
    // ======================================

    @Test
    public void shouldGenerateANumber() throws NamingException {

        // Looks up for the ItemService
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Generates a number
        String number = itemService.generateNumber();

        // Checks the ISBN number has been generated
        assertTrue(number.startsWith("13-84356-"));
    }

    @Test
    public void shouldCreateABook() throws Exception {

        // Looks up for the ItemService
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists a book
        book = itemService.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Finds the book by primary key
        book = itemService.findBook(book.getId());
        assertEquals(book.getTitle(), "H2G2");

        // Deletes the book
        itemService.removeBook(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", itemService.findBook(book.getId()));
    }

    @Test
    public void shouldFindAllBooks() throws Exception {

        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Finds all books
        int initialNumberOfBooks = itemService.findAllBooks().size();

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        book = itemService.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Finds all books
        assertEquals("Should have one extra book", initialNumberOfBooks + 1, itemService.findAllBooks().size());

        // Deletes the book
        itemService.removeBook(book);

        // Finds all books
        assertEquals("Should have initial number of books", initialNumberOfBooks, itemService.findAllBooks().size());
    }

    @Test
    public void shouldCreateABookWithTags() throws Exception {

        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Creates a book with tags
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);
        List<String> tags = new ArrayList<>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Persists the book
        book = itemService.createBook(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = itemService.findBook(id);
        assertEquals(book.getTitle(), "H2G2");

        // Checks the number of tags
        assertEquals(book.getTags().size(), 2);

        // Deletes the book
        itemService.removeBook(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", itemService.findBook(id));
    }

    @Test
    public void shouldCreateABookWithChapters() throws Exception {

        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

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
        book = itemService.createBook(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = itemService.findBook(id);
        assertEquals(book.getTitle(), "H2G2");

        // Checks the number of tags
        assertEquals(book.getTags().size(), 2);

        // Checks the number of chapters
        assertEquals(book.getChapters().size(), 2);

        // Deletes the book
        itemService.removeBook(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", itemService.findBook(id));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateABookWithANullTitle() throws Exception {

        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Creates a book with null title
        Book book = new Book(null, 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Persists the book
        itemService.createBook(book);
    }

    @Test
    public void shouldCreateACD() throws Exception {
        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Counts all the cds in the database
        int nbCDs = itemService.findAllCDs().size();

        // Creates a cd
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the cd to the database
        cd = itemService.createCD(cd);
        assertNotNull("ID should not be null", cd.getId());

        // Finds the cd by primary key
        cd = itemService.findCD(cd.getId());
        assertEquals(cd.getTitle(), "St Pepper");

        // Checks that there is an extra cd in the database
        assertEquals("Should have an extra cd", itemService.findAllCDs().size(), nbCDs + 1);

        // Removes the cd
        itemService.removeCD(cd);

        // Checks that the extra cd has been removed
        assertEquals("Should have got rid of the extra cd", itemService.findAllCDs().size(), nbCDs);
    }

    @Test
    public void shouldFindAllCDs() throws Exception {

        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Finds all books
        int initialNumberOfCDs = itemService.findAllCDs().size();

        // Creates a cd
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the cd
        cd = itemService.createCD(cd);
        assertNotNull("ID should not be null", cd.getId());

        // Finds all cds
        assertEquals("Should have one extra cd", initialNumberOfCDs + 1, itemService.findAllCDs().size());

        // Deletes the cd
        itemService.removeCD(cd);

        // Finds all cds
        assertEquals("Should have initial number of cds", initialNumberOfCDs, itemService.findAllCDs().size());
    }

    @Test
    public void shouldCreateACDWithTracks() throws Exception {
        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Creates a CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Tracks
        Track track1 = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");
        Track track2 = new Track("Fixing a Hole", 3.34f, "Beleive it or not, this song is about drugs");
        List<Track> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        cd.setTracks(tracks);

        // Persists the cd to the database
        cd = itemService.createCD(cd);
        assertNotNull("ID should not be null", cd.getId());
        Long id = cd.getId();

        // Finds the CD by primary key
        cd = itemService.findCD(id);
        assertEquals(cd.getTitle(), "St Pepper");

        // Checks the number of tracks
        assertEquals(cd.getTracks().size(), 2);

        // Removes the cd
        itemService.removeCD(cd);

        // Checks that the extra cd has been removed
        assertNull("CD should has been deleted", itemService.findCD(id));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateACDWithANullTitle() throws Exception {
        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Creates a CD
        CD cd = new CD(null, 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the cd to the database
        itemService.createCD(cd);
    }
}
