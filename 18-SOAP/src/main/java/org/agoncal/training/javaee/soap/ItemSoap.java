package org.agoncal.training.javaee.soap;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.service.ItemService;
import org.agoncal.training.javaee.util.Loggable;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Loggable
@WebService
public class ItemSoap {

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

    @WebMethod(operationName = "GenerateNumber")
    public String generateNumber() {
        String number = itemService.generateNumber();
        logger.debug("Number generated" + number);
        return number;
    }

    @WebMethod(operationName = "CreateBook")
    public Book createBook(Book book) {
        Book created = itemService.createBook(book);
        return created;
    }

    @WebMethod(operationName = "FindBook")
    public Book findBook(@Max(10000) Long id) {
        return itemService.findBook(id);
    }

    @WebMethod(operationName = "RemoveBook")
    public void removeBook(Long id) {
        Book toDeleted = itemService.findBook(id);
        itemService.removeBook(toDeleted);
    }

    @WebMethod(operationName = "FindAllBooks")
    public List<Book> findAllBooks() {
        return itemService.findAllBooks();
    }
}
