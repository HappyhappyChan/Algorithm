import java.util.Arrays;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz23
 * @Description: [二叉搜索树的后序遍历序列]
 * @Author: [clh]
 * @Date: 2021/10/12 20:12
 **/
public class Jz33 {
    //输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
    //假设输入的数组的任意两个数字都互不相同。
    // （ps：我们约定空树不是二叉搜索树）

    //改来改去还是错……
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0)
            return false;
        int len = sequence.length;
        //根结点
        int root = sequence[len - 1];
        //寻找左右子树
        int lef = 0;
        while(lef < len - 1 && sequence[lef] < root){
            lef++;
        }
        //寻找右子树
        int rgt = len - 1;
        while(rgt >= 0 && sequence[rgt] >= root){
            rgt--;
        }
        if(lef == rgt+1)
            return true;
        if(lef > rgt+1)
            return false;
        return VerifySquenceOfBST(Arrays.copyOfRange(sequence,0, lef)) &&
                VerifySquenceOfBST(Arrays.copyOfRange(sequence,rgt++, len));
    }

    //solution 1 from github
    public boolean solution1(int[] seq){
        if(seq == null || seq.length == 0)
            return false;
        return solution1(seq, 0, seq.length - 1);
    }

    private boolean solution1(int[] seq, int first, int last){
        //如果只有2个数 或者更少 则true ?? 试了几个数确实如此……
        if(last - first <= 1)
            return true;
        int root = seq[last];
        int cur = first;
        //寻找左边的边界
        //不明白为什么这里是<=
        //测试发现这里<也可以通过
        while(cur < last && seq[cur] <= root)
            cur++;
        for(int i = cur; i < last; i++){
            //右子树都要大于root
            if(seq[i] < root)
                return false;
        }
        return solution1(seq, first, cur - 1) &&
                solution1(seq, cur, last - 1);
    }
}
