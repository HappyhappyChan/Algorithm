import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc406
 * @Description: [406. Queue Reconstruction by Height(Medium) 根据身高和序号重组队列]
 * @Author: [clh]
 * @Date: 2021/10/28 15:21
 **/
public class Lc406 {
    //1 我自己写
    public int[][] reconstructQueue(int[][] people) {
        if(people == null)
            return null;
        //先按人数排 人数相同按照身高排
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if(o1[1] > o2[1])
                    return 1;
                if(o1[1] == o2[1]){
                    if(o1[0] < o2[0])
                        return -1;
                    if(o1[0] == o2[0])
                        return 0;
                    if(o1[0] > o2[0])
                        return 1;
                }
                return -1;
            }
        });

        int[][] ret = people;
        for(int i = 1; i < ret.length; i++){
            int k = ret[i][1];
            int height = ret[i][0];
            int cnt = 0;
            int ind = 0;
            for(int j = 0; j < i ; j++){
                if(ret[j][0] >= height){
                    cnt++;
                }
                if(cnt == k && j + 1 < i){
                    //记录下到第几个就够k个人了
                    ind = j + 1;
                }
            }
            if(cnt > k){
                swap(ret, ind, i);
            }
        }
        return ret;
    }

    private void swap(int[][] ret, int ind, int i) {
        int[] tmp = ret[ind];
        ret[ind] = ret[i];
        for(int j = i; j > ind + 1; j--){
            ret[j] = ret[j - 1];
        }
        ret[ind+1] = tmp;
    }

    //2 github
    public int[][] reconstructQueue2(int[][] people){
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        //身高降序 个数升序
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> que = new ArrayList<>();
        for(int[] p: people){
            que.add(p[1], p);
        }
        return que.toArray(new int[que.size()][]);
    }

    // 3 leetcode-cn
    public int[][] reconstructQueue3(int[][] people) {
        //按数组第一个元素进行降序，按第二个元素进行升序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] person1, int[] person2){
                if (person1[0] != person2[0]){
                    //第一个元素不相等时，第一个元素降序
                    return person2[0] - person1[0];
                }else{
                    //第一个元素相等时，第二个元素升序
                    return person1[1] - person2[1];
                }
            }
        });
        //新建一个list,用于保存结果集
        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
            if (list.size() > people[i][1]){
                //结果集中元素个数大于第i个人前面应有的人数时，将第i个人插入到结果集的 people[i][1]位置
                list.add(people[i][1],people[i]);
            }else{
                //结果集中元素个数小于等于第i个人前面应有的人数时，将第i个人追加到结果集的后面
                list.add(list.size(),people[i]);
            }
        }
        //将list转化为数组，然后返回
        return list.toArray(new int[list.size()][]);
    }
}
