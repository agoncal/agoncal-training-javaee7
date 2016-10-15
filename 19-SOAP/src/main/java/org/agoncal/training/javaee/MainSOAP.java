package org.agoncal.training.javaee;

import localhost._8080.cdbookstore.Book;
import localhost._8080.cdbookstore.ItemSoap;
import localhost._8080.cdbookstore.ItemSoapService;
import localhost._8080.cdbookstore.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Vetoed;

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
    private static final String baseURL = "http://localhost:8080/cdbookstore";


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
        logger.info(itemService.findAllBooks());
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
        logger.info(itemService.createBook(book));
    }

    private static void findBookById(String id)
    {
        logger.info("#### findBookById");

        ItemSoap itemService = new ItemSoapService().getItemSoapPort();
        logger.info(itemService.findBook(Long.valueOf(id)));
    }

    private static void removeBookById(String id)
    {
        logger.info("#### removeBookById");

        ItemSoap itemService = new ItemSoapService().getItemSoapPort();
        itemService.removeBook(Long.valueOf(id));
    }
}


