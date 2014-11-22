package org.agoncal.training.javaee7;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookTest.class,
        CDTest.class,
        ChapterTest.class,
        ItemTest.class,
        TrackTest.class
})
public class AllTests {
}
