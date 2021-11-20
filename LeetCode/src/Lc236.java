import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc236
 * @Description: [236. Lowest Common Ancestor of a Binary Tree 二叉树的最近公共祖先]
 * @Author: [clh]
 * @Date: 2021/11/20 18:55
 **/
public class Lc236 {

    //我好早之前写的
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        //用map存储父亲指针
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        //当 p q都在map里面的时候退出循环
        while(!parent.containsKey(p) || !parent.containsKey(q)){
            TreeNode tmp = stack.pop();

            if(tmp.left != null){
                //<孩子, 祖先>
                parent.put(tmp.left, tmp);
                stack.push(tmp.left);
            }

            if(tmp.right != null){
                parent.put(tmp.right, tmp);
                stack.push(tmp.right);
            }
        }
        //p结点的祖先集合
        Set<TreeNode> ancestor = new HashSet<>();

        while(p != null){
            ancestor.add(p);
            p = parent.get(p);
        }

        while(!ancestor.contains(q)){
            q = parent.get(q);
        }
        return q;
    }

    //我一开始想根据先根遍历的list 然后找到前一个即为祖先
    //但是发现不行 因为画个图就知道 不能单纯返回i-1 因为有可能也是i啊
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return root;
        if(root.val == p.val || root.val == q.val)
            return root;
        List<TreeNode> ltree = new ArrayList<>();
        List<TreeNode> rtree = new ArrayList<>();
        boolean[] f1 = new boolean[2];
        boolean[] f2 = new boolean[2];
        preorder(root.left, ltree, f1, p, q);
        preorder(root.right, rtree, f2, p, q);
        if(f1[0] && f1[1]){
            for(int i = 0; i < ltree.size(); i++){
                if(ltree.get(i) == p || ltree.get(i) == q)
                    return ltree.get(i-1);
            }
        }
        if(f2[0] && f2[1]){
            for(int i = 0; i < rtree.size(); i++){
                if(rtree.get(i) == p || rtree.get(i) == q)
                    return rtree.get(i-1);
            }
        }
        return root;
    }

    private void preorder(TreeNode node, List<TreeNode> tree, boolean[] f, TreeNode p, TreeNode q) {
        if(node == null)
            return;
        tree.add(node);
        if(node.val == p.val){
            f[0] = true;
        }
        if(node.val == q.val){
            f[1] = true;
        }
        if(f[0] && f[1])
            return;
        preorder(node.left, tree, f, p, q);
        preorder(node.right, tree, f, p, q);
    }

    //github 真绝
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    //4 leetcode 递归
    private TreeNode ans = null;
    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;


        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }

    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.ans;
    }

    //5 iterative with parent pointer
    public TreeNode lowestCommonAncestor5(TreeNode root, TreeNode p, TreeNode q) {

        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}
