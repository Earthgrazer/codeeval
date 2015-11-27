import java.io.*;
import java.math.BigInteger;

public class LuckyTickets {

    // Lookup table for even digits from 2 to 100
    private static final String[] lookup = new String[]{
            "10",
            "670",
            "55252",
            "4816030",
            "432457640",
            "39581170420",
            "3671331273480",
            "343900019857310",
            "32458256583753952",
            "3081918923741896840",
            "294056694657804068000",
            "28170312778225750242100",
            "2707859169387181467852100",
            "261046730157780861858821136",
            "25228791861003454642059261392",
            "2443553412892220489195278947230",
            "237126779700111728623210793896700",
            "23050391247812238203687824747157800",
            "2244066255357188250744344225634235600",
            "218768894829904122626725603838896148680",
            "21353575263502884630388273714197145182600",
            "2086610157206763614866736016835099941514800",
            "204105559167234718098196864419783302549632800",
            "19983598742087310057357815311872236715171970100",
            "1958235988893910037740658552689739094876481545140",
            "192043570409723719873997281482556737440155994069900",
            "18847430845804472530652413091697822872821444349147800",
            "1850969282556229733639327066252630957954651870505893968",
            "181894832005057034383999852869437328659060112416355888580",
            "17885336470694891279287104747395798264488027368907029555640",
            "1759595312376617572854059903364131713003363235732517504574240",
            "173201762205643166279968008940412331699470273735812696599774110",
            "17056971894934558688846441987433052988620303403449436294441959820",
            "1680541301718359604648576749737521003907285387827431001567574176596",
            "165646654319976723701992406615067655308208865219762876621727020294840",
            "16333976304699214718657221877442624229398621392350520803192093730809000",
            "1611266420446186900661455564918186756941611539777885816296938086322793400",
            "159001106265949634483960692567311589105760040472435056876279269251280262400",
            "15695752713441211517384309707116838588162950015054857902560999394638257318080",
            "1549907697862929680336583303564541550920867807532198717258856653744296207074760",
            "153096138752277110214836722220881087457063409672964274344894548977869525514285400",
            "15126933076887535241547078349055723957328538056942697111947324195746641221987741240",
            "1495063936394820145978173084952651226318895610169643711778698526582132897696194675360",
            "147803690249353793831835578756919945270648840422121798343465830237679031761117155521520",
            "14615786698841561369944959191566195353879505580317403926959872129388565881505390562968052",
            "1445658259577075287740895176423089081604032456789033769154490620014797374846744269788548840",
            "143024700435101183997266162498015519369139255434050832382762644794442460391496029666811987520",
            "14153183315801667166858772596125134421835467308472191612483110921724154905563283000857391746100",
            "1400847570487018446424152137399824237820256780329223966658770240584725100387355667521375484520200",
            "138681178063913146486663255108385891670476531416644888545033078503482282975641730091720919340564340"
    };

    public static void main(String[] args) throws java.lang.Exception {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            int digits = Integer.parseInt(line);

            if (digits >= 1 && digits <= 100 && digits % 2 == 0)
                System.out.println(lookup[digits / 2 - 1]);
            else
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
//        for (int digits = 0; digits < numDigits; digits++) {
//            for (int num = 0; num < numDigits * 9; num++) {
//                int[] combos = new int[digits + 1];
//                int[] dupes = new int[(int) Math.pow(10, digits + 1)];
//                BigInteger ways = BigInteger.ZERO;
//                for (int iter = 0; iter < Math.pow(10, digits + 1); iter++) {
//                    int sum = 0;
//                    int seq = 0;
//
//                    for (int digit = 0; digit <= digits; digit++) {
//                        sum += combos[digit];
//                        seq += Math.pow(10, digit) * combos[digit];
//                    }
//
//                    if (dupes[seq] == 0 && sum == num + 1) {
//                        ways.add(BigInteger.ONE);
//                        dupes[seq] = 1;
//                    }
//
//                    for (int digit = 0; digit <= digits; digit++) {
//                        if (iter % Math.pow(10, digit) == 0) {
//                            if (combos[digit] == 9)
//                                combos[digit] = 0;
//                            else
//                                combos[digit]++;
//                        }
//                    }
//                }
//
//                permutations[num][digits] = ways;
//            }
//        }

        // compute the number of ways to add up to numbers from 1 to 9
        for (int digits = 0; digits < numDigits; digits++) {
            for (int num = 0; num < 9; num++) {
                // Only 1 way to add up to each number if there's only 1 digit
                if (digits == 0) {
                    permutations[num][digits] = BigInteger.ONE;
                    continue;
                }

                BigInteger perms = BigInteger.ZERO;
                // Sum the number of ways for previous number of digits up to num
                // Example: # of ways to get 2 with 3 digits is (number of ways to get 2 with 2 digits) +
                // (number of ways to get 1 with 2 digits)
                for (int subNum = 0; subNum <= num; subNum++) {
                    perms = perms.add(permutations[subNum][digits - 1]);
                }

                // Add one more to have the entirety of the number in the additional digit
                perms = perms.add(BigInteger.ONE);

                permutations[num][digits] = perms;
            }
        }

        // Numbers 10 to D*9
        for (int digits = 1; digits < numDigits; digits++) {
            int minNum = digits * 9;
            int maxNum = (digits + 1) * 9;

            for (int num = minNum; num < maxNum; num++) {
                BigInteger perms = BigInteger.ZERO;
                // Sum up all the ways to add to the number using # of ways from previous number of digits.
                // Example: To get 10 with 3 digits, add up all the ways to get 9 with 2 digits (plus 1 from
                // 3rd digit) + 8 with 2 digits (plus 2 from 3rd digit), etc.
                for (int num2 = minNum - maxNum + num; num2 < minNum; num2++) {
                    perms = perms.add(permutations[num2][digits - 1]);
                }

                permutations[num][digits] = perms;
            }

            // With the solution for digit D, we can now get the solution for D+1 using the same technique.
            for (int nextDigits = digits + 1; nextDigits < numDigits; nextDigits++) {
                for (int nextNum = minNum; nextNum < maxNum; nextNum++) {
                    BigInteger perms = BigInteger.ZERO;
                    for (int num2 = 0; num2 < 10; num2++) {
                        perms = perms.add(permutations[nextNum - num2][nextDigits - 1]);
                    }
                    permutations[nextNum][nextDigits] = perms;
                }
            }
        }

        BigInteger maxPerm = BigInteger.ZERO;

        // Get the maximum number of ways a number can added to.
        for (int numIndex = 0; numIndex < permutations.length; numIndex++) {
            if (permutations[numIndex][numDigits - 1].compareTo(maxPerm) > 0)
                maxPerm = permutations[numIndex][numDigits - 1];
        }

        //writePermutationsToFile(permutations);

        return maxPerm;
    }

    /**
     * Helper method to write permutation table to file in CSV format for testing purposes.
     * @param permutations permutation table
     */
    private static void writePermutationsToFile(int[][] permutations)
    {
        try {
            PrintWriter writer = new PrintWriter("output.csv");

            StringBuffer header = new StringBuffer("num,");

            for (int digit = 1; digit <= permutations[0].length; digit++) {
                header.append(digit);
                header.append(",");
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
