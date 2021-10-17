public class LC258_AddDigits {
  public int addDigits(int num) {
    int res = 0;
    while (num > 0) {
      res += num % 10;
      num = num / 10;
      if (num == 0 && res > 9) {
        num = res;
        res = 0;
      }
    }
    return res;
  }

  public int addDigitsFaster(int num) {
    return (num - 1) % 9 + 1;
  }
}
