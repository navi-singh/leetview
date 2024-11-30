package com.lc;/*6. ZigZag Conversion
Medium
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);

Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

P A Y P A L I S H I R  I  N  G
P I N A L S I G Y A H  R  P  I
0 1 2 3 4 5 6 7 8 9 10 11 12 13

Example 3:
Input: s = "A", numRows = 1
Output: "A"
 PAYP
Constraints:
    1 <= s.length <= 1000
    s consists of English letters (lower-case and upper-case), ',' and '.'.
    1 <= numRows <= 1000

*/

public class LC_0006_zigzag{
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        StringBuilder res = new StringBuilder();
        int len = s.length();
        int gap = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < len; j += gap) {
                res.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + gap - i < len)
                    res.append(s.charAt(j + gap - i));
            }
        }
        return res.toString();
    }
}