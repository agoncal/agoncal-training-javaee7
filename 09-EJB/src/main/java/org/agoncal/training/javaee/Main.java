package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.*;
import org.agoncal.training.javaee.service.ItemService;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

    private static Logger logger = Logger.getLogger("org.agoncal.training.javaee6");

    public static void main(String[] args) throws Exception {

        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));

        EJBContainer ec = EJBContainer.createEJBContainer(properties);
        Context ctx = ec.getContext();

        // Looks up for the EJB
        ItemService itemService = (ItemService) ctx.lookup("java:global/classes/ItemService");

        // Creates an instance of book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);
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

        // Creates an instance of CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");
        // Tracks
        Track track1 = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");
        Track track2 = new Track("Fixing a Hole", 3.34f, "Beleive it or not, this song is about drugs");
        List<Track> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        cd.setTracks(tracks);

        // Persists the book to the database
        itemService.createBook(book);

        // Persists the CD to the database
        itemService.createCD(cd);

        // Finds all the items
        logger.info("##### All items");
        List<Item> items = itemService.findAllItems();
        for (Item oneItem : items) {
            logger.info("# " + oneItem);
        }

        // Finds all the CDs
        logger.info("##### All CDs");
        List<CD> cds = itemService.findAllCDs();
        for (CD oneCD : cds) {
            logger.info("# " + oneCD);
        }

        // Finds all the Books
        logger.info("##### All Books");
        List<Book> books = itemService.findAllBooks();
        for (Book oneBook : books) {
            logger.info("# " + oneBook);
        }

        ec.close();
    }
}


