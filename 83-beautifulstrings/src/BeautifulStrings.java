import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BeautifulStrings {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            byte[] letterIndices = new byte[26];
            byte[] countToIndex = new byte[26];
            int[] letterCounts = new int[26];
            byte lastIndex = 0;

            for (int i = 0; i < letterIndices.length; i++) {
                letterIndices[i] = -1;
                countToIndex[i] = -1;
            }

            for (int i = 0; i < line.length(); i++) {
                byte c = (byte) line.charAt(i);

                if (c >= 65 && c <= 90)
                    c -= 65;
                else if (c >= 97 && c <= 122)
                    c -= 97;
                else
                    continue;

                if (letterIndices[c] == -1) {
                    letterIndices[c] = lastIndex++;
                    countToIndex[letterIndices[c]] = c;
                }

                letterCounts[letterIndices[c]]++;

                while (letterIndices[c] != 0 && letterCounts[letterIndices[c]] > letterCounts[letterIndices[c] - 1]) {
                    int temp = letterCounts[letterIndices[c]];
                    letterCounts[letterIndices[c]] = letterCounts[letterIndices[c] - 1];
                    letterCounts[letterIndices[c] - 1] = temp;

                    letterIndices[c]--;
                    letterIndices[countToIndex[letterIndices[c]]]++;

                    byte tempIndex = countToIndex[letterIndices[c]];
                    countToIndex[letterIndices[c]] = countToIndex[letterIndices[c] + 1];
                    countToIndex[letterIndices[c] + 1] = tempIndex;
                }
            }

            int sum = 0;

            for (int i = 0; i < letterCounts.length; i++) {
                sum += letterCounts[i] * (26 - i);
            }

            System.out.println(sum);
        }
    }
}
