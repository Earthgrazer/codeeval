import java.io.*;

public class DecodeNumbers {
    public static void main(String[] args) throws Exception {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;

        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            int prevDigit = Character.getNumericValue(line.charAt(0));
            int[] ways = new int[line.length()];

            ways[0] = 1;

            for (int i = 1; i < line.length(); i++) {
                int currDigit = Character.getNumericValue(line.charAt(i));
                int combinedValue = prevDigit * 10 + currDigit;

                if (combinedValue >= 1 && combinedValue <= 26) {
                    if (i - 2 >= 0) {
                        ways[i] = 2 * ways[i - 2];
                    }
                    else {
                        ways[i] = 2;
                    }
                }
                else {
                    ways[i] = ways[i - 1];
                }

                prevDigit = currDigit;
            }

            System.out.println(ways[ways.length - 1]);
        }

        // required for CodeEval
        System.exit(0);
    }
}