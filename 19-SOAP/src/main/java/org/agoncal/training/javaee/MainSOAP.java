package org.agoncal.training.javaee;

import org.agoncal.training.javaee.soap.Book;
import org.agoncal.training.javaee.soap.ItemSoap;
import org.agoncal.training.javaee.soap.ItemSoapService;
import org.agoncal.training.javaee.soap.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Vetoed;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Vetoed
public class MainSOAP {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static final Logger logger = LogManager.getLogger(MainSOAP.class);

    // ======================================
    // =          Business methods          =
    // ======================================

    public static void main(String[] args)
    {
        findAllBooks();
        createBook();
        findBookById("19");
        removeBookById("19");
    }

    private static void findAllBooks()
    {
        logger.info("#### findAllBooks");

        ItemSoap itemService = new ItemSoapService().getItemSoapPort();
        List<Book> books = itemService.findAllBooks();
        for (Book book : books) {
            logger.info(book.getTitle());
        }
    }

    private static void createBook()
    {
        logger.info("#### createBook");

        Book book = new Book();
        book.setTitle("Dummy Title");
        book.setPrice(29.99F);
        book.setDescription("Dummy Description");
        book.setIsbn("1430258489");
        book.setNbOfPage(240);
        book.setContentLanguage(Language.ITALIAN);

        ItemSoap itemService = new ItemSoapService().getItemSoapPort();
        Book result = itemService.createBook(book);
        logger.info(result.getId());
    }

    private static void findBookById(String id)
    {
        logger.info("#### findBookById");

        ItemSoap itemService = new ItemSoapService().getItemSoapPort();
        Book book = itemService.findBook(Long.valueOf(id));
        logger.info(book.getTitle());
    }

    private static void removeBookById(String id)
    {
        logger.info("#### removeBookById");

        ItemSoap itemService = new ItemSoapService().getItemSoapPort();
        itemService.removeBook(Long.valueOf(id));
    }
}


