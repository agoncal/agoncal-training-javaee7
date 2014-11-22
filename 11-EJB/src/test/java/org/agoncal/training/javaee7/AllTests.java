package org.agoncal.training.javaee7;

import org.agoncal.training.javaee7.service.IsbnGenerator;
import org.agoncal.training.javaee7.service.ItemEJBTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ItemEJBTest.class,
        IsbnGenerator.class
})
public class AllTests {
}
