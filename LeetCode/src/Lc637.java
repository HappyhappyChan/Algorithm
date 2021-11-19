import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc637
 * @Description: [637. Average of Levels in Binary Tree (Easy) 一棵树每层节点的平均数]
 * @Author: [clh]
 * @Date: 2021/11/19 20:09
 **/
public class Lc637 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ret = new ArrayList<Double>();
        if(root == null)
            return ret;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            double sum = 0;
            int cnt = 0;
            while(cnt < size){
                TreeNode cur = que.poll();
                sum += cur.val;
                if(cur.left != null)
                    que.offer(cur.left);
                if(cur.right != null)
                    que.offer(cur.right);
                cnt++;
            }
            ret.add(sum/size);
        }
        return ret;
    }

    //2 dfs
    //leetcode solution
    public List<Double> averageOfLevels2(TreeNode root){
        List<Integer> count = new ArrayList<Integer>();
        List<Double> res = new ArrayList<Double>();
        average(root, 0, res, count);
        for(int i = 0; i < res.size(); i++){
            res.set(i, res.get(i)/count.get(i));
        }
        return res;
    }

    private void average(TreeNode t, int i, List<Double> sum, List<Integer> cnt) {
        if(t == null)
            return;
        if(i < sum.size()){
            sum.set(i, sum.get(i)+t.val);
            cnt.set(i, cnt.get(i) + 1);
        }else{
            sum.add(1.0*t.val);
            cnt.add(1);
        }
        average(t.left, i+1, sum, cnt);
        average(t.right, i+1, sum, cnt);
    }


}
