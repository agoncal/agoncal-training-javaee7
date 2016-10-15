package org.agoncal.training.javaee.soap;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.service.ItemService;
import org.agoncal.training.javaee.util.Loggable;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.jws.*;
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
// TODO Change the WS* annotation and generate the WSDL with mvn clean compile jaxws:wsgen
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
    @WebResult(name = "GeneratedNumber")
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
    @Oneway
    public void removeBook(@WebParam(name = "BookID") Long id) {
        Book toDeleted = itemService.findBook(id);
        itemService.removeBook(toDeleted);
    }

    @WebMethod(operationName = "FindAllBooks")
    @WebResult(name = "Books")
    public List<Book> findAllBooks() {
        return itemService.findAllBooks();
    }
}
