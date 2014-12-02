package org.agoncal.training.javaee;

import org.agoncal.training.javaee.service.ItemService;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;

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

    public static void main(String[] args) {

        System.out.println("\n\n>>> Executing : " + MainCDI.class.toString() + " <<<\n");

        // this will give you a CdiContainer for Weld or OWB, depending on the jar you added
        CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();

        // now we gonna boot the CDI container. This will trigger the classpath scan, etc
        cdiContainer.boot();

        BeanManager beanManager = cdiContainer.getBeanManager();

        Set<Bean<?>> beans = beanManager.getBeans(ItemService.class);
        Bean<?> bean = beanManager.resolve(beans);

        ItemService ItemService = (ItemService) beanManager.getReference(bean, ItemService.class, beanManager.createCreationalContext(bean));


        System.out.println("# " + ItemService.createBook());
        System.out.println("# " + ItemService.createBook());

        // finally we gonna stop the container
        cdiContainer.shutdown();

    }
}


