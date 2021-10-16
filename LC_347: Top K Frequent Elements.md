347. Top K Frequent Elements
Medium

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:

Input: nums = [1], k = 1
Output: [1]

 

Constraints:

    1 <= nums.length <= 105
    k is in the range [1, the number of unique elements in the array].
    It is guaranteed that the answer is unique.

Approach 1: Heap

Let's start from the simple heap approach with O(Nlog⁡k)\mathcal{O}(N \log k)O(Nlogk) time complexity. To ensure that O(Nlog⁡k)\mathcal{O}(N \log k)O(Nlogk) is always less than O(Nlog⁡N)\mathcal{O}(N \log N)O(NlogN), the particular case k=Nk = Nk=N could be considered separately and solved in O(N)\mathcal{O}(N)O(N) time.

Algorithm

    The first step is to build a hash map element -> its frequency. In Java, we use the data structure HashMap. Python provides dictionary subclass Counter to initialize the hash map we need directly from the input array.
    This step takes O(N)\mathcal{O}(N)O(N) time where N is a number of elements in the list.

    The second step is to build a heap of size k using N elements. To add the first k elements takes a linear time O(k)\mathcal{O}(k)O(k) in the average case, and O(log⁡1+log⁡2+...+log⁡k)=O(logk!)=O(klog⁡k)\mathcal{O}(\log 1 + \log 2 + ... + \log k) = \mathcal{O}(log k!) = \mathcal{O}(k \log k)O(log1+log2+...+logk)=O(logk!)=O(klogk) in the worst case. It's equivalent to heapify implementation in Python. After the first k elements we start to push and pop at each step, N - k steps in total. The time complexity of heap push/pop is O(log⁡k)\mathcal{O}(\log k)O(logk) and we do it N - k times that means O((N−k)log⁡k)\mathcal{O}((N - k)\log k)O((N−k)logk) time complexity. Adding both parts up, we get O(Nlog⁡k)\mathcal{O}(N \log k)O(Nlogk) time complexity for the second step.

    The third and the last step is to convert the heap into an output array. That could be done in O(klog⁡k)\mathcal{O}(k \log k)O(klogk) time.

In Python, library heapq provides a method nlargest, which combines the last two steps under the hood and has the same O(Nlog⁡k)\mathcal{O}(N \log k)O(Nlogk) time complexity.

diff

Implementation
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }
        
        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
          count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(
            (n1, n2) -> count.get(n1) - count.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
          heap.add(n);
          if (heap.size() > k) heap.poll();    
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }
}

Complexity Analysis

    Time complexity : O(Nlog⁡k)\mathcal{O}(N \log k)O(Nlogk) if k<Nk < Nk<N and O(N)\mathcal{O}(N)O(N) in the particular case of N=kN = kN=k. That ensures time complexity to be better than O(Nlog⁡N)\mathcal{O}(N \log N)O(NlogN).

    Space complexity : O(N+k)\mathcal{O}(N + k)O(N+k) to store the hash map with not more NNN elements and a heap with kkk elements. 
    
    Approach 2: Quickselect (Hoare's selection algorithm)

Quickselect is a textbook algorthm typically used to solve the problems "find kth something": kth smallest, kth largest, kth most frequent, kth less frequent, etc. Like quicksort, quickselect was developed by Tony Hoare, and also known as Hoare's selection algorithm.

It has O(N)\mathcal{O}(N)O(N) average time complexity and widely used in practice. It worth to note that its worth case time complexity is O(N2)\mathcal{O}(N^2)O(N2), although the probability of this worst-case is negligible.

The approach is the same as for quicksort.

    One chooses a pivot and defines its position in a sorted array in a linear time using so-called partition algorithm.

As an output, we have an array where the pivot is on its perfect position in the ascending sorted array, sorted by the frequency. All elements on the left of the pivot are less frequent than the pivot, and all elements on the right are more frequent or have the same frequency.

Hence the array is now split into two parts. If by chance our pivot element took N - kth final position, then kkk elements on the right are these top kkk frequent we're looking for. If not, we can choose one more pivot and place it in its perfect position.

diff

If that were a quicksort algorithm, one would have to process both parts of the array. That would result in O(Nlog⁡N)\mathcal{O}(N \log N)O(NlogN) time complexity. In this case, there is no need to deal with both parts since one knows in which part to search for N - kth less frequent element, and that reduces the average time complexity to O(N)\mathcal{O}(N)O(N).

Algorithm

The algorithm is quite straightforward :

    Build a hash map element -> its frequency and convert its keys into the array unique of unique elements. Note that elements are unique, but their frequencies are not. That means we need a partition algorithm that works fine with duplicates.

    Work with unique array. Use a partition scheme (please check the next section) to place the pivot into its perfect position pivot_index in the sorted array, move less frequent elements to the left of pivot, and more frequent or of the same frequency - to the right.

    Compare pivot_index and N - k.

        If pivot_index == N - k, the pivot is N - kth most frequent element, and all elements on the right are more frequent or of the same frequency. Return these top kkk frequent elements.

        Otherwise, choose the side of the array to proceed recursively.

diff

Lomuto's Partition Scheme

There is a zoo of partition algorithms. The most simple one is Lomuto's Partition Scheme, and so is what we will use in this article.

Here is how it works:

    Move pivot at the end of the array using swap.

    Set the pointer at the beginning of the array store_index = left.

    Iterate over the array and move all less frequent elements to the left swap(store_index, i). Move store_index one step to the right after each swap.

    Move the pivot to its final place, and return this index.

Current
1 / 14

Implementation
class Solution {
    int[] unique;
    Map<Integer, Integer> count;

    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }
    
    public void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place. 
        */

        // base case: the list contains only one element
        if (left == right) return;
        
        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left); 

        // find the pivot position in a sorted list
        pivot_index = partition(left, right, pivot_index);

        // if the pivot is in its final sorted position
        if (k_smallest == pivot_index) {
            return;    
        } else if (k_smallest < pivot_index) {
            // go left
            quickselect(left, pivot_index - 1, k_smallest);     
        } else {
            // go right 
            quickselect(pivot_index + 1, right, k_smallest);  
        }
    }
    
    public int[] topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        count = new HashMap();
        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        // array of unique elements
        int n = count.size();
        unique = new int[n]; 
        int i = 0;
        for (int num: count.keySet()) {
            unique[i] = num;
            i++;
        }
        
        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array. 
        // All element on the left are less frequent.
        // All the elements on the right are more frequent. 
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }
}

Here is a total algorithm implementation.

Complexity Analysis

    Time complexity: O(N)\mathcal{O}(N)O(N) in the average case, O(N2)\mathcal{O}(N^2)O(N2) in the worst case. Please refer to this card for the good detailed explanation of Master Theorem. Master Theorem helps to get an average complexity by writing the algorithm cost as T(N)=aT(N/b)+f(N)T(N) = a T(N / b) + f(N)T(N)=aT(N/b)+f(N). Here we have an example of Master Theorem case III: T(N)=T(N2)+NT(N) = T \left(\frac{N}{2}\right) + NT(N)=T(2N​)+N, that results in O(N)\mathcal{O}(N)O(N) time complexity. That's the case of random pivots.

    In the worst-case of constantly bad chosen pivots, the problem is not divided by half at each step, it becomes just one element less, that leads to O(N2)\mathcal{O}(N^2)O(N2) time complexity. It happens, for example, if at each step you choose the pivot not randomly, but take the rightmost element. For the random pivot choice the probability of having such a worst-case is negligibly small.

    Space complexity: up to O(N)\mathcal{O}(N)O(N) to store hash map and array of unique elements. 
