package info.juanmendez.android.reviewservices;

import junit.framework.Assert;

import org.junit.Test;

import io.reactivex.Single;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void singleTest() throws Exception {

        int febCount = 5;
        Single.<String>create(e -> {
            String result = "";

            int[] feb = new int[febCount];
            feb[0] = 0;
            feb[1] = 1;
            for(int i=2; i < febCount; i++){
                feb[i] = feb[i-1] + feb[i-2];
            }

            for(int i=0; i< febCount; i++) {
                result += feb[i] + " ";
            }

            e.onSuccess( result );

        }).subscribe(s -> {

            Assert.assertEquals("Value returned is", s, "0 1 1 2 3 ");
        } );
    }
}