import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz60
 * @Description: [把二叉树打印成多行]
 * @Author: [clh]
 * @Date: 2021/10/12 17:05
 **/
public class Jz78 {
    //和Jz22几乎一模一样

    //mine 错漏百出 算了 直接看解析
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if(pRoot == null)
            return new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int incnt = 1;
        int oucnt = 0;
        que.add(pRoot);
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(pRoot.val);
        ret.add(tmp);
        while(!que.isEmpty()){
            TreeNode node = que.poll();
            oucnt++;
            if(incnt == oucnt){
                ret.add(tmp);
                tmp = new ArrayList<>();
                incnt = 0;
                oucnt = 0;
            }

            if(node.left != null){
                incnt++;
                que.add(node.left);
                tmp.add(node.left.val);
            }
            if(node.right != null){
                incnt++;
                que.add(node.right);
                tmp.add(node.right.val);
            }
            //if(cnt > 0) ret.add(tmp);
        }
        return ret;
    }

    //solution 1 from github
    ArrayList<ArrayList<Integer>> solution1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(pRoot);
        while(!que.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = que.size();
            while(cnt-->0){
                TreeNode node = que.poll();
                if(node == null)
                    continue;
                list.add(node.val);
                que.add(node.left);
                que.add(node.right);
            }
            if(list.size() != 0)
                ret.add(list);
        }
        return ret;
    }
}
