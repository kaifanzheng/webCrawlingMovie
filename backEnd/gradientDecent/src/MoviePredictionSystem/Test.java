package MoviePredictionSystem;

import java.io.IOException;

public class Test {

    public static void testGetMovieType() throws IOException {

    }

    public static void main(String[] args) throws IOException {
        WebSpider testCast = new WebSpider();
        System.out.println(testCast.getMovieInfoByName("The Lord of the Rings","2001"));//tv series might be a problem
//        WebSpider testCast = new WebSpider();
//        testCast.getFilmRating("fsdfs");

    }

}
