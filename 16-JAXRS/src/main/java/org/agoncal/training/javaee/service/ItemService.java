package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.model.CD;
import org.agoncal.training.javaee.model.Item;
import org.agoncal.training.javaee.util.Loggable;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Loggable
@Stateless
@Path("/items")
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

    // ======================================
    // =          Business methods          =
    // ======================================

    @GET
    public String generateNumber() {
        String number = numberGenerator.generateNumber();
        logger.debug("Number generated" + number);
        return number;
    }

    public List<Item> findAllItems() {
        return em.createNamedQuery("findAllItems", Item.class).getResultList();
    }

    @POST
    @Path("book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Book createBook(Book book) {
        book.setIsbn(numberGenerator.generateNumber());
        em.persist(book);
        return book;
    }

    @GET
    @Path("book/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book findBook(@PathParam("id") @NotNull Long id) {
        return em.find(Book.class, id);
    }

    public void removeBook(Book book) {
        em.remove(em.merge(book));
    }

    @DELETE
    @Path("book/{id}")
    public void removeBook(@PathParam("id") Long id) {
        em.remove(em.find(Book.class, id));
    }

    public Book raiseBookPrice(@NotNull Long id, @Min(1) Float raise) {
        Book book = em.find(Book.class, id);
        if (book != null)
            book.setPrice(book.getPrice() + raise);
        return book;
    }

    @GET
    @Path("books")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> findAllBooks() {
        return em.createNamedQuery("findAllBooks", Book.class).getResultList();
    }

    public CD createCD(CD cd) {
        em.persist(cd);
        return cd;
    }

    @GET
    @Path("cd/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CD findCD(@PathParam("id") @NotNull Long id) {
        return em.find(CD.class, id);
    }

    public void removeCD(CD cd) {
        em.remove(em.merge(cd));
    }

    @GET
    @Path("cds")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<CD> findAllCDs() {
        return em.createNamedQuery("findAllCDs", CD.class).getResultList();
    }
}
