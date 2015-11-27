import java.io.*;
import java.math.BigInteger;

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

    private static BigInteger getLuckyCount(int numDigits)
    {
        BigInteger[][] permutations = new BigInteger[9 * numDigits][numDigits];

        for (int digits = 0; digits < numDigits; digits++) {
            for (int num = 0; num < numDigits * 9; num++) {
                permutations[num][digits] = BigInteger.ZERO;
            }
        }

        // brute force method, disabled
        for (int digits = 0; false && digits < numDigits; digits++) {
            for (int num = 0; num < numDigits * 9; num++) {
                int[] combos = new int[digits + 1];
                int[] dupes = new int[(int) Math.pow(10, digits + 1)];
                BigInteger ways = BigInteger.ZERO;
                for (int iter = 0; iter < Math.pow(10, digits + 1); iter++) {
                    int sum = 0;
                    int seq = 0;

                    for (int digit = 0; digit <= digits; digit++) {
                        sum += combos[digit];
                        seq += Math.pow(10, digit) * combos[digit];
                    }

                    if (dupes[seq] == 0 && sum == num + 1) {
                        ways.add(BigInteger.ONE);
                        dupes[seq] = 1;
                    }

                    for (int digit = 0; digit <= digits; digit++) {
                        if (iter % Math.pow(10, digit) == 0) {
                            if (combos[digit] == 9)
                                combos[digit] = 0;
                            else
                                combos[digit]++;
                        }
                    }
                }

                permutations[num][digits] = ways;
            }
        }

        for (int digits = 0; digits < numDigits; digits++) {
            for (int num = 0; num < 9; num++) {
                // 1 digit
                if (digits == 0) {
                    permutations[num][digits] = BigInteger.ONE;
                    continue;
                }

                BigInteger perms = BigInteger.ZERO;

                for (int subNum = 0; subNum <= num; subNum++) {
                    perms.add(permutations[subNum][digits - 1]);
                }

                perms.add(BigInteger.ONE);

                permutations[num][digits] = perms;
            }
        }

        for (int digits = 1; digits < numDigits; digits++) {
            int minNum = digits * 9;
            int maxNum = (digits + 1) * 9;

            for (int num = minNum; num < maxNum; num++) {
                BigInteger perms = BigInteger.ZERO;
                for (int num2 = minNum - maxNum + num; num2 < minNum; num2++) {
                    perms.add(permutations[num2][digits - 1]);
                }

                permutations[num][digits] = perms;
            }

            for (int nextDigits = digits + 1; nextDigits < numDigits; nextDigits++) {
                for (int nextNum = minNum; nextNum < maxNum; nextNum++) {
                    BigInteger perms = BigInteger.ZERO;
                    for (int num2 = 0; num2 < 10; num2++) {
                        perms.add(permutations[nextNum - num2][nextDigits - 1]);
                    }
                    permutations[nextNum][nextDigits] = perms;
                }
            }
        }

        BigInteger maxPerm = BigInteger.ZERO;

        for (int numIndex = 0; numIndex < permutations.length; numIndex++) {
            if (permutations[numIndex][numDigits - 1].compareTo(maxPerm) > 0)
                maxPerm = permutations[numIndex][numDigits - 1];
        }

        //writePermutationsToFile(permutations);

        return maxPerm;
    }

    private static void writePermutationsToFile(int[][] permutations)
    {
        try {
            PrintWriter writer = new PrintWriter("output.csv");

            StringBuffer header = new StringBuffer("num,");

            for (int digit = 1; digit <= permutations[0].length; digit++) {
                header.append(digit + ",");
            }

            writer.println(header);

            for (int num = 0; num < permutations.length; num++) {
                writer.print(num + ",");
                for (int digit = 0; digit < permutations[num].length; digit++) {
                    writer.print(permutations[num][digit] + ",");
                }

                writer.println();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
