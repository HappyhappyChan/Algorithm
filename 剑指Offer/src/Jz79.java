/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz39
 * @Description: [平衡二叉树]
 * @Author: [clh]
 * @Date: 2021/10/13 16:42
 **/
public class Jz79 {
    private boolean isBalanced = true;

    public boolean IsBalanced_Solution(TreeNode root) {
        height(root);
        return isBalanced;
    }

    private int height(TreeNode root) {
        if (root == null || !isBalanced)
            return 0;
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1)
            isBalanced = false;
        return 1 + Math.max(left, right);
    }

    //solution 2 from newcoder 对上面balanceDepth的优化
    public int TreeDepth(TreeNode root) {
        if(root == null)
            return 0;
        int l = TreeDepth(root.left);
        if(l == -1)  return -1;  // 提前返回
        int r = TreeDepth(root.right);
        if(r == -1)  return -1;  // 提前返回
        if(Math.abs(l-r) > 1){
            isBalanced = false;  // 不是平衡树
            return -1;  // 加一个标记-1，已经不可能是平衡树了
        }
        return Math.max(l,r)+1;
    }

    //solution 3 from newcoder dfs
    public boolean solution3(TreeNode root) {
        if (root == null) return true;
        //判断左子树和右子树是否符合规则，且深度不能超过2
        return solution3(root.left) && solution3(root.right) && Math.abs(deep(root.left) - deep(root.right)) < 2;
    }
    //判断二叉树深度
    public int deep(TreeNode root) {
        if (root == null) return 0;
        return Math.max(deep(root.left), deep(root.right)) + 1;
    }
}
