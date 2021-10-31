import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc127
 * @Description: [127. Word Ladder 最短单词路径]
 * @Author: [clh]
 * @Date: 2021/10/31 11:01
 **/
public class Lc127 {
    // 1 我自己写的 通过啦！
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        boolean[] visit = new boolean[n+1];
        int len = 0;
        Queue<String> que = new LinkedList<>();
        que.add(beginWord);
        while(!que.isEmpty()){
            int size = que.size();
            len++;
            while (size-- > 0) {
                String cur = que.poll();
                for(int i = 0; i < n; i++){
                    if(visit[i])
                        continue;
                    String next = wordList.get(i);
                    if(!check(cur, next))
                        continue;
                    if(next.equals(endWord))
                        return len + 1;
                    que.add(next);
                    visit[i] = true;
                }
            }
        }
        return 0;
    }

    private boolean check(String cur, String next) {
        int len1 = cur.length();
        int len2 = next.length();
        if(len1 != len2)
            return false;
        int p1 = 0;
        int p2 = 0;
        int cnt = 0; //记录不同字符的个数
        while(p1 < len1 && p2 < len2){
            if(cur.charAt(p1) != next.charAt(p2))
                cnt++;
            p1++;
            p2++;
        }
        return cnt < 2;
    }

    //2 github 思路跟我一样 不过拆分成好几个方法
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int N = wordList.size();
        int start = N - 1;
        int end = 0;
        while (end < N && !wordList.get(end).equals(endWord)) {
            end++;
        }
        if (end == N) {
            return 0;
        }
        List<Integer>[] graphic = buildGraphic(wordList);
        return getShortestPath(graphic, start, end);
    }

    private List<Integer>[] buildGraphic(List<String> wordList) {
        int N = wordList.size();
        List<Integer>[] graphic = new List[N];
        for (int i = 0; i < N; i++) {
            graphic[i] = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (isConnect(wordList.get(i), wordList.get(j))) {
                    graphic[i].add(j);
                }
            }
        }
        return graphic;
    }

    private boolean isConnect(String s1, String s2) {
        int diffCnt = 0;
        for (int i = 0; i < s1.length() && diffCnt <= 1; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }

    private int getShortestPath(List<Integer>[] graphic, int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[graphic.length];
        queue.add(start);
        marked[start] = true;
        int path = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            path++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int next : graphic[cur]) {
                    if (next == end) {
                        return path;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }
        return 0;
    }

}
