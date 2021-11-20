import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc230
 * @Description: [230. Kth Smallest Element in a BST (Medium) 寻找二叉查找树的第 k 个元素]
 * @Author: [clh]
 * @Date: 2021/11/20 15:59
 **/
public class Lc230 {
    //1 我自己写的 迭代
    //Time O(H+k) Space O(H)
    public int kthSmallest(TreeNode root, int k) {
        if(root == null)
            return -1;
        int cnt = 0;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            cnt++;
            if(cnt == k)
                return cur.val;
            cur = cur.right;
        }
        return -1;
    }

    //2 递归
    public int kthSmallest2(TreeNode root, int k){
        List<Integer> ret = new ArrayList<>();
        inorder(root, ret);
        return k > ret.size() ? -1 : ret.get(k-1);
    }

    private void inorder(TreeNode root, List<Integer> ret) {
        if(root == null)
            return;
        inorder(root.left, ret);
        ret.add(root.val);
        inorder(root.right, ret);
    }

    //3 递归 github
    public int kthSmallest3(TreeNode root, int k) {
        int leftCnt = count(root.left);
        if (leftCnt == k - 1) return root.val;
        if (leftCnt > k - 1) return kthSmallest3(root.left, k);
        return kthSmallest3(root.right, k - leftCnt - 1);
    }

    private int count(TreeNode node) {
        if (node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }
}
