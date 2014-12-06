package org.agoncal.training.javaee;

import org.agoncal.training.javaee.service.ItemService;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class MainCDI {

    private static final Logger logger = LogManager.getLogger(MainCDI.class);

    public static void main(String[] args) {

        // this will give you a CdiContainer for Weld or OWB, depending on the jar you added
        CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();

        // now we gonna boot the CDI container. This will trigger the classpath scan, etc
        cdiContainer.boot();

        BeanManager beanManager = cdiContainer.getBeanManager();

        Set<Bean<?>> beans = beanManager.getBeans(ItemService.class);
        Bean<?> bean = beanManager.resolve(beans);

        ItemService ItemService = (ItemService) beanManager.getReference(bean, ItemService.class, beanManager.createCreationalContext(bean));

        logger.info("# " + ItemService.createBook());
        logger.info("# " + ItemService.createBook());

        // finally we gonna stop the container
        cdiContainer.shutdown();

    }
}


