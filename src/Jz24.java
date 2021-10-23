import java.util.ArrayList;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz34
 * @Description: [二叉树中和为某一值的路径]
 * @Author: [clh]
 * @Date: 2021/10/12 21:11
 **/
public class Jz24 {
    //路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    //在返回值的list中，数组长度大的数组靠前
    //但其实题没有校验数组的长度

    //solution 1 from newcoder
    //运用递归
    private ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> solution1(TreeNode root,int target) {
        if(root == null)
            return result;
        list.add(root.val);
        target -= root.val;
        //说明是叶子节点了
        if(target == 0 && root.left == null && root.right == null)
            result.add(new ArrayList<Integer>(list));
//因为在每一次的递归中，我们使用的是相同的result引用，所以其实左右子树递归得到的结果我们不需要关心，
//可以简写为FindPath(root.left, target)；FindPath(root.right, target)；
//但是为了大家能够看清楚递归的真相，此处我还是把递归的形式给大家展现了出来。
        ArrayList<ArrayList<Integer>> result1 = solution1(root.left, target);
        ArrayList<ArrayList<Integer>> result2 = solution1(root.right, target);
       //因为当本次递归结束返回上一层的时候，我们已经遍历完这个节点的左右子树，也就是已经该树中可能存在的路径
        // 再次返回上一层的时候要把这个节点挪除去，这样在遍历上一个节点的其他子树的时候遍历的结果才是对的
        //看newcoder评论慢慢悟吧
        //remove(第几个元素)
        list.remove(list.size()-1);
        return result;
    }

}
