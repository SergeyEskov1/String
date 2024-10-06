import java.util.Scanner;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);

        String exp = scn.nextLine();
        exp = exp.replace("\"", "");
        exp = exp.replace(" ", "");
        String[] data;
        char action;
        if (exp.contains("+")) {
            data = exp.split("\\+");
            action = '+';
        } else if (exp.contains("-")) {
            data = exp.split("-");
            action = '-';
        } else if (exp.contains("*")) {
            data = exp.split("\\*");
            action = '*';
        } else if (exp.contains("/")) {
            data = exp.split("/");
            action = '/';
        } else {
            throw new Exception("Некорректный знак дейстивия");
        }

        if(isNumeric(data[0]))
            throw new Exception("Первый аргумент должен быть строкой");

        if(isStringIsOutOfBounds(data[0]))
            throw new Exception("Первый аргумент должен быть не более 10 символов");

        if(isNumeric(data[1]))
        {
            int numeric = Integer.parseInt(data[1]);
            if(isNumericIsOutOfBounds(numeric))
                throw new Exception("Второй аргумент должен быть не более 10");
        }
        else
        {
            if(isStringIsOutOfBounds(data[1]))
                throw new Exception("Второй аргумент должен быть не более 10 символов");
        }

        if (action == '*' || action == '/') {
            try
            {
                Integer.parseInt(data[1]);
            }
            catch(Exception ex)
            {
                throw new Exception("Строчку можно делить или умножать на число");
            }
        }

        String result = "";
        if (action == '+') {
            result = sumString(data[0], data[1]);
        } else if (action == '/'){
            result = divideString(data[0], data[1]);
        }
        else if (action == '*') {
            result = multiplyString(data[0], data[1]);
        } else if (action == '-') {
            result = takeItAwayString(data[0], data[1]);
        }


        printInQuote(result);
    }
    public static String takeItAwayString(String firstStr, String secondStr) {
        String takeItAwayresult = "";
        int index = firstStr.indexOf(secondStr);
        if (index == -1) {
            printInQuote(firstStr);
        }else{
            takeItAwayresult = firstStr.substring(0, index);
            takeItAwayresult +=firstStr.substring(index+secondStr.length());
        }


        return takeItAwayresult;
    }
    public static String divideString(String firstStr, String secondStr) {

        String divideResult = "";
        int newLen = firstStr.length()/Integer.parseInt(secondStr);
        divideResult = firstStr.substring(0, newLen);
        return divideResult;
    }

    public static String sumString(String firstStr,String secondStr)
    {
        return firstStr + secondStr;
    }

    public static String multiplyString(String firstStr, String secondStr)
    {
        int multiplier = Integer.parseInt(secondStr);
        String multiplyResult = "";
        for (int i = 0; i < multiplier; i++) {
            multiplyResult += firstStr;

        }
        return multiplyResult;
    }

    public static void printInQuote(String text)
    {
        String result = trimString(text,41);
        System.out.println("\""+result+"\"");
    }

    public static String trimString(String text, int count) {
        String result = text;
        if (result.length() >= count) {
            result = result.substring(0, count - 1);
            result += "...";
        }
        return result;
    }

    public static boolean isNumeric(String input){
        Pattern numberPattern = Pattern.compile("^[-+]?\\d+(\\.\\d+)?$");

        if (numberPattern.matcher(input).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumericIsOutOfBounds(int input)
    {
        return input > 10;
    }

    public static boolean isStringIsOutOfBounds(String input)
    {
        return input.length() > 10;
    }
}
