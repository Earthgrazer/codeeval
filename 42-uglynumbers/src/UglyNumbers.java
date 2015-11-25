import java.lang.*;
import java.io.*;

/*
 * Ugly Numbers challenge solution - https://www.codeeval.com/open_challenges/42/
 */
public class UglyNumbers
{
    /**
     * Enum denoting possible operations between two digits.
     */
    enum Operation
    {
        None(0), // No operation, i.e. the two digits are part of the same number
        Plus(1), // Addition of the two digits
        Minus(-1); // Subtraction of the two digits

        private final int value;

        Operation(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        // CodeEval boilerplate to read input
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();

            int[] digits = new int[line.length()];

            for (int index = 0; index < line.length(); index++) {
                digits[index] = Character.getNumericValue(line.charAt(index));
            }

            int uglyCount = getUglyCount(0, 0, Operation.None, Operation.None, 0, digits);

            System.out.println(uglyCount);
        }

        // required for CodeEval
        System.exit(0);
    }

    /**
     * Gets the count of ugly numbers for all 3^(D-1) permutations of operations for the given digits (D).
     * @param lhs Current left hand-side value.
     * @param rhs Current right hand-side value.
     * @param prevOp The previously-encountered operation.
     * @param nextOp The next operation to perform.
     * @param index Current index in the digit sequence.
     * @param digits The complete digit sequence.
     * @return Count of ugly numbers for the given digit sequence.
     */
    private static int getUglyCount(int lhs, int rhs, Operation prevOp, Operation nextOp, int index, int[] digits)
    {
        /**
         * Work our way from the left side of the sequence to the right side. While the operation is None, we keep
         * appending the digits to form larger rhs. As soon as a Plus or Minus operation is encountered, we add or
         * subtract the rhs from the lhs, and replace lhs with the resulting value. Repeat this process until we hit
         * the end of the sequence, and evaluate whether the end total is an ugly number or not.
         */

        int uglyCount = 0;
        int currDigit = digits[index];

        if (nextOp == Operation.None) {
            // Shift the previous rhs one digit to the left, and append the current digit.
            rhs = rhs * 10 + currDigit;
        }
        else {
            lhs = eval(lhs, rhs, prevOp);
            rhs = digits[index];
            prevOp = nextOp;
        }

        // We've hit the end of the sequence, so evaluate the remaining expression and determine if ugly or not.
        if (index == digits.length - 1) {
            lhs = eval(lhs, rhs, prevOp);
            return (lhs % 2 == 0 || lhs % 3 == 0 || lhs % 5 == 0 || lhs % 7 == 0) ? 1 : 0;
        }

        // Recursively work through all three operations for the next index
        uglyCount += getUglyCount(lhs, rhs, prevOp, Operation.None, index + 1, digits);
        uglyCount += getUglyCount(lhs, rhs, prevOp, Operation.Plus, index + 1, digits);
        uglyCount += getUglyCount(lhs, rhs, prevOp, Operation.Minus, index + 1, digits);

        return uglyCount;
    }

    /**
     * Helper method to evaluate the result of two numbers given an operation.
     * @param lhs Left hand-side value.
     * @param rhs Right hand-side value.
     * @param op The operation to perform.
     * @return The resulting number from the operation.
     */
    private static int eval(int lhs, int rhs, Operation op)
    {
        if (op == Operation.None)
            return rhs;
        else
            return lhs + (op == Operation.Plus ? 1 : -1) * rhs;
    }
}