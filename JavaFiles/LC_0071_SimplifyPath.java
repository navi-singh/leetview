import java.util.LinkedList;
import java.util.Stack;

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
