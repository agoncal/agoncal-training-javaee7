package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.*;
import org.agoncal.training.javaee.service.ItemService;
import org.agoncal.training.javaee.service.MockGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Vetoed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Vetoed
public class MainJPA {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static final Logger logger = LogManager.getLogger(MainJPA.class);

    private static String PERSISTENCE_UNIT_NAME = "trainingPU";

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    // ======================================
    // =          Business methods          =
    // ======================================

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Gets an Entity Manager
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        tx = em.getTransaction();

        // Creates an ItemService
        ItemService service = new ItemService(em, new MockGenerator());

        // Creates a book
        Book book = new Book(4044L, "H2G2", 12.5F, "Best IT Scifi Book", 247, true, Language.ENGLISH);
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
        tx.begin();
        book = service.createBook(book);
        tx.commit();
        logger.info("Book Persisted : " + book);

        // Finds the book by primary key
        book = service.findBook(4044L);
        logger.info("Book Found     : " + book);

        // Updates the book
        tx.begin();
        book.setTitle("Hitchhiker's Guide");
        tx.commit();
        logger.info("Book Updated   : " + book);

        // Updates the price
        tx.begin();
        book = service.raiseBookPrice(4044L, 100.50F);
        tx.commit();
        logger.info("Price Raised   : " + book);

        // Finds the book by primary key
        book = service.findBook(4044L);
        logger.info("Book Found     : " + book);

        // Deletes the book
        tx.begin();
        service.removeBook(book);
        tx.commit();
        logger.info("Book Removed");

        // Finds the book by primary key
        book = service.findBook(4044L);
        logger.info("Book Not Found : " + book);


        // Creates a CD
        CD cd = new CD(2022L, "St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");
        // Tracks
        Track track1 = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");
        Track track2 = new Track("Fixing a Hole", 3.34f, "Beleive it or not, this song is about drugs");
        List<Track> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        cd.setTracks(tracks);

        // Persists the cd
        tx.begin();
        cd = service.createCD(cd);
        tx.commit();
        logger.info("CD Persisted : " + cd);

        // Finds the cd by primary key
        cd = service.findCD(2022L);
        logger.info("CD Found     : " + cd);

        // Updates the cd
        tx.begin();
        cd.setTitle("Help");
        tx.commit();
        logger.info("CD Updated   : " + cd);

        // Finds the cd by primary key
        cd = service.findCD(2022L);
        logger.info("CD Found     : " + cd);

        // Deletes the cd
        tx.begin();
        service.removeCD(cd);
        tx.commit();
        logger.info("CD Removed");

        // Finds the cd by primary key
        cd = service.findCD(2022L);
        logger.info("CD Not Found : " + cd);

        em.close();
        emf.close();
    }
}


