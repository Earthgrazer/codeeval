import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TextDollar {

    private static final String TEENS[] = new String[]{
            "Eleven",
            "Twelve",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Nineteen"
    };

    private static final String TENS[] = new String[]{
            "Ten",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };

    private static final String ONES[] = new String[]{
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine"
    };

    public static void main(String[] args) throws java.lang.Exception {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            printDollar(Integer.parseInt(line));
        }

        // required for CodeEval
        System.exit(0);
    }

    private static void printDollar(int amount)
    {
        int hundreds = amount - amount / 1000 * 1000;
        int thousands = (amount - (amount / 1000000 * 1000000) - hundreds) / 1000;
        int millions = (amount - thousands * 1000 - hundreds) / 1000000;

        StringBuffer buffer = new StringBuffer();

        addText(buffer, millions, "Million");
        addText(buffer, thousands, "Thousand");
        addText(buffer, hundreds, "");

        buffer.append("Dollars");

        System.out.println(buffer);
    }

    private static void addText(StringBuffer buffer, int amount, String scaleWord)
    {
        if (amount <= 0)
            return;

        int hundreds = amount / 100;
        int tens = (amount - hundreds * 100) / 10;
        int ones = amount - hundreds * 100 - tens * 10;

        if (hundreds > 0) {
            buffer.append(ONES[hundreds - 1]);
            buffer.append("Hundred");
        }

        if (tens >= 2 || (tens == 1 && ones == 0)) {
            buffer.append(TENS[tens - 1]);
        }
        else if (tens == 1) {
            buffer.append(TEENS[tens * 10 + ones - 11]);
        }

        if (tens != 1 && ones > 0) {
            buffer.append(ONES[ones - 1]);
        }

        buffer.append(scaleWord);
    }
}
