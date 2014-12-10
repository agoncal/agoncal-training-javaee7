package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.*;
import org.agoncal.training.javaee.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class MainEJB {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static final Logger logger = LogManager.getLogger(MainEJB.class);

    private static EJBContainer ec;
    private static Context ctx;

    // ======================================
    // =          Business methods          =
    // ======================================

    public static void main(String[] args) throws Exception {

        // Gets an EEJBContainer
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();

        // Looks up for the ItemService
        ItemService service = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Creates a book
        Book book = new Book("H2G2", 12.5F, "Best IT Scifi Book", 247, true, Language.ENGLISH);
        // Tags
        List<String> tags = new ArrayList<>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);
        // Chapters
        Chapter chapter1 = new Chapter("Arriving on earth", "blah blah blah blah blah");
        Chapter chapter2 = new Chapter("Restaurant of the universe", "Forty two");
        List<Chapter> chapters = new ArrayList<>();
        chapters.add(chapter1);
        chapters.add(chapter2);
        book.setChapters(chapters);

        // Persists the book
        service.createBook(book);

        // Creates a CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");
        // Tracks
        Track track1 = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");
        Track track2 = new Track("Fixing a Hole", 3.34f, "Beleive it or not, this song is about drugs");
        List<Track> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        cd.setTracks(tracks);

        // Persists the cd
        service.createCD(cd);

        // Finds all the items
        logger.info("##### All items");
        List<Item> items = service.findAllItems();
        for (Item oneItem : items) {
            logger.info("# " + oneItem);
        }

        // Finds all the CDs
        logger.info("##### All CDs");
        List<CD> cds = service.findAllCDs();
        for (CD oneCD : cds) {
            logger.info("# " + oneCD);
        }

        // Finds all the Books
        logger.info("##### All Books");
        List<Book> books = service.findAllBooks();
        for (Book oneBook : books) {
            logger.info("# " + oneBook);
        }

        ec.close();
    }
}


