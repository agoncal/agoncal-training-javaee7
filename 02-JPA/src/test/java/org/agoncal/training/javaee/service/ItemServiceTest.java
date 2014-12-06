package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.model.Book;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ItemServiceTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    private CdiContainer cdiContainer;
    private ItemService itemService;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @Before
    public void initBeanManager() {
        // this will give you a CdiContainer for Weld or OWB, depending on the jar you added
        cdiContainer = CdiContainerLoader.getCdiContainer();

        // now we gonna boot the CDI container. This will trigger the classpath scan, etc
        cdiContainer.boot();

        BeanManager beanManager = cdiContainer.getBeanManager();

        Set<Bean<?>> beans = beanManager.getBeans(ItemService.class);
        Bean<?> bean = beanManager.resolve(beans);

        itemService = (ItemService) beanManager.getReference(bean, ItemService.class, beanManager.createCreationalContext(bean));
    }

    @After
    public void closeBeanManager() {
        cdiContainer.shutdown();
    }

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateABook() {

        // Creates a Book
        Book book = itemService.createBook(new Book());

        // Checks the ISBN number has been generated
        assertTrue(book.getIsbn().startsWith("13-84356-"));
    }
}