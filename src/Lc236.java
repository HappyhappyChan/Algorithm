import java.util.*;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: lc236
 * @Description: [Lowest Common Ancestor of a Binary Tree]
 * @Author: [clh]
 * @Date: 2021/10/22 15:03
 **/
public class Lc236 {

    //solution 1: from lc  用递归遍历
    private TreeNode ans = null;

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        //遍历树
        this.recurseTree(root, p, q);
        return this.ans;
    }

    private boolean recurseTree(TreeNode cur, TreeNode p, TreeNode q) {
        if(cur == null)
            return false;
        //对左子树进行递归 如果左子树返回true
        int left = this.recurseTree(cur.left, p, q)? 1 : 0;

        //对右子树进行递归，如果右子树返回true 说明含有该结点
        int right = this.recurseTree(cur.right, p, q) ? 1 : 0;

        //如果当前结点就是其中一个结点
        int mid = (cur == p || cur == q)? 1 : 0;

        if(mid + left + right >= 2){
            this.ans = cur;
        }

        //只要这三种情况其中一种符合，则返回true
        return (mid + left + right > 0);
    }

    //solution 2: from lc by iterative
    public TreeNode  lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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

    //solution 3 from github
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    //solution 4 from lc
}
