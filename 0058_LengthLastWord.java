
public class LC58_LengthLastWord {
    public int lengthOfLastWord(String s) {
        if(s == null || s.length() < 1){
            return 0;
        }
        int firstNonSpace = s.length() - 1;
        while (s.charAt(firstNonSpace) == ' ') {
            if (firstNonSpace == 0) {
                return 0;
            }
            firstNonSpace--;
        }
        int left = firstNonSpace;
        while (left >= 0 && s.charAt(left) != ' ') {
            left--;
        }
        int length = firstNonSpace - left;
        return left == 0 ? s.charAt(left) == ' ' ? length : length + 1 : length;
    }}
}