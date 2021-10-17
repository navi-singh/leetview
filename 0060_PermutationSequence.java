import java.util.ArrayList;

public class LC60_PermutationSequence {
    // Need to find k/n! where n! will be total permutations
    public String getPermutation(int n, int k) {
        ArrayList<Integer> lis = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            lis.add(i);
            System.out.print(i + ",");
        }
        System.out.println();
        k--;
        long mod = 1;
        for (int i = 1; i <= n; ++i) {
            mod = mod * i;
            System.out.print(mod + ",");
        }
        System.out.println();
        String result = "";
        for (int i = 0; i < n; i++) {
            mod = mod / (n - i);
            int currIndex = k / mod;
            k %= mod;
            result += lis.get(currIndex);
            System.out.println(currIndex + ":" + k + "<>" + mod + "::" + result);
            lis.remove(currIndex);
        }
        return result.toString();
    }
}