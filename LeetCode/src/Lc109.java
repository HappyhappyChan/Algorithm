import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc109
 * @Description: [109. Convert Sorted List to Binary Search Tree (Medium) 根据有序链表构造平衡的二叉查找树 ]
 * @Author: [clh]
 * @Date: 2021/11/20 20:20
 **/
public class Lc109 {
    //我：将链表转成list 然后用Lc108的套路
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;
        List<Integer> nums = new ArrayList<>();
        while(head != null){
            nums.add(head.val);
            head = head.next;
        }
        return helper(nums, 0, nums.size()-1);
    }

    private TreeNode helper(List<Integer> nums, int start, int end) {
        if(start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = helper(nums, start, mid - 1);
        root.right = helper(nums, mid + 1, end);
        return root;
    }

    // 不转 而是利用快慢指针找到中间点
    // 根据leetcode cn写的 有bug 而且查出来了 看注释
    public TreeNode sortedListToBST2(ListNode head){
        if(head == null)
            return null;
        if(head.next == null)
            return new TreeNode(head.val);
        return helper2(head, null);
    }

    private TreeNode helper2(ListNode left, ListNode right) {
        //要用right来判断 而不是null！
        if(left == right)
            return null;
        if(left.next == right)
            return new TreeNode(left.val);
        ListNode slow = left;
        ListNode fast = left;
        //ListNode pre = left;
        //这里要改成right
        while(slow != right && fast.next != right && fast.next.next != right){
            //pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast.next != null){
            fast = fast.next;
            fast.next = null;
        }
        TreeNode root = new TreeNode(slow.val);
        //pre.next = null;
        //不能这样写 因为不是同一条链上的
        root.left = helper2(left, slow);
        root.right = helper2(slow.next, right);
        return root;
    }

    //3 github
    public TreeNode sortedListToBST3(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode preMid = preMid(head);
        ListNode mid = preMid.next;
        preMid.next = null;  // 断开链表
        TreeNode t = new TreeNode(mid.val);
        t.left = sortedListToBST3(head);
        t.right = sortedListToBST3(mid.next);
        return t;
    }

    private ListNode preMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }

    //4 leetcode-cn 分治+中序遍历
    ListNode globalHead;

    public TreeNode sortedListToBST4(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }

    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

}
