import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz58
 * @Description: [对称的二叉树]
 * @Author: [clh]
 * @Date: 2021/10/12 16:23
 **/
public class Jz28 {

    //mine 有误
    //{8,6,6,5,7,7,5} true 但是我却是false
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null)
            return true;
        if(pRoot.left != null && pRoot.right == null)
            return false;
        if(pRoot.right != null && pRoot.left == null)
            return false;
        if(pRoot.right.val == pRoot.left.val)
            return isSymmetrical(pRoot.left) && isSymmetrical(pRoot.right);
        return false;
    }

    //solution 1 from github
    boolean solution1(TreeNode pRoot){
        if(pRoot == null)
            return true;
        return solution1(pRoot.left, pRoot.right);
    }

    boolean solution1(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null)
            return true;
        //这里return false 因为t1 == null t2==null同时成立不满足
        if(t1 == null || t2 == null) {
            return false;
        }
        if(t1.val != t2.val)
            return false;
        //这里要求对称 是左和右相等
        return solution1(t1.left, t2.right) && solution1(t1.right, t2.left);
    }


    //solution 2 from newcoder
    boolean solution2(TreeNode proot){
        if(proot == null)
            return true;
        Queue<TreeNode> lq = new LinkedList<>();
        Queue<TreeNode> rq = new LinkedList<>();
        lq.add(proot.left);
        rq.add(proot.right);
        while(!lq.isEmpty() && !rq.isEmpty()){
            TreeNode lnode = lq.poll();
            TreeNode rnode = rq.poll();
            if(lnode == null && rnode == null)
                continue;
            if(lnode == null || rnode == null)
                return false;
            if(lnode.val != rnode.val)
                return false;
            // 左子树从左往右添加节点
            lq.add(lnode.left);
            lq.add(lnode.right);
            // 右子树从右往左添加结点
            rq.add(rnode.right);
            rq.add(rnode.left);
        }
        return lq.isEmpty() && rq.isEmpty();
    }
}
