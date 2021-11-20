import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc669
 * @Description: [669. Trim a Binary Search Tree (Easy) 修剪二叉查找树]
 * @Author: [clh]
 * @Date: 2021/11/20 11:21
 **/
public class Lc669 {
    //1 我写的 一直有错
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null)
            return null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while(cur != null && cur.val < low){
            root = root.right;
            cur = root;
        }
        while(cur != null && cur.val > high){
            root = root.left;
            cur = root;
        }
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                if(cur.left != null && cur.left.val < low) {
                    cur.left = cur.left.right;
                }else {
                    cur = cur.left;
                }
            }
            cur = s.pop();
            if(cur != null && cur.right != null){
                if(cur.right.val > high){
                    cur.right = cur.right.left;
                }
            }
            cur = cur.right;
        }
        return root;
    }

    //2 leetcode recursion
    public TreeNode trimBST2(TreeNode root, int low, int high) {
        if (root == null) return root;
        if (root.val > high) return trimBST2(root.left, low, high);
        if (root.val < low) return trimBST2(root.right, low, high);

        root.left = trimBST2(root.left, low, high);
        root.right = trimBST2(root.right, low, high);
        return root;
    }

    //3 leetcode discuss iteration inorder
    public TreeNode trimBST3(TreeNode root, int low, int high) {
        while(root!=null&&(root.val<low||root.val>high)){
            if(root.val<low)root=root.right;
            if(root.val>high)root=root.left;
        }
        TreeNode newRoot=root;
        Stack<TreeNode> stack=new Stack<>();
        while(root!=null||!stack.isEmpty()){
            while(root!=null){
                boolean matchInterval=true;
                if(root.left!=null&&root.left.val<low)
                    {
                        root.left=root.left.right;
                        matchInterval=false;
                    }
                if(root.right!=null&&root.right.val>high){
                    root.right=root.right.left;
                    matchInterval=false;
                }
                if(matchInterval){
                    stack.push(root);
                    root=root.left;
                }
            }
            root=stack.pop();
            root=root.right;
        }
        return newRoot;
    }

    //4 leetcode discuss iteration
    public TreeNode trimBST4(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }
        //Find a valid root which is used to return.
        // 处理头结点，让root移动到[L, R] 范围内，注意是左闭右闭
        while (root.val < L || root.val > R) {
            if (root.val < L) {
                root = root.right;
            }
            if (root.val > R) {
                root = root.left;
            }
        }
        TreeNode dummy = root;
        // Remove the invalid nodes from left subtree.
        // 此时root已经在[L, R] 范围内，处理左孩子元素小于L的情况
        while (dummy != null) {
            while (dummy.left != null && dummy.left.val < L) {
                dummy.left = dummy.left.right;
                // If the left child is smaller than L, then we just keep the right subtree of it.
            }
            dummy = dummy.left;
        }
        dummy = root;
        // Remove the invalid nodes from right subtree
        // 此时root已经在[L, R] 范围内，处理右孩子大于R的情况
        while (dummy != null) {
            while (dummy.right != null && dummy.right.val > R) {
                dummy.right = dummy.right.left;
                // If the right child is biggrt than R, then we just keep the left subtree of it.
            }
            dummy = dummy.right;
        }
        return root;
    }

    //5 leetcode discuss iteration preorder
    public TreeNode trimBST5(TreeNode root, int low, int high) {
        while(root!=null&&(root.val<low||root.val>high)){
            if(root.val<low)root=root.right;
            if(root.val>high)root=root.left;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node=stack.pop();
            boolean matchInterval=true;
            if(node!=null&&node.left!=null&&node.left.val<low){
                node.left=node.left.right;matchInterval=false;
            }
            if(node!=null&&node.right!=null&&node.right.val>high){
                node.right=node.right.left;matchInterval=false;
            }
            if(matchInterval){
                if(node.left!=null){
                    stack.push(node.left);
                }
                if(node.right!=null){
                    stack.push(node.right);
                }
                matchInterval=false;
            }else{
                stack.push(node);
            }
        }
        return root;
    }
}
