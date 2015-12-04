import java.io.*;

public class MultiplesOfANumber {
    public static void main(String[] args) throws IOException
    {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;

        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] strParams = line.split(",");
            int[] params = new int[]{Integer.parseInt(strParams[0]), Integer.parseInt(strParams[1])};
            int multiple = params[1];

            while (multiple < params[0]) {
                multiple += params[1];
            }

            System.out.println(multiple);
        }

        // required for CodeEval
        System.exit(0);
    }
}
