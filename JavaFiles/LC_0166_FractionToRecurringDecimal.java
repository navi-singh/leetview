import java.util.HashMap;
import java.util.Map;

public class LC166_FractionToRecurringDecimal {
  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) {
      return "0";
    }
    if (denominator == 0) {
      return "";
    }
    String res = "";
    if (numerator < 0 ^ denominator < 0) {
      res += "-";
    }
    long num = Math.abs(numerator);
    long den = Math.abs(denominator);
    long div = num / den;
    res += div;
    long rem = (num % den) * 10;
    if (rem == 0) {
      return res;
    }

    Map<Long, Integer> map = new HashMap<>();
    res += ".";
    while (rem != 0) {
      if (map.containsKey(rem)) {
        int beg = map.get(rem);
        String part1 = res.substring(0, beg);
        String part2 = res.substring(beg, res.length());
        return part1 + "(" + part2 + ")";
      }
      map.put(rem, res.length());
      div = rem / den;
      res += String.valueOf(div);
      rem = (rem % den) * 10;
    }
    return res;
  }
}
