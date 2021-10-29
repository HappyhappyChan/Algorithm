import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc451
 * @Description: [451. Sort Characters By Frequency (Medium) 按照字符出现次数对字符串排序]
 * @Author: [clh]
 * @Date: 2021/10/27 20:18
 **/
public class Lc451 {

    // 1 hashmap + heap
    public String frequencySort(String s) {
        if(s.length() < 2)
            return s;
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i++);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        Queue<Character> que = new PriorityQueue<>((o1,o2)-> (map.get(o2) - map.get(o1)));
        for(char c: map.keySet()){
            que.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!que.isEmpty()){
            char c = que.poll();
            int cnt = map.get(c);
            //tree-->eetr而不是etr!
            while(cnt > 0){
                sb.append(c+"");
                cnt--;
            }
        }
        return sb.toString();
    }

    // 2 review 前面我学的对hashmap的排序
    public String frequencySort2(String s) {
        if (s.length() < 2)
            return s;
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i++);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>(){
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2){
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character, Integer> mapping: list){
            char c = mapping.getKey();
            int cnt = mapping.getValue();
            //tree-->eetr而不是etr!
            while(cnt > 0){
                sb.append(c+"");
                cnt--;
            }
        }
        return sb.toString();
    }

    // 3 bucket sort
    public String frequencySort3(String s) {
        if (s.length() < 2)
            return s;
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i++);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character>[] buckets = new ArrayList[s.length() + 1];

        for(char key : map.keySet()){
            int freq = map.get(key);
            if(buckets[freq] == null){
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }
        StringBuilder sb = new StringBuilder();
       for(int k = s.length(); k > 0; k--){
           if(buckets[k] == null)
               continue;
            for(int j = 0; j < buckets[k].size(); j++){
                int cnt = k;
                List<Character> tmp = buckets[k];
                while(cnt > 0){
                    sb.append(tmp.get(j)+"");
                    cnt--;
                }
            }
        }
        return sb.toString();
    }

}
