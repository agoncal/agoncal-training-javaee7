package org.agoncal.training.javaee.rest;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.model.CD;
import org.agoncal.training.javaee.service.ItemService;
import org.agoncal.training.javaee.util.Loggable;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.validation.constraints.Max;
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
@Path("/items")
public class ItemRest {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private ItemService itemService;

    @Inject
    private Logger logger;

    // ======================================
    // =          Business methods          =
    // ======================================

    @GET
    public String generateNumber() {
        String number = itemService.generateNumber();
        logger.debug("Number generated" + number);
        return number;
    }

    @POST
    @Path("book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Book createBook(Book book) {
        Book created = itemService.createBook(book);
        return created;
    }

    @GET
    @Path("book/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book findBook(@PathParam("id") @Max(10000) Long id) {
        return itemService.findBook(id);
    }

    @DELETE
    @Path("book/{id}")
    public void removeBook(@PathParam("id") Long id) {
        Book toDeleted = itemService.findBook(id);
        itemService.removeBook(toDeleted);
    }

    @GET
    @Path("books")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> findAllBooks() {
        return itemService.findAllBooks();
    }

    @GET
    @Path("cd/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CD findCD(@PathParam("id") @Max(10000) Long id) {
        return itemService.findCD(id);
    }

    @GET
    @Path("cds")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<CD> findAllCDs() {
        return itemService.findAllCDs();
    }
}
