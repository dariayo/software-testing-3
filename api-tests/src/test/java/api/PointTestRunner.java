package api;

import com.intuit.karate.junit5.Karate;

public class PointTestRunner {
    @Karate.Test
    Karate testPoints() {
        return Karate.run("classpath:points.feature").relativeTo(getClass());
    }
}
