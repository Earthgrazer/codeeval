import java.io.*;

class FizzBuzz {
    public static void main(String[] args) throws java.lang.Exception {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            String[] params = line.split(" ");

            print(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]));
        }

        // required for CodeEval
        System.exit(0);
    }

    private static void print(int fizzDiv, int buzzDiv, int maxNum)
    {
        StringBuffer buffer = new StringBuffer();

        for (int currNum = 1; currNum <= maxNum; currNum++) {
            boolean fizzBuzzed = false;

            if (currNum % fizzDiv == 0) {
                buffer.append("F");
                fizzBuzzed = true;
            }
            if (currNum % buzzDiv == 0) {
                buffer.append("B");
                fizzBuzzed = true;
            }

            if (!fizzBuzzed)
                buffer.append(currNum);

            buffer.append(" ");
        }

        buffer.deleteCharAt(buffer.length() - 1);

        System.out.println(buffer);
    }
}
