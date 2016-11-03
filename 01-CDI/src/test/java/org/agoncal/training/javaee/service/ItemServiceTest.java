package org.agoncal.training.javaee.service;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.junit.*;

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

    private static CdiContainer cdiContainer;
    private static ItemService itemService;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initBeanManager() {
        // this will give you a CdiContainer for Weld or OWB, depending on the jar you added
        cdiContainer = CdiContainerLoader.getCdiContainer();

        // now we gonna boot the CDI container. This will trigger the classpath scan, etc
        cdiContainer.boot();

        BeanManager beanManager = cdiContainer.getBeanManager();

        Set<Bean<?>> beans = beanManager.getBeans(ItemService.class);
        Bean<?> bean = beanManager.resolve(beans);

        itemService = (ItemService) beanManager.getReference(bean, ItemService.class, beanManager.createCreationalContext(bean));
    }

    @AfterClass
    public static void closeBeanManager() {
        cdiContainer.shutdown();
    }

    // ======================================
    // =             Unit tests             =
    // ======================================

    @Test
    public void shouldGenerateANumber() {

        // Generates a number
        String number = itemService.generateNumber();

        // Checks the ISBN number has been generated
        assertTrue(number.startsWith("13-84356-"));
    }
}
