package lis_andlisa.vk.com.decoder;

import org.junit.Test;

import static lis_andlisa.vk.com.decoder.MainActivity.shifr;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainActivityTest {

    @Test
    public void addition_isCorrect() {
        String text = "@string/large_text!/n/n/n\n\nfyfbb \r\t zddfbgc";
        String key = "key";
        String result = shifr(text.toCharArray(), key.toCharArray());
        System.out.println(result);
        assertEquals(text, shifr(result.toCharArray(), key.toCharArray()));
    }
}