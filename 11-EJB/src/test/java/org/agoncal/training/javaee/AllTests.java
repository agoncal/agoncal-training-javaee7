package org.agoncal.training.javaee;

import org.agoncal.training.javaee.service.IsbnGenerator;
import org.agoncal.training.javaee.service.ItemServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ItemServiceTest.class,
        IsbnGenerator.class
})
public class AllTests {
}
