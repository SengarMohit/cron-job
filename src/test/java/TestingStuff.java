import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import parser.CronParser;
import parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class TestingStuff {

    static Parser parser;

    @BeforeClass
    static public void initParser(){
        System.out.println("Initialising parser");
        parser= new CronParser();// Mockito.spy(new CronParser());
    }

    @Test
    public void hello(){
        Assert.assertTrue(true);
    }


    @Test
    public void TestExactValues() throws Exception {

        List<String> expectedOutPut = new ArrayList<>();

        expectedOutPut.add("minute  32");
        expectedOutPut.add("hour  15");
        expectedOutPut.add("daysOfMonth 22");
        expectedOutPut.add("month 7");
        expectedOutPut.add("daysOfWeek  5");
        expectedOutPut.add("command /usr/bin/find");

        List<String> outPutCron = parser.parse("32 15 22 7 5 /usr/bin/find");

        Assert.assertEquals( expectedOutPut, outPutCron);
    }

    @Test
    public void TestRangeValues() throws Exception {

        List<String> expectedOutPut = new ArrayList<>();

        expectedOutPut.add("minute  0 15 30 45");
        expectedOutPut.add("hour  0");
        expectedOutPut.add("daysOfMonth 1 15");
        expectedOutPut.add("month 1 2 3 4 5 6 7 8 9 10 11 12");
        expectedOutPut.add("daysOfWeek  1 2 3 4 5");
        expectedOutPut.add("command /usr/bin/find");


        List<String> outPutCron = parser.parse("0/15 0 1,15 * 1-5 /usr/bin/find");

        Assert.assertEquals( expectedOutPut, outPutCron);
    }

    @Test
    public void TestAllValues() throws Exception {

        List<String> expectedOutPut = new ArrayList<>();

        expectedOutPut.add("minute  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59");
        expectedOutPut.add("hour  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23");
        expectedOutPut.add("daysOfMonth 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31");
        expectedOutPut.add("month 1 2 3 4 5 6 7 8 9 10 11 12");
        expectedOutPut.add("daysOfWeek  0 1 2 3 4 5 6");
        expectedOutPut.add("year null");
        expectedOutPut.add("command /usr/bin/find");

        List<String> outPutCron = parser.parse("* * * * * * /usr/bin/find");
        Assert.assertEquals( expectedOutPut, outPutCron);
    }


    @Test
    public void TestExactNegativeValues() throws Exception {

        List<String> expectedOutPut = new ArrayList<>();

        expectedOutPut.add("minute  32");
        expectedOutPut.add("hour  15");
        expectedOutPut.add("daysOfMonth 22");
        expectedOutPut.add("month 7");
        expectedOutPut.add("daysOfWeek  5");
        expectedOutPut.add("year null");
        expectedOutPut.add("command /usr/bin/find");

        List<String> outPutCron = parser.parse("32 15 22 7 5 /usr/bin/find");

        Assert.assertNotEquals( expectedOutPut, outPutCron);
    }

    @Test(expected =  IllegalArgumentException.class)
    public void TestException() throws Exception {

        List<String> expectedOutPut = new ArrayList<>();

        expectedOutPut.add("minute  32");
        expectedOutPut.add("hour  15");
        expectedOutPut.add("daysOfMonth 22");
        expectedOutPut.add("month 7");
        expectedOutPut.add("daysOfWeek  5");
        expectedOutPut.add("year null");
        expectedOutPut.add("command /usr/bin/find");

        parser.parse("32 15 22 /usr/bin/find");
    }




}
