import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc617
 * @Description: [617. Merge Two Binary Trees (Easy) 归并两棵树]
 * @Author: [clh]
 * @Date: 2021/11/18 10:10
 **/
public class Lc617 {

    //1 Queue
    //有bug
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode ret  = new TreeNode();
        if(root1 == null && root2 == null)
            return ret;
        if(root1 == null){
            ret = root2;
            return ret;
        }
        if(root2 == null){
            ret = root1;
            return ret;
        }

        Queue<TreeNode> que1 = new LinkedList<TreeNode>();
        Queue<TreeNode> que2 = new LinkedList<TreeNode>();
        Queue<TreeNode> que3 = new LinkedList<TreeNode>();
        que1.add(root1);
        que2.add(root2);
        ret.val = root1.val + root2.val;
        que3.add(ret);
        int flag = Integer.MIN_VALUE;
        //while(!que1.isEmpty() && !que2.isEmpty() && !que3.isEmpty()){
        while(!que1.isEmpty() && !que2.isEmpty() && !que3.isEmpty()){
            int size1 = que1.size();
            int size2 = que2.size();
            int size3 = que3.size();
            while(size3 > 0){
                TreeNode n1 = que1.poll();
                TreeNode n2 = que2.poll();
                TreeNode n3 = que3.poll();
                /*if(n1.val == flag && n2.val == flag)
                    n3.val = flag;
                else
                    n3.val = n1.val == flag ? 0 : n1.val + n2.val == flag? 0 : n2.val;
                que3.add(new TreeNode());*/
                size1--;
                size2--;
                size3--;
                if(n3 == null)
                    continue;
                if(n1.left != null && n2.left != null && n1.left.val != flag && n2.left.val != flag){
                    que1.add(n1.left);
                    que2.add(n2.left);
                    n3.left = new TreeNode(n1.left.val + n2.left.val);
                }else if(n1.left == null && n2.left != null && n2.left.val != flag){
                    que1.add(new TreeNode(flag));
                    que2.add(n2.left);
                    n3.left = new TreeNode(0 + n2.left.val);
                }else if(n1.left != null && n2.left == null && n1.left.val != flag){
                    que2.add(new TreeNode(flag));
                    que1.add(n1.left);
                    n3.left = new TreeNode(0 + n1.left.val);
                }else{
                    /*que1.add(new TreeNode(flag));
                    que2.add(new TreeNode(flag));*/
                    n3.left = null;
                }
                if(n1.right != null && n2.right != null && n1.right.val != flag && n2.right.val != flag){
                    que1.add(n1.right);
                    que2.add(n2.right);
                    n3.right = new TreeNode(n1.right.val + n2.right.val);
                }else if(n1.right == null && n2.right != null && n2.right.val != flag){
                    que1.add(new TreeNode(flag));
                    que2.add(n2.right);
                    n3.right = new TreeNode(0 + n2.right.val);
                }else if(n1.right != null && n2.right == null && n1.right.val != flag){
                    que2.add(new TreeNode(flag));
                    que1.add(n1.right);
                    n3.right = new TreeNode(n2.right.val + 0);
                }else{
                    /*que1.add(new TreeNode(flag));
                    que2.add(new TreeNode(flag));*/
                    n3.right = null;
                }
                que3.add(n3.left);
                que3.add(n3.right);
            }
        }
        return ret;
    }

    //2 还是有bug
    //案例通过了 但是还是没AC 不知道bug在哪……
/*    Input
[1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2]
        [1,null,1,null,1,null,1,null,1,null,1,2]
    Output
[2,null,2]
    Expected
[2,null,2,null,2,null,2,null,2,null,2,2,1,null,null,null,1,null,1,null,1,null,1,2]*/
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        TreeNode ret  = new TreeNode();
        if(root1 == null && root2 == null){
           return null;
        }
        if(root1 == null){
            ret = root2;
            return ret;
        }
        if(root2 == null){
            ret = root1;
            return ret;
        }

        Queue<TreeNode> que1 = new LinkedList<TreeNode>();
        Queue<TreeNode> que2 = new LinkedList<TreeNode>();
        Queue<TreeNode> que3 = new LinkedList<TreeNode>();
        que1.add(root1);
        que2.add(root2);
        ret.val = root1.val + root2.val;
        que3.add(ret);
        int flag = Integer.MIN_VALUE;
        //while(!que1.isEmpty() && !que2.isEmpty() && !que3.isEmpty()){
        while(!que1.isEmpty() && !que2.isEmpty() && !que3.isEmpty()){
            int size1 = que1.size();
            int size2 = que2.size();
            int size3 = que3.size();
            while(size3 > 0){
                TreeNode n1 = que1.poll();
                TreeNode n2 = que2.poll();
                TreeNode n3 = que3.poll();
                /*if(n1.val == flag && n2.val == flag)
                    n3.val = flag;
                else
                    n3.val = n1.val == flag ? 0 : n1.val + n2.val == flag? 0 : n2.val;
                que3.add(new TreeNode());*/
                size1--;
                size2--;
                size3--;
                if(n3 == null)
                    continue;
                if(n1 != null && n2 != null && n1.val != flag && n2.val != flag && n1.left != null && n2.left != null ){
                    que1.add(n1.left);
                    que2.add(n2.left);
                    n3.left = new TreeNode(n1.left.val + n2.left.val);
                }else if( n2 != null && n2.val != flag && n1.left == null && n2.left != null ){
                    que1.add(new TreeNode(flag));
                    que2.add(n2.left);
                    n3.left = new TreeNode(0 + n2.left.val);
                }else if(n1 != null && n1.val != flag && n1.left != null && n2.left == null ){
                    que2.add(new TreeNode(flag));
                    que1.add(n1.left);
                    n3.left = new TreeNode(0 + n1.left.val);
                }else{
                    /*que1.add(new TreeNode(flag));
                    que2.add(new TreeNode(flag));*/
                    n3.left = null;
                }

                if(n1 != null && n2 != null && n1.val != flag && n2.val != flag && n1.right != null && n2.right != null){
                    que1.add(n1.right);
                    que2.add(n2.right);
                    n3.right = new TreeNode(n1.right.val + n2.right.val);
                }else if( n2 != null&& n2.val != flag && n1.right == null && n2.right != null ){
                    que1.add(new TreeNode(flag));
                    que2.add(n2.right);
                    n3.right = new TreeNode(0 + n2.right.val);
                }else if(n1 != null && n1.val != flag && n1.right != null && n2.right == null){
                    que2.add(new TreeNode(flag));
                    que1.add(n1.right);
                    n3.right = new TreeNode(n1.right.val + 0);
                }else{
                    /*que1.add(new TreeNode(flag));
                    que2.add(new TreeNode(flag));*/
                    n3.right = null;
                }
                que3.add(n3.left);
                que3.add(n3.right);
            }
        }
        return ret;
    }

    //3 github
    //recursion
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees3(t1.left, t2.left);
        root.right = mergeTrees3(t1.right, t2.right);
        return root;
    }

    //4 iterative method
    public TreeNode mergeTrees4(TreeNode t1, TreeNode t2){
        if(t1 == null) return t2;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});
        while(!stack.isEmpty()){
            TreeNode[] t = stack.pop();
            if(t[0] == null ||t[1] == null)
                continue;
            t[0].val += t[1].val;
            if(t[0].left == null){
                t[0].left = t1.left;
            }else{
                stack.push(new TreeNode[]{t[0].left, t[1].left});
            }
        }
        return t1;
    }

    //5 bfs
    public TreeNode mergeTrees5(TreeNode t1, TreeNode t2){
        if(t1 == null)
            return t2;
        if(t2 == null)
            return t1;
        TreeNode ret = new TreeNode(t1.val + t2.val);
        Queue<TreeNode> que = new LinkedList<>();
        Queue<TreeNode> que1 = new LinkedList<>();
        Queue<TreeNode> que2 = new LinkedList<>();
        que.offer(ret);
        que1.offer(t1);
        que2.offer(t2);
        while(!que1.isEmpty() && !que2.isEmpty()){
            TreeNode p = que.poll(), p1 = que1.poll(), p2 = que2.poll();
            TreeNode l1 = p1.left, l2 = p2.left, r1 = p1.right, r2 = p2.right;
            if(l1 != null || l2 != null){
                if(l1 != null && l2 != null){
                    TreeNode left = new TreeNode(l1.val + l2.val);
                    p.left = left;
                    que.offer(left);
                    que1.offer(l1);
                    que2.offer(l2);
                }else if(l1 != null){
                    p.left = l1;
                }else if(l2 != null){
                    p.left = l2;
                }
            }
            if(r1 != null || r2 != null){
                if(r1 != null && r2 != null){
                    TreeNode right = new TreeNode(r1.val + r2.val);
                    p.right = right;
                    que.offer(right);
                    que1.offer(r1);
                    que2.offer(r2);
                }else if(r1 != null){
                    p.right = r1;
                }else if(r2 != null){
                    p.right = r2;
                }
            }
        }
        return ret;
    }
}
