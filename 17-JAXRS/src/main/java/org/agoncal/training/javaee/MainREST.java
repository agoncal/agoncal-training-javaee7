package org.agoncal.training.javaee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Vetoed;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Vetoed
public class MainREST {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static final Logger logger = LogManager.getLogger(MainREST.class);
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

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURL).path("rest").path("items").path("books");
        logger.info(target.getUri().toString());

        Response response = target.request(MediaType.APPLICATION_JSON).get();
        logger.info(response.getStatus());
        logger.info(response.readEntity(String.class));
    }

    private static void createBook()
    {
        logger.info("#### createBook");

        JsonObject json = Json.createObjectBuilder()
                .add("title", "Dummy Title")
                .add("price", "29.99")
                .add("description", "Dummy Description")
                .add("isbn", "1430258489")
                .add("nbOfPages", "240")
                .add("publicationDate", "2013-06-26")
                .add("contentLanguage", "ITALIAN")
                .build();

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURL).path("rest").path("items").path("book");
        logger.info(target.getUri().toString());

        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(json.toString()));
        logger.info(response.getStatus());
        logger.info(response.readEntity(String.class));
    }

    private static void findBookById(String id)
    {
        logger.info("#### findBookById");

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURL).path("rest").path("items").path("book").path(id);
        logger.info(target.getUri().toString());

        Response response = target.request(MediaType.APPLICATION_JSON).get();
        logger.info(response.getStatus());
        logger.info(response.readEntity(String.class));
    }

    private static void removeBookById(String id)
    {
        logger.info("#### removeBookById");

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURL).path("rest").path("items").path("book").path(id);
        logger.info(target.getUri().toString());

        Response response = target.request(MediaType.APPLICATION_JSON).delete();
        logger.info(response.getStatus());
    }
}


