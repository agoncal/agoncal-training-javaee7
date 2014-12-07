package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.model.Language;
import org.agoncal.training.javaee.service.ItemService;
import org.agoncal.training.javaee.service.MockGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class MainJPA {

    private static final Logger logger = LogManager.getLogger(MainJPA.class);

    private static String PERSISTENCE_UNIT_NAME = "trainingPU";

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        tx = em.getTransaction();

        ItemService service = new ItemService(em, new MockGenerator());

        // Creates a book
        Book book = new Book(4044L, "H2G2", 12.5F, "Best IT Scifi Book", 247, true, Language.ENGLISH);

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


