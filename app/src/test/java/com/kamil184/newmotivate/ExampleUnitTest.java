package com.kamil184.newmotivate;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void shift_isCorrect() {
        Calendar calendar = new GregorianCalendar();
        int shift = calendar.getFirstDayOfWeek() - 0;
        System.out.println("shift: " + shift);

        boolean[] days = {false, true, true, true, true, true, false};
        boolean[] finalDays = {false, true, true, true, true, true, false};

        int lastI = 7;
        for (int i = 0; i < 7; i++) {
            if (i + shift < 7) {
                finalDays[i] = days[i + shift];
            } else {
                lastI = i;
                break;
            }
        }
        int temp = 0;
        for (int i = lastI; i < 7; i++) {
            finalDays[i] = days[temp++];
        }
        System.out.println(Arrays.toString(finalDays));

    }

    @Test
    public void te(){
        int proportion = 123;
        System.out.println(proportion / 100);
        System.out.println(proportion / 10 % 10);
        System.out.println(proportion % 10);
    }
}