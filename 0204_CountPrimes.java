import java.util.List;

public class LC204_CountPrimes {
  public int countPrimes(int n) {
    if (n < 3) {
      return 0;
    }
    if (n == 3) {
      return 1;
    }
    List<Integer> primes = new ArrayList<Integer>();
    primes.add(2);
    primes.add(3);
    for (int i = 4; i < n; i++) {
      boolean isPrime = true;
      for (int p : primes) {
        int rem = i % p;
        if (rem == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        primes.add(i);
      }
    }
    return primes.size();
  }
}
