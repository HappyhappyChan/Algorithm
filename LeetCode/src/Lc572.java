import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc572
 * @Description: [572. Subtree of Another Tree (Easy) 子树]
 * @Author: [clh]
 * @Date: 2021/11/18 19:57
 **/
public class Lc572 {
    //搞了半天终于通过了 但才10% 60%
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null || subRoot == null){
            if(root == null && subRoot == null){
                return true;
            }else{
                return false;
            }
        }
        /*if(root.val != subRoot.val)
            return false;*/
        if(root.val == subRoot.val){
            Queue<TreeNode> q1 = new LinkedList<>();
            Queue<TreeNode> q2 = new LinkedList<>();
            q1.add(root);
            q2.add(subRoot);
            boolean flag = true;
            outer:
            while(!q1.isEmpty() && !q2.isEmpty()){
                int s1 = q1.size();
                int s2 = q2.size();
                if(s1 != s2)
                    /*return false;*/
                    break outer;
                while(s1 > 0){
                    TreeNode n1 = q1.poll();
                    TreeNode n2 = q2.poll();
                    if(n1.left != null && n2.left != null){
                        if(n1.left.val != n2.left.val) {
                            /*return false;*/
                            flag = false;
                            break outer;
                        }
                        q1.add(n1.left);
                        q2.add(n2.left);
                    }else if(n1.left == null && n2.left == null){

                    }else{
                        /*return false;*/
                        flag = false;
                        break outer;
                    }
                    if(n1.right != null && n2.right != null){
                        if(n1.right.val != n2.right.val) {
                            /*return false;*/
                            flag = false;
                            break outer;
                        }
                        q1.add(n1.right);
                        q2.add(n2.right);
                    }else if(n1.right == null && n2.right == null){

                    }else{
                        /*return false;*/
                        flag = false;
                        break outer;
                    }
                    s1--;
                }
            }
            if(flag) return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    //2 尝试用递归
    //我的代码还是错的
    public boolean isSubtree2(TreeNode root, TreeNode subRoot){
        if(root == null || subRoot == null){
            if(root == null && subRoot == null){
                return true;
            }else{
                return false;
            }
        }
        if(root == subRoot)
            return true;
        return isSubtree2(root.left, subRoot) || isSubtree2(root.right, subRoot);
    }

    //3 github
    public boolean isSubtree3(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return isSubtreeWithRoot(s, t) || isSubtree3(s.left, t) || isSubtree3(s.right, t);
    }

    private boolean isSubtreeWithRoot(TreeNode s, TreeNode t) {
        if (t == null && s == null) return true;
        if (t == null || s == null) return false;
        if (t.val != s.val) return false;
        return isSubtreeWithRoot(s.left, t.left) && isSubtreeWithRoot(s.right, t.right);
    }

    //4 leetcode-cn
    //dfs+ brute force matching
    public boolean isSubtree4(TreeNode s, TreeNode t) {
        return dfs4(s, t);
    }
    public boolean dfs4(TreeNode s, TreeNode t){
        if(s == null)
            return false;
        return check4(s, t) || dfs4(s.left, t) || dfs4(s.right, t);
    }

    public boolean check4(TreeNode s, TreeNode t){
        if(s == null && t == null)
            return true;
        if(s == null || t == null)
            return false;
        if(s.val != t.val)
            return false;
        return check4(s.left, t.left) && check4(s.right, t.right);
    }

    //5 leetcode-cn
    //dfs + kmp
    List<Integer> sOrder = new ArrayList<Integer>();
    List<Integer> tOrder = new ArrayList<Integer>();
    int maxElement, lnull, rnull;
    public boolean isSubtree5(TreeNode s, TreeNode t){
        maxElement = Integer.MIN_VALUE;
        getMaxElement(s);
        getMaxElement(t);
        lnull = maxElement + 1;
        rnull = maxElement + 2;
        getDfsOrder(s, sOrder);
        getDfsOrder(t, tOrder);
        return kmp();
    }

    private void getMaxElement(TreeNode s) {
        if(s == null)
            return;
        maxElement = Math.max(maxElement, s.val);
        getMaxElement(s.left);
        getMaxElement(s.right);
    }

    //获得深度优先的序列
    private void getDfsOrder(TreeNode s, List<Integer> res) {
        if(s == null)
            return;
        res.add(s.val);
        if(s.left != null){
            getDfsOrder(s.left, res);
        }else{
            res.add(lnull);
        }
        if(s.right != null){
            getDfsOrder(s.right, res);
        }else{
            res.add(rnull);
        }
    }

    //判断一个串是否是另一个串的子串
    public boolean kmp(){
        int slen = sOrder.size(), tlen = tOrder.size();
        int[] fail = new int[tlen];
        Arrays.fill(fail, -1);
        //建表
        for(int i = 1, j = -1; i < tlen; i++){
            while(j != -1 && !(tOrder.get(i).equals(tOrder.get(j+1)))){
                j = fail[j];
            }
            if(tOrder.get(i).equals(tOrder.get(j+1))){
                j++;
            }
            fail[i] = j;
        }
        for(int i = 0, j = -1; i < slen; i++){
            while(j != -1 && !(sOrder.get(i).equals(tOrder.get(j+1)))){
                j = fail[j];
            }
            if(sOrder.get(i).equals(tOrder.get(j+1))){
                j++;
            }
            if(j == tlen-1)
                return true;
        }
        return false;
    }

    //6 leetcode-cn hashmap
    static final int MAX_N = 1005;
    static final int MOD = 1000000007;
    boolean[] vis = new boolean[MAX_N];
    int[] p = new int[MAX_N];
    int tot;
    Map<TreeNode,int[]> hs = new HashMap<>();
    Map<TreeNode, int[]> ht = new HashMap<>();

    public boolean isSubtree6(TreeNode s, TreeNode t){
        getPrime();
        dfs(s, hs);
        dfs(t, ht);
        int tHash = ht.get(t)[0];
        for(Map.Entry<TreeNode, int[]> entry: hs.entrySet()){
            if(entry.getValue()[0] == tHash){
                return true;
            }
        }
        return false;
    }

    public void getPrime(){
        vis[0] = vis[1] = true;
        tot = 0;
        for(int i = 2; i < MAX_N; i++){
            if(!vis[i]){
                p[++tot] = i;
            }
            for(int j = 1; j <= tot && i*p[j] < MAX_N; j++){
                vis[i * p[j]] = true;
                if(i % p[j] == 0)
                    break;
            }
        }
    }

    public void dfs(TreeNode o, Map<TreeNode, int[]> h) {
        h.put(o, new int[]{o.val, 1});
        if (o.left == null && o.right == null) {
            return;
        }
        if (o.left != null) {
            dfs(o.left, h);
            int[] val = h.get(o);
            val[1] += h.get(o.left)[1];
            val[0] = (int) ((val[0] + (31L * h.get(o.left)[0] * p[h.get(o.left)[1]]) % MOD) % MOD);
        }
        if (o.right != null) {
            dfs(o.right, h);
            int[] val = h.get(o);
            val[1] += h.get(o.right)[1];
            val[0] = (int) ((val[0] + (179L * h.get(o.right)[0] * p[h.get(o.right)[1]]) % MOD) % MOD);
        }
    }


}
