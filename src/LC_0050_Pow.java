public class LC_0050_Pow{
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;

        if (n < 0) {
            return 1 / myPow(x, -n);
        }

        double v = myPow(x, n / 2);
        System.out.println("n:" + n + "::v=" + v);
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }
}