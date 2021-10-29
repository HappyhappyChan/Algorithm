import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc347
 * @Description: [347. Top K Frequent Elements 出现最多次数的元素]
 * @Author: [clh]
 * @Date: 2021/10/27 17:12
 **/
public class Lc347 {
    // 1 我写的 搞了好久 终于成功！
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length < k)
            return null;
        int[] ret = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for(int x: nums){
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        /**
        Integer[] v = map.values().toArray(new Integer[0]);
        //这个是从小到大排序
        Arrays.sort(v);
        //map里面没有根据value得到key的……
        for(int i = 0; i < k; i++){
            ret[i] = map.key(v[i]);
        }
        */
        //利用Comparator接口进行排序
        //这里将map.entrySet()转换成list
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });
        int i = 0;
        for(Map.Entry<Integer,Integer> mapping:list){
            if(i >= k){
                break;
            }
            ret[i++] = mapping.getKey();
        }
        return ret;
    }

    //2 heap+hashmap leetcode
    public int[] topKFrequent2(int[] nums, int k){
        //o(1)time
        if(k == nums.length){
            return nums;
        }

        //1 build hashmap
        //O(N)
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int n:nums){
            cnt.put(n, cnt.getOrDefault(n, 0)+1);
        }

        //init heap 频率低的先进去
        //Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> cnt.get(n1) - cnt.get(n2));
        //一个小顶堆
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(cnt::get));
        //2 keep k top frequent elements in the heap
        //O(Nlogk) < O(NlogN) time
        for(int n: cnt.keySet()){
            heap.add(n);
            if(heap.size() > k)
                heap.poll();
        }

        //3 build an output array
        //O(klogk)
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; i--){
            top[i] = heap.poll();
        }
        return top;
    }

    //3 quick select hashmap leetcode
    int[] unique;
    Map<Integer, Integer> count;

    public void swap(int a, int b){
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }
    public int partition(int l, int r, int pivot_index){
        int pivot_freq = count.get(unique[pivot_index]);
        //1 将pivot挪到end
        swap(pivot_index, r);
        int store_ind = l;

        //2 把所有频率小的挪到左边
        for(int i = l; i <= r; i++){
            if(count.get(unique[i]) < pivot_freq){
                swap(store_ind, i);
                store_ind++;
            }
        }

        // 3 把pivot放到final place
        swap(store_ind,r);
        return store_ind;
    }

    public void quickselect(int l, int r, int k_smallest){
        //base case: only 1 element
        if(l == r) return;

        //select a random pivot_index
        Random random_num = new Random();
        int pivot_index = l + random_num.nextInt(r - l);

        //find pivot positionn in a sorted list
        pivot_index = partition(l, r, pivot_index);

        // if the pivot is in its final sorted position
        if (k_smallest == pivot_index) {
            return;
        } else if (k_smallest < pivot_index) {
            // go left
            quickselect(l, pivot_index - 1, k_smallest);
        } else {
            // go right
            quickselect(pivot_index + 1, r, k_smallest);
        }
    }

    public int[] topKFrequent3(int[] nums, int k) {
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


    //4 bucket sort
    public int[] topKFrequent4(int[] nums, int k){
        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new ArrayList[nums.length + 1];

        for (int key : frequencyForNum.keySet()) {
            int frequency = frequencyForNum.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            //第 i 个桶中存储的数出现的频率为 i
            buckets[frequency].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
            if (buckets[i] == null) {
                continue;
            }
            //频率为i的有几个数 要的是k个，现在list里面有topK.size()个
            //所以还能放k-topk.size()个
            //如果频率为i的桶里面<=k-top.size()个 则可以放入
            if (buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            } else {
                //如果超过了，就选几个放进去就好
                topK.addAll(buckets[i].subList(0, k - topK.size()));
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topK.get(i);
        }
        return res;
    }
}
