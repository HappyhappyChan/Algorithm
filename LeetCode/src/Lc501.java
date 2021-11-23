import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc501
 * @Description: [501. Find Mode in Binary Search Tree (Easy) 找二叉查找树中出现次数最多的值]
 * @Author: [clh]
 * @Date: 2021/11/22 15:27
 **/
public class Lc501 {
    public int[] findMode(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        TreeNode cur = root;
        Stack<TreeNode> s = new Stack<>();
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            if(map.containsKey(cur.val)){
                map.put(cur.val, map.getOrDefault(cur.val, 0) + 1);
            }else{
                map.put(cur.val, 1);
            }
            max = Math.max(max, map.get(cur.val));
            cur = cur.right;
        }
        for(int key: map.keySet()){
            if(map.get(key) == max){
                ret.add(key);
            }
        }
        int[] ans = new int[ret.size()];
        for(int i = 0; i < ret.size(); i++){
            ans[i] = ret.get(i);
        }
        return ans;
    }

    //2 递归
    int curcnt = 1;
    int maxcnt = 1;
    TreeNode pre = null;
    public int[] findMode2(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        inOrder(root, ans);
        int[] ret = new int[ans.size()];
        int i = 0;
        for(int num: ans){
            ret[i++] = num;
        }
        return ret;
    }

    private void inOrder(TreeNode node, List<Integer> nums) {
        if (node == null) return;
        inOrder(node.left, nums);
        if(pre != null){
            if(node.val == pre.val)
                curcnt++;
            else
                curcnt = 1;
        }
        if(curcnt > maxcnt){
            maxcnt = curcnt;
            nums.clear();
            nums.add(node.val);
        }else if(curcnt == maxcnt){
            nums.add(node.val);
        }
        pre = node;
        inOrder(node.right, nums);
    }


}
