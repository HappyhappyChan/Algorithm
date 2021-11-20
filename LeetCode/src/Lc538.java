import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc538
 * @Description: [538. Convert BST to Greater Tree 把二叉查找树每个节点的值都加上比它大的节点的值]
 * @Author: [clh]
 * @Date: 2021/11/20 16:17
 **/
public class Lc538 {
    //1 我自己写的 迭代
    public TreeNode convertBST(TreeNode root) {
        if(root == null)
            return root;
        //右根左遍历
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        int sum = 0;
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                cur = cur.right;
            }
            cur = s.pop();
            int tmp = cur.val;
            cur.val += sum;
            sum += tmp;
            cur = cur.left;
        }
        return root;
    }

    //2 尝试递归
    //这个代码一直有错……
    public TreeNode convertBST2(TreeNode root){
        int sum = 0;
        helper(root, sum);
        return root;
    }

    private void helper(TreeNode root, int sum) {
        if(root == null)
            return;
        helper(root.right, sum);
        int tmp = root.val;
        root.val += sum;
        sum += tmp;
        helper(root.left, sum);
    }

    //3 github 递归
    //这样又对 是为什么……
    private int sum = 0;

    public TreeNode convertBST3(TreeNode root) {
        traver(root);
        return root;
    }

    private void traver(TreeNode node) {
        if (node == null) return;
        traver(node.right);
        sum += node.val;
        node.val = sum;
        traver(node.left);
    }

    //4 morris + reverse
    //没细看
    private TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }

    public TreeNode convertBST4(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            /*
             * If there is no right subtree, then we can visit this node and
             * continue traversing left.
             */
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
            /*
             * If there is a right subtree, then there is at least one node that
             * has a greater value than the current one. therefore, we must
             * traverse that subtree first.
             */
            else {
                TreeNode succ = getSuccessor(node);
                /*
                 * If the left subtree is null, then we have never been here before.
                 */
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                }
                /*
                 * If there is a left subtree, it is a link that we created on a
                 * previous pass, so we should unlink it and visit this node.
                 */
                else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

}
