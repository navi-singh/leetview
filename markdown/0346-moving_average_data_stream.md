# [346. Moving Average from Data Stream](https://leetcode.com/problems/moving-average-from-data-stream/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days)

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the`MovingAverage` class:

- `MovingAverage(int size)` Initializesthe object with the size of the window `size`.
- `double next(int val)` Returns the moving average of the last `size` values of the stream.

**Example 1:** 

```
Input

["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output

[null, 1.0, 5.5, 4.66667, 6.0]

Explanation

MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
```
### Solution
#### Complexity
* Time Complexity: O(1), as we explained in intuition.
* Space Complexity: O(N), where N is the size of the moving window.

```Java
class MovingAverage {
  int size, windowSum = 0, count = 0;
  Deque queue = new ArrayDeque<Integer>();

  public MovingAverage(int size) {
    this.size = size;
  }

  public double next(int val) {
    ++count;
    // calculate the new sum by shifting the window
    queue.add(val);
    int tail = count > size ? (int)queue.poll() : 0;

    windowSum = windowSum - tail + val;

    return windowSum * 1.0 / Math.min(size, count);
  }
}
```
#### python
```python
from collections import deque
class MovingAverage:
    def __init__(self, size: int):
        self.size = size
        self.queue = deque()
        # number of elements seen so far
        self.window_sum = 0
        self.count = 0

    def next(self, val: int) -> float:
        self.count += 1
        # calculate the new sum by shifting the window
        self.queue.append(val)
        tail = self.queue.popleft() if self.count > self.size else 0

        self.window_sum = self.window_sum - tail + val

        return self.window_sum / min(self.size, self.count)
```
