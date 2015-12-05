import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class HappyNumbers {

    private static final int[] squares = new int[]{0, 1, 4, 9, 16, 25, 36, 49, 64, 81};

    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            Set<String> seenNums = new TreeSet<>();

            while (!line.equals("1")) {
                if (seenNums.contains(line))
                    break;

                seenNums.add(line);

                int sum = 0;

                for (int index = 0; index < line.length(); index++) {
                    sum += squares[line.charAt(index) - 48];
                }

                line = Integer.toString(sum);
            }

            System.out.println(line.equals("1") ? 1 : 0);
        }
    }
}
