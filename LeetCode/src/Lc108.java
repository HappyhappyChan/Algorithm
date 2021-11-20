/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: lc108
 * @Description: [108. Convert Sorted Array to Binary Search Tree (Easy) 从有序数组中构造二叉查找树]
 * @Author: [clh]
 * @Date: 2021/11/20 19:59
 **/
public class Lc108 {
    //我自己写的 通过啦！！！
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0)
            return null;
        int len = nums.length;
        int mid = len/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, 0, mid-1);
        root.right = helper(nums, mid + 1, len-1);
        return root;
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if(start < 0 || end >= nums.length || start > end)
            return null;
        if(start == end)
            return new TreeNode(nums[start]);
        //这里如果不加上start就错了！
        int mid = start+ (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid-1);
        root.right = helper(nums, mid + 1, end);
        return root;
    }

    //2 github
    public TreeNode sortedArrayToBST2(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }

    private TreeNode toBST(int[] nums, int sIdx, int eIdx){
        if (sIdx > eIdx) return null;
        int mIdx = (sIdx + eIdx) / 2;
        TreeNode root = new TreeNode(nums[mIdx]);
        root.left =  toBST(nums, sIdx, mIdx - 1);
        root.right = toBST(nums, mIdx + 1, eIdx);
        return root;
    }
}
