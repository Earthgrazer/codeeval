import java.io.*;

public class SetIntersection {
    public static void main(String[] args) throws java.lang.Exception {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        boolean firstLine = true;

        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            String[] sets = line.split(";");
            String[] strSet1 = sets[0].split(",");
            String[] strSet2 = sets[1].split(",");
            int[] set1 = new int[strSet1.length];
            int[] set2 = new int[strSet2.length];

            for (int index = 0; index < strSet1.length; index++) {
                set1[index] = Integer.parseInt(strSet1[index]);
            }

            for (int index = 0; index < strSet2.length; index++) {
                set2[index] = Integer.parseInt(strSet2[index]);
            }

            if (!firstLine)
                System.out.println();

            firstLine = false;

            // no intersection
            if (set1[0] > set2[set2.length - 1] || set2[0] > set1[set1.length - 1])
                continue;

            int pos2 = 0;
            boolean firstNumPrinted = false;

            for (int pos1 = 0; pos1 < set1.length; pos1++) {
                while (set2[pos2] < set1[pos1] && pos2 < set2.length - 1) {
                    pos2++;
                }

                if (set1[pos1] == set2[pos2]) {
                    if (firstNumPrinted)
                        System.out.print(",");

                    System.out.print(set1[pos1]);

                    firstNumPrinted = true;
                }
            }
        }

        // required for CodeEval
        System.exit(0);
    }
}
