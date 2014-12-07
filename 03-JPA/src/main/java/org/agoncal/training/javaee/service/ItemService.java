package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.util.Loggable;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Loggable
public class ItemService {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    private EntityManager em;

    @Inject
    private Logger logger;

    // ======================================
    // =            Constructors            =
    // ======================================

    public ItemService() {
    }

    public ItemService(EntityManager em, NumberGenerator numberGenerator) {
        this.em = em;
        this.numberGenerator = numberGenerator;
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public String generateNumber() {
        String number = numberGenerator.generateNumber();
        logger.debug("Number generated" + number);
        return number;
    }

    public Book createBook(Book book) {
        book.setIsbn(numberGenerator.generateNumber());
        em.persist(book);
        return book;
    }

    public Book findBook(Long id) {
        return em.find(Book.class, id);
    }

    public void removeBook(Book book) {
        em.remove(em.merge(book));
    }

    public Book raiseBookPrice(Long id, Float raise) {
        Book book = em.find(Book.class, id);
        if (book != null)
            book.setPrice(book.getPrice() + raise);
        return book;
    }
}
