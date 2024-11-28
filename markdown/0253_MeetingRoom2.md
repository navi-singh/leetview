# Description
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example

Example1
Input: intervals = [(0,30),(5,10),(15,20)]
Output: 2

Explanation:<br/>
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)

Example2
Input: intervals = [(2,7)]
Output: 1

Explanation: <br/>
Only need one meeting room

Time: O(nlogn)  
Space: O(n)
```java
class Solution {
  public int minMeetingRooms(int[][] intervals) {
    if (intervals.length == 0)
      return 0;

    // store end times of each room
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

    Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

    for (int[] interval : intervals) {
      if (!pq.isEmpty() && interval[0] >= pq.peek())
        pq.poll(); // no overlap, we can reuse the same room
      pq.offer(interval[1]);
    }

    return pq.size();
  }
}

```
```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC253_MeetingRoom2 {
  public int minMeetingRooms(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparing((int[] interval) -> interval[0]));
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int roomsNeeded = 0;
    for (int[] interval : intervals) {
      if (heap.isEmpty()) {
        heap.offer(interval[1]);
        roomsNeeded++;
      } else {
        if (interval[0] >= heap.peek()) {
          heap.poll();
        } else {
          roomsNeeded++;
        }
        heap.offer(interval[1]);
      }
    }
    return roomsNeeded;
  }
}
```
