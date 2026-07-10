---
description: MEDIUM
---

# 71. Simplify Path

Given a string `path`, which is an **absolute path** (starting with a slash `'/'`) to a file or directory in a Unix-style file system, convert it to the simplified **canonical path**.

In a Unix-style file system, a period `'.'` refers to the current directory, a double period `'..'` refers to the directory one level up, and any multiple consecutive slashes (i.e., `'//'`) are treated as a single slash `'/'`. For this problem, any other format of periods such as `'...'` are treated as file/directory names.

The **canonical path** should have the following format:

- The path starts with a single slash `'/'`.
- Any two directories are separated by a single slash `'/'`.
- The path does not end with a trailing `'/'`.
- The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period `'.'` or double period `'..'`).

Return the simplified canonical path.

**Example 1:**

```text
Input: path = "/home/"
Output: "/home"
```

**Example 2:**

```text
Input: path = "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op.
```

**Example 3:**

```text
Input: path = "/home//foo/"
Output: "/home/foo"
```

**Constraints:**

- `1 <= path.length <= 3000`
- `path` consists of English letters, digits, period `'.'`, slash `'/'`, or `'_'`.
- `path` is a valid absolute Unix path.

---

## Approach 1: Stack-Based Segment Processing

Strip trailing slashes, then split the path into slash-delimited segments and push each onto a stack. Pop segments in reverse order using a `LinkedList` as the output queue. Segments equal to `/.` or `/` (the root marker) are skipped. Segments equal to `/..` increment a back-counter. Valid directory segments are added to the result unless a back-counter needs to be consumed.

#### Complexity Analysis

- **Time complexity: O(n).** We scan the path once to tokenize it and once to drain the stack.
- **Space complexity: O(n).** The stack and linked list together hold at most all path segments.

```java
class LC71_SimplifyPath {
  public String simplifyPath(String path) {
    Stack<String> stack = new Stack<String>();
    stack.push("/");
    int endIndex = path.length() - 1;

    while (path.length() > 0 && path.charAt(path.length() - 1) == '/') {
      endIndex--;
    }
    path = path.substring(0, endIndex);

    int start = 0;
    for (int i = 1; i < path.length(); i++) {
      if (path.charAt(i) == '/') {
        stack.push(path.substring(start, i));
        start = i;
      } else if (i == path.length() - 1) {
        stack.push(path.substring(start));
      }
    }

    LinkedList<String> list = new LinkedList<>();
    int back = 0;

    while (!stack.empty()) {
      String top = stack.pop();
      if (top.equals("/.") || top.equals("/")) {
      } else if (top.equals("/..")) {
        back++;
      } else {
        if (back > 0) {
          back--;
        } else {
          list.push(top);
        }
      }
    }
    if (list.isEmpty()) {
      return "/";
    }

    StringBuilder result = new StringBuilder();
    while (!list.isEmpty()) {
      result.append(list.pop());
    }
    return result.toString();
  }
}
```

**Key insight:** Using a stack to reverse the traversal order and a `back` counter to handle `..` segments allows the output to be assembled in canonical order without needing to re-scan the stack.
