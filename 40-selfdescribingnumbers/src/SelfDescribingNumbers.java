public class SelfDescribingNumbers {
    public static void main (String[] args) throws java.lang.Exception
    {
        String line = "2020";

        int[] positions = new int[line.length()];

        for (int index = 0; index < line.length(); index++) {
            int num = line.charAt(index) - 48;

            if (num < positions.length) {
                positions[num]++;
            }
        }

        boolean isSelfDesc = true;

        for (int index = 0; index < positions.length; index++) {
            if (positions[index] != line.charAt(index) - 48) {
                isSelfDesc = false;
                break;
            }
        }

        System.out.println(isSelfDesc ? 1 : 0);
    }
}
