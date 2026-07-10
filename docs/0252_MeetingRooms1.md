# Find meeting room

  Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2],
  ... , determine if a person could attend all meetings.
 
  <p>For example, Given [ [0, 30], [5, 10], [15, 20] ], return false.
 

```java
  class Solution {
  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

    for (int i = 1; i < intervals.length; ++i)
      if (intervals[i - 1][1] > intervals[i][0])
        return false;

    return true;
  }
}
```
  
```java
import java.util.Arrays;
public class LC252_MeetingRooms1 {
  public boolean canAttendMeetings(Interval[] intervals) {
    if (intervals == null || intervals.length < 1) {
      return true;
    }
    Arrays.sort(
        intervals,
        new Comparator<Interval>() {
          @Override
          public int compare(Interval a, Interval b) {
            return a.start - b.start;
          }
        });
    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i].end > intervals[i + 1].start) {
        return false;
      }
    }
    return true;
  }
 }
```
