import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc653
 * @Description: [653. Two Sum IV - Input is a BST 在二叉查找树中寻找两个节点，使它们的和为一个给定值]
 * @Author: [clh]
 * @Date: 2021/11/20 22:40
 **/
public class Lc653 {
    //1 一直报错
    //改了半天 终于通过了！
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;
        int next = k - root.val;
        if(next < root.val){
            if(find(root.left, next))
                return true;
        }else if(next > root.val){
            if(find(root.right, next))
                return true;
        }
        return findTarget(root.left, root, k) || findTarget(root.right, root, k);
    }
    public boolean findTarget(TreeNode left, TreeNode right, int k) {
        if(left == null)
            return false;
        int next = k - left.val;
        if(next < left.val){
            if(find(left.left, next))
                return true;
        }else if(next > left.val){
            if(find(left.right, next) || find(right, next))
                return true;
        }
        return findTarget(left.left, right, k) || findTarget(left.right, right, k);
    }
    private boolean find(TreeNode node, int num) {
        if(node == null)
            return false;
        if(node.val == num)
            return true;
        if(node.val < num){
            return find(node.right, num);
        }
        return find(node.left, num);
    }

    //2 放弃了 用回最原始的办法
    //Time Complexity: O(n), Space Complexity: O(n).
    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            int sum = nums.get(i) + nums.get(j);
            if (sum == k) return true;
            if (sum < k) i++;
            else j--;
        }
        return false;
    }

    private void inOrder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }

    //3 leetcode discuss
    //Time Complexity: O(n), Space Complexity: O(n).
    public boolean findTarget3(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if(root == null)return false;
        if(set.contains(k - root.val))return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    //4 leetcode discuss
    //Time Complexity: O(nh), Space Complexity: O(h)
    public boolean findTarget4(TreeNode root, int k) {
        return dfs(root, root,  k);
    }

    public boolean dfs(TreeNode root,  TreeNode cur, int k){
        if(cur == null)return false;
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }

    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null)return false;
        return (root.val == value) && (root != cur)
                || (root.val < value) && search(root.right, cur, value)
                || (root.val > value) && search(root.left, cur, value);
    }

    //5 leetcode dicuss
    // time: O(nlogn), space: O(1)
    public boolean findTarget5(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        TreeNode start = root;
        TreeNode end = root;
        while (start.left != null) {
            start = start.left;
        }
        while (end.right != null) {
            end = end.right;
        }
        while (start != end) {
            int sum = start.val + end.val;
            if (sum > k) {
                end = findPredecessor(root, end);
            } else if (sum < k) {
                start = findSuccessor(root, start);
            } else {
                return true;
            }
        }
        return false;
    }
    private TreeNode findPredecessor(TreeNode root, TreeNode node) {
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < node.val) {
                pre = cur;
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return pre;
    }
    private TreeNode findSuccessor(TreeNode root, TreeNode node) {
        TreeNode succ = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > node.val) {
                succ = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return succ;
    }
}
