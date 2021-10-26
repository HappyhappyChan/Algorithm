/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz26
 * @Description: [二叉搜索树与双向链表]
 * @Author: [clh]
 * @Date: 2021/10/12 23:32
 **/
public class Jz36 {
    //输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表

    private TreeNode pre =  null;
    private TreeNode head = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null)
            return pRootOfTree;
        inOrder(pRootOfTree);
        return head;
    }

    private void inOrder(TreeNode node) {
        if(node == null)
            return;
        inOrder(node.left);
        node.left = pre;
        if(pre != null)
            pre.right = node;
        pre = node;
        if(head == null)
            head = node;
        inOrder(node.right);
    }


}
