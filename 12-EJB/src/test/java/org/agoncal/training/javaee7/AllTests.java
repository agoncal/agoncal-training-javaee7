package org.agoncal.training.javaee7;

import org.agoncal.training.javaee7.service.IsbnGeneratorTest;
import org.agoncal.training.javaee7.service.ItemEJBTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ItemEJBTest.class,
        IsbnGeneratorTest.class
})
public class AllTests {
}
