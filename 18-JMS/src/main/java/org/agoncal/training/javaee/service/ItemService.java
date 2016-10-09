package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.model.CD;
import org.agoncal.training.javaee.model.Item;
import org.agoncal.training.javaee.util.Loggable;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Loggable
@Stateless
public class ItemService {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    @Inject
    private EntityManager em;

    @Inject
    private Logger logger;

    @Resource
    private ConnectionFactory factory;

    @Resource
    private Destination queue;

    // ======================================
    // =          Business methods          =
    // ======================================

    public String generateNumber() {
        String number = numberGenerator.generateNumber();
        logger.debug("Number generated" + number);
        return number;
    }

    public List<Item> findAllItems() {
        return em.createNamedQuery("findAllItems", Item.class).getResultList();
    }

    public Book createBook(Book book) {
        book.setIsbn(numberGenerator.generateNumber());
        em.persist(book);
        factory.createContext().createProducer().send(queue, book);
        return book;
    }

    public Book findBook(@NotNull Long id) {
        return em.find(Book.class, id);
    }

    public void removeBook(Book book) {
        em.remove(em.merge(book));
    }

    public Book raiseBookPrice(@NotNull Long id, @Min(1) Float raise) {
        Book book = em.find(Book.class, id);
        if (book != null)
            book.setPrice(book.getPrice() + raise);
        return book;
    }

    public List<Book> findAllBooks() {
        return em.createNamedQuery("findAllBooks", Book.class).getResultList();
    }

    public CD createCD(CD cd) {
        em.persist(cd);
        return cd;
    }

    public CD findCD(@NotNull Long id) {
        return em.find(CD.class, id);
    }

    public void removeCD(CD cd) {
        em.remove(em.merge(cd));
    }

    public List<CD> findAllCDs() {
        return em.createNamedQuery("findAllCDs", CD.class).getResultList();
    }
}
