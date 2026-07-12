A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

## In Plain Terms

Check whether the string still reads as the same number after a 180-degree rotation. Each digit must rotate into its matching partner (`0`, `1`, and `8` stay the same; `6` and `9` swap), and any other digit makes the number invalid.

Example:

The numbers "69", "88", and "818" are all strobogrammatic.

```java
class Solution {
  public boolean isStrobogrammatic(String num) {
    char[] rotated = {'0', '1', 'n', 'n', 'n', 'n', '9', 'n', '8', '6'};

    for (int l = 0, r = num.length() - 1; l <= r; ++l, --r)
      if (rotated[num.charAt(l) - '0'] != num.charAt(r))
        return false;

    return true;
  }
}

```
