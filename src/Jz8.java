import java.util.ArrayList;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz57
 * @Description: [二叉树的下一个结点]
 * @Author: [clh]
 * @Date: 2021/10/12 15:02
 **/

/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/

public class Jz8 {
    //给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
    //中序遍历：先遍历左子树 再遍历根结点 再遍历右子树

    //solution 1 from github
    //中序遍历
    void traverse(TreeNode root){
        if(root == null) return;
        traverse(root.left);
        //visit(root); //这里应该是要有一个方法
        traverse(root.right);
    }

    public TreeLinkNode solution1(TreeLinkNode pnode){
        if(pnode.right != null){
            TreeLinkNode node = pnode.right;
            //有右子树，下一结点是右子树中的最左结点
            while(node.left != null){
                node = node.left;
            }
            return node;
        }else{
            while(pnode.next != null){
                TreeLinkNode parent = pnode.next;
                //无右子树，且结点是该结点父结点的左子树，则下一结点是该结点的父结点
                if(parent.left == pnode)
                    return parent;
                //无右子树，且结点是该结点父结点的右子树，则我们一直沿着父结点追朔，直到找到某个结点是其父结点的左子树
                pnode = pnode.next;
            }
        }
        return null;
    }

    //solution 2 from newcoder 还原二叉树
    //找到根结点，对树进行中序遍历，根据遍历结果找到给定结点的下一结点
    static ArrayList<TreeLinkNode> list = new ArrayList<>();
    public TreeLinkNode solution2(TreeLinkNode pnode){
        TreeLinkNode par = pnode;
        //先根据所给结点找到根结点
        while(par.next != null){
            par = par.next;
        }
        //调用函数对根结点进行中序遍历
        InOrder(par);
        //遍历中序的结果找到下个
        for(int i = 0; i < list.size(); i++){
            if(pnode == list.get(i)){
                return i == list.size() - 1? null : list.get(i+1);
            }
        }
        return null;
    }

    private void InOrder(TreeLinkNode pnode) {
        if(pnode != null){
            InOrder(pnode.left);
            list.add(pnode);
            InOrder(pnode.right);
        }
    }

}
