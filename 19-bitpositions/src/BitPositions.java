import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BitPositions {
    public static void main(String[] args) throws IOException
    {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;

        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] strParams = line.split(",");
            int num = Integer.parseInt(strParams[0]);
            int p1 = Integer.parseInt(strParams[1]) - 1;
            int p2 = Integer.parseInt(strParams[2]) - 1;

            if (((num & 1 << p1) != 0) == ((num & 1 << p2) != 0))
                System.out.println("true");
            else
                System.out.println("false");
        }

        // required for CodeEval
        System.exit(0);
    }
}
