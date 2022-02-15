package parser;

import java.util.*;

public class CronParser implements  Parser{


    public CronParser()
    {
        daysSet = new LinkedHashSet<>();
        daysSet.add("SUN");
        daysSet.add("MON");
        daysSet.add("TUE");
        daysSet.add("WED");
        daysSet.add("THU");
        daysSet.add("FRI");
        daysSet.add("SAT");

        monthsSet = new LinkedHashSet<>();
        monthsSet.add("JAN");
        monthsSet.add("FEB");
        monthsSet.add("MAR");
        monthsSet.add("APR");
        monthsSet.add("MAY");
        monthsSet.add("JUNE");
        monthsSet.add("JULY");
        monthsSet.add("AUG");
        monthsSet.add("SEPT");
        monthsSet.add("OCT");
        monthsSet.add("NOV");
        monthsSet.add("DEC");
    }

    @Override
    public boolean isValid(String str) throws Exception
    {
        String[] newStr = str.split("\\s+");

        if (newStr.length == 6 || newStr.length == 7)
          return true;

        throw new IllegalArgumentException("Invalid Expression"); // Exception should be thrown
    }

    @Override
    public List<String> parse(String str) throws  Exception {

        List<String> list = new ArrayList<>();

        if (isValid(str)  != true)
            return list;

        System.out.println("Parsing the statement " + str );
        String[] newStr = str.split("\\s+"); // split based on spaces

//        for (int i = 0; i < newStr.length; i++) {
//            System.out.println(newStr[i]);
//        }

        String value = getMinuteString(newStr[0]);

        list.add(minute + "  " +  value);
        
        value = getHourString(newStr[1]);
        list.add(hour + "  "+ value);
        
        value = getDayOfMonth(newStr[2]);
        list.add(daysOfMonth +  " " + value);
        
        value = getMonthString(newStr[3]); 
        list.add(month + " " + value);
        
        value = getDayOfWeekString(newStr[4]); 
        list.add(daysOfWeek + "  " + value);
        
        if (newStr.length == 7)
        {
            value = getYearString(newStr[5]);
            list.add(year + " " +value);

            value = newStr[6];
            list.add(command + " " +value);
        }
        else {
            value = newStr[5];
            list.add(command + " " + value);
        }

        return list;
    }


    private String getMinuteString(String str)
    {
        return generateValues(str, numMinutes, false);
    }

    private String getHourString(String str)
    {
        return generateValues(str, numHours, false);
    }

    private String getDayOfMonth(String str)
    {
        return generateValues(str, numdaysOfMonth, true);
    }

    private String getMonthString(String str)
    {
        return generateValues(str, numMonths, true);
    }

    private String getDayOfWeekString(String str)
    {
        return generateValues(str, 7, false);
    }

    private String getYearString(String str)
    {
        String year = null;
        return year;
    }

    private String generateValues(String str, int numDays, boolean limitInclusive)
    {
        StringBuilder buffer = new StringBuilder();

        int findRange = str.indexOf("-");
        int index = str.indexOf(",");
        int findIncRange = str.indexOf("/");

        if ("*".equals(str))
        {
            if (limitInclusive)
            {
                for (int i = 1; i <= numDays; i++) {
                    buffer.append(Integer.toString(i) + " ");
                }
            }
            else {
                for (int i = 0; i < numDays; i++) {
                    buffer.append(Integer.toString(i) + " ");
                }
            }
        }
        else if (index != -1)
        {
            buffer.append(multipleValues(str));
        }

        else if (findRange != -1) {
            buffer.append(rangeValues(str, "-"));
        }
        else if (findIncRange != -1)
        {
            buffer.append(getIncrementString(str, numDays, limitInclusive));
        }
        else
        {
            buffer.append(str);
        }

        return buffer.toString().trim();
    }

    private String multipleValues(String str)
    {
        StringBuilder stringBuilder = new StringBuilder();

        String[] newStr = str.split(",");

        for (int i =0; i < newStr.length; i++)
        {
            stringBuilder.append( newStr[i] + " ");
        }

        return stringBuilder.toString();
    }

    private String rangeValues(String str, String delimeter) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] newStr = str.split("-");

        int lowIndex = Integer.parseInt(newStr[0]);
        int largerIndex = Integer.parseInt(newStr[1]);

        for (int i =lowIndex; i <= largerIndex; i++)
        {
            stringBuilder.append(Integer.toString(i) + " ");
        }

        return stringBuilder.toString();

    }

    private String getIncrementString(String str, int upperLimit,
           boolean limitsInclusive)
    {
        StringBuilder stringBuilder = new StringBuilder();
        String[] newStr = str.split("/");

        int lowIndex = Integer.parseInt(newStr[0]);
        int incrementValue = Integer.parseInt(newStr[1]);

        if (limitsInclusive) {
            for (int i = lowIndex; i <= upperLimit; i += incrementValue) {
                stringBuilder.append(Integer.toString(i) + " ");
            }
        }
        else
        {
            for (int i = lowIndex; i < upperLimit; i += incrementValue) {
                stringBuilder.append(Integer.toString(i) + " ");
            }
        }

        return stringBuilder.toString();

    }

    final int numMinutes = 60;
    final int numdaysOfMonth = 31;
    final int numMonths = 12;
    final int numDaysInWeek = 7;
    final int numHours = 24;
    
    final String minute = "minute"; 
    final String hour = "hour";
    final String daysOfMonth = "daysOfMonth"; 
    final String daysOfWeek = "daysOfWeek";
    final String month = "month"; 
    final String year = "year"; 
    final String command = "command"; 

    HashSet<String> daysSet;
    HashSet<String> monthsSet;
}