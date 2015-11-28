import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DiscountOffers {
    private static final boolean[] vowels = new boolean[]{true, false, false, false, true, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, true, false};

    public static void main(String[] args) throws java.lang.Exception {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            String[] parts = line.split(";");
            String[] customers = parts[0].split(",");
            String[] products = parts[1].split(",");

            int[][] scores = calculateScores(customers, products);
            System.out.println(scores);
        }

        // required for CodeEval
        System.exit(0);
    }

    private static int[][] calculateScores(String[] customers, String[] products)
    {
        int[][]scores = new int[customers.length][products.length];

        for (int custIndex = 0; custIndex < customers.length; custIndex++) {
            int[] custLetterCounts = getLetterTypeCounts(customers[custIndex]);

            for (int prodIndex = 0; prodIndex < products.length; prodIndex++) {
                int[] prodLetterCounts = getLetterTypeCounts(products[prodIndex]);
                double score = 0;

                if ((prodLetterCounts[0] + prodLetterCounts[1]) % 2 == 0)
                    score = custLetterCounts[0] * 1.5;
                else
                    score = custLetterCounts[1];

                if (getGCD(prodLetterCounts[0] + prodLetterCounts[1], custLetterCounts[0] + custLetterCounts[1]) != 1)
                    score *= 1.5;

                // store 2 decimal point precision as integer
                scores[custIndex][prodIndex] = (int) score * 100;
            }
        }

        return scores;
    }

    /**
     * Euclidean algorithm to get greatest common divisor.
     * @param a First number
     * @param b Second number
     * @return Greatest common divisor of the two numbers.
     */
    private static int getGCD(int a, int b)
    {
        if (b == 0)
            return a;

        return getGCD(b, a%b);
    }

    private static int[] getLetterTypeCounts(String characters)
    {
        int[] counts = new int[2];

        for (int charIndex = 0; charIndex < characters.length(); charIndex++) {
            char character = characters.charAt(charIndex);

            if (character >= 65 && character <=90) {
                if (vowels[character - 65])
                    counts[0]++;
                else
                    counts[1]++;
            }
            else if (character >= 97 && character <= 122) {
                if (vowels[character - 97])
                    counts[0]++;
                else
                    counts[1]++;
            }
        }

        return counts;
    }
}
