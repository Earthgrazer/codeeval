import java.io.*;

public class ReverseWords {
    public static void main(String[] args) throws IOException
    {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        boolean firstLine = true;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            String[] words = line.split(" ");

            if (words.length == 0)
                continue;

            if (!firstLine)
                System.out.println();

            for (int index = words.length - 1; index >= 0; index--) {
                System.out.print(words[index]);
                System.out.print(index != 0 ? " " : "");
            }

            firstLine = false;
        }

        // required for CodeEval
        System.exit(0);
    }
}
