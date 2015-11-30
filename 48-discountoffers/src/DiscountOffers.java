import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
            List<Integer> custIndices = new ArrayList<>();
            List<Integer> prodIndices = new ArrayList<>();

            for (int cust = 0; cust < scores.length; cust++) {
                custIndices.add(cust);
            }

            for (int prod = 0; prod < scores[0].length; prod++) {
                prodIndices.add(prod);
            }

            StringBuffer strBuf = new StringBuffer(String.valueOf(getMaxScore(custIndices, prodIndices, scores)));

            if (strBuf.length() == 1)
                System.out.println("0.00");
            else
                System.out.println(strBuf.insert(strBuf.length() - 2, "."));
        }

        // required for CodeEval
        System.exit(0);
    }

    private static int getMaxScore(List<Integer> custIndices, List<Integer> prodIndices, int[][] scores) {
        int highScore = 0;

        if (custIndices.size() == 1) {
            for (Integer prodIndex : prodIndices) {
                int score = scores[custIndices.get(0)][prodIndex];

                if (score > highScore)
                    highScore = score;
            }

            return highScore;
        }

        for (Integer custIndex : custIndices) {
            for (Integer prodIndex : prodIndices) {
                List<Integer> remainingCustIndices = new ArrayList<>(custIndices);
                List<Integer> remainingProdIndices = new ArrayList<>(prodIndices);

                remainingCustIndices.remove(custIndex);
                remainingProdIndices.remove(prodIndex);

                int subHighScore = getMaxScore(remainingCustIndices, remainingProdIndices, scores);
                int score = scores[custIndex][prodIndex];
                int subTotal = subHighScore + score;

                if (subTotal > highScore)
                    highScore = subTotal;
            }
        }

        return highScore;
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
                scores[custIndex][prodIndex] = (int) (score * 100.0);
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
