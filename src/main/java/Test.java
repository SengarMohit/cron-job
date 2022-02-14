import parser.*;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("In the main method of the project ");

        Parser parser = new CronParser();
        try
        {
            List<String> outPutCron = parser.Parse("0/15 0 1,15 * 1-5 /usr/bin/find");
            for (String str : outPutCron)
            {
                System.out.println(str);
            }

            outPutCron = parser.Parse("* * * * * * /usr/bin/find");
            for (String str : outPutCron)
            {
                System.out.println(str);
            }

            outPutCron = parser.Parse("32 15 22 7 5 /usr/bin/find");
            for (String str : outPutCron)
            {
                System.out.println(str);
            }

        }
        catch (Exception e)
        {
            System.out.println("Exception occured " + e.getMessage().toString());
        }
    }
}
