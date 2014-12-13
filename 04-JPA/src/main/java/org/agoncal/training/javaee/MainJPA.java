package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.model.Chapter;
import org.agoncal.training.javaee.model.Language;
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

        em.close();
        emf.close();
    }
}


