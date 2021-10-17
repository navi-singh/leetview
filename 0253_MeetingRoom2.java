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
