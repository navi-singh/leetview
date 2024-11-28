# 1047. Remove All Adjacent Duplicates In String

You are given a string `s` consisting of lowercase English letters. A **duplicate removal** consists of choosing two **adjacent** and **equal** letters and removing them.

We repeatedly make **duplicate removals** on `s` until we no longer can.

Return _the final string after all such duplicate removals have been made_. It can be proven that the answer is **unique**.

**Example 1:**

```text
Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
```

**Example 2:**

```text
Input: s = "azxxzy"
Output: "ay"
```

**Approach 2: Stack**

We could trade an extra space for speed. The idea is to use an output stack to keep track of only non duplicate characters. Here is how it works:

* Current string character is equal to the last element in stack? Pop that last element out of stack.
* Current string character is _not_ equal to the last element in stack? Add the current character into stack.

> Which data structure to use as the stack here?

Something that is fast to convert to string for output, for example list in Python and StringBuilder in Java.![Current](blob:https://leetcode.com/3c88d389-8748-6446-8722-d10b58b83794)1 / 11

**Algorithm**

* Initiate an empty output stack.
* Iterate over all characters in the string.
  * Current element is equal to the last element in stack? Pop that last element out of stack.
  * Current element is not equal to the last element in stack? Add the current element into stack.
* Convert stack into string and return it.

**Implementation**

{% tabs %}
{% tab title="Java" %}
```java
class Solution {
  public String removeDuplicates(String S) {
    StringBuilder sb = new StringBuilder();
    int sbLength = 0;
    for(char character : S.toCharArray()) {
      if (sbLength != 0 && character == sb.charAt(sbLength - 1))
        sb.deleteCharAt(sbLength-- - 1);
      else {
        sb.append(character);
        sbLength++;
      }
    }
    return sb.toString();
  }
}
```
{% endtab %}

{% tab title="Python" %}
```python
class Solution:
    def removeDuplicates(self, S: str) -> str:
        output = []
        for ch in S:
            if output and ch == output[-1]: 
                output.pop()
            else: 
                output.append(ch)
        return ''.join(output)
```
{% endtab %}
{% endtabs %}

**Complexity Analysis**

* Time complexity :O\(N\)\mathcal{O}\(N\)O\(N\), where N is a string length.
* Space complexity :O\(N−D\)\mathcal{O}\(N - D\)O\(N−D\) where D is a total length for all duplicates.

