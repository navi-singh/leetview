package com.lc;

import java.util.ArrayList;
import java.util.List;

public class LC_0068_TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        int runningLength = 0, lastIndex = 0;

        for (int i = 0; i < words.length; i++) {
            runningLength += words[i].length();
            if ((runningLength + i - lastIndex) > maxWidth) {
                int wordsLen = runningLength - words[i].length();
                int spaceLen = maxWidth - wordsLen;
                int eachSpaceLen = 1;
                int addtionalSpaces = 0;
                if (i - lastIndex - 1 > 0) {
                    eachSpaceLen = spaceLen / (i - lastIndex - 1);
                    addtionalSpaces = spaceLen % (i - lastIndex - 1);
                }
                StringBuilder sb = new StringBuilder();
                for (int k = lastIndex; k < i - 1; k++) {
                    sb.append(words[k]);
                    for (int num = 0; num < eachSpaceLen; num++) {
                        sb.append(" ");
                    }
                    if (addtionalSpaces > 0) {
                        sb.append(" ");
                        addtionalSpaces--;
                    }
                }
                sb.append(words[i - 1]);
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                res.add(sb.toString());
                lastIndex = i;
                runningLength = words[i].length();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = lastIndex; i < words.length - 1; i++) {
            runningLength += words[i].length();
            sb.append(words[i] + " ");
        }
        sb.append(words[words.length - 1]);
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        res.add(sb.toString());
        return res;
    }
}