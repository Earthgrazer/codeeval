import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LuckyTickets {
    public static void main(String[] args) throws java.lang.Exception {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            System.out.println(getLuckyCount(Integer.parseInt(line)));
        }

        // required for CodeEval
        System.exit(0);
    }

    private static int getLuckyCount(int numDigits)
    {
        int[][] permutations = new int[9 * numDigits][numDigits];

        // initialize all permutations to 0
        for (int numIndex = 0; numIndex < permutations.length; numIndex++) {
            for (int digitIndex = 0; digitIndex < permutations[0].length; digitIndex++) {
                permutations[numIndex][digitIndex] = 0;
            }
        }

        for (int digits = 0; digits < numDigits; digits++) {
            for (int num = 0; num < 9; num++) {
                // 1 digit
                if (digits == 0) {
                    permutations[num][digits] = 1;
                    continue;
                }

                int perms = 0;

                for (int subNum = 0; subNum <= num; subNum++) {
                    perms += permutations[subNum][digits - 1];
                }

                perms += 1;

                permutations[num][digits] = perms;
            }
        }

        for (int digits = 1; digits < numDigits; digits++) {
            int minNum = digits * 9;
            int maxNum = (digits + 1) * 9;

            for (int num = minNum; num < maxNum; num++) {
                int perms = 0;
                for (int num2 = minNum - maxNum + num; num2 < minNum; num2++) {
                    perms += permutations[num2][digits - 1];
                }

                permutations[num][digits] = perms;
            }
        }

        int totalPermCount = 0;

        for (int numIndex = (numDigits - 1) * 9; numIndex < permutations.length; numIndex++) {
            totalPermCount += permutations[numIndex][numDigits - 1];
        }

        return totalPermCount;
    }
}
