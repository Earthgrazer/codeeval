import java.util.ArrayList;
import java.util.List;

public class SumOfPrimes {
    public static void main(String[] args)
    {
        final List<Integer> primes = new ArrayList<>();
        double num = 3;
        int primeSum = 5; // initial sum 2 + 3

        primes.add(2);
        primes.add(3);

        while (primes.size() < 1000) {
            num++;
            Integer squareRoot = (int)Math.sqrt(num);
            boolean isPrime = true;

            for (Integer divisor : primes) {
                if (divisor > squareRoot)
                    break;

                if (num % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (!isPrime)
                continue;

            primes.add((int)num);
            primeSum += num;
        }

        System.out.println(primeSum);
    }
}
