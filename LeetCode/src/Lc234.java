import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc234
 * @Description: [234. Palindrome Linked List (Easy) 回文链表]
 * @Author: [clh]
 * @Date: 2021/11/17 11:10
 **/
public class Lc234 {
    //我之前写的
    public boolean isPalindrome(ListNode head) {
        if(head == null) {
            return true;
        }
        //1.遍历确定长度
        int length = 0;
        ListNode p = head;
        while(p != null) {
            length ++;
            p = p.next;
        }
        p = head;//用完之后, p归位
        if(length == 1) {
            return true;
        }
        //2.将后半部分链反转
        int half = (length + 1) / 2;
        ListNode q = head;
        for(int i = 0; i < half; i ++) {
            q = q.next;
        }
        //开始反转
        ListNode r = q.next;
        q.next = null;
        ListNode m;
        while(r != null) {
            m = r.next;
            r.next = q;
            q = r;
            r = m;
        }
        //3.依次比较,直到其中一个或者两个链遍历完
        while(q != null && p != null) {
            if(p.val == q.val) {
                q = q.next;
                p = p.next;
            }else {
                return false;
            }

        }
        return true;
    }

    //这样一直不能通过，可能是因为reverse的时候把head那条链也改了
    //将reverse修改后就可以AC了
    public boolean isPalindrome1(ListNode head){
        if(head == null || head.next == null)
            return true;
        ListNode l1 = head;
        ListNode l2 = reverse(head);
        while(l1 != null && l2 != null){
            if(l1.val != l2.val)
                return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    private ListNode reverse(ListNode l) {
        //不能单纯的反转数组 要返回一个新的链
        if(l == null || l.next == null)
            return l;
        ListNode phead = new ListNode(-1);
        while(l != null){
            ListNode l2 = new ListNode(l.val);
            ListNode cur = l.next;
            ListNode next = phead.next;
            l2.next = next;
            phead.next = l2;
            l = cur;
        }
        return phead.next;
    }

    //ArrayList+双指针
    public boolean isPalindrome2(ListNode head){
        if(head == null || head.next == null)
            return true;
        List<Integer> list = new ArrayList<Integer>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur.val);
            cur = cur.next;
        }
        int h = list.size()-1;
        int l = 0;
        while(l <= h){
            if(list.get(l) != list.get(h))
                return false;
            l++;
            h--;
        }
        return true;
    }

    //快慢指针
    public boolean isPalindrome3(ListNode head){
        if(head == null || head.next == null)
            return true;
        ListNode phead = new ListNode(-1);
        phead.next = head;
        ListNode fast = head;
        ListNode slow = head;
        while(slow != null && fast != null && fast.next != null){
            fast = fast.next.next;
            ListNode next = phead.next;
            ListNode cur = slow.next;
            slow.next = next;
            phead.next = slow;
            slow = cur;
        }
        //slow对应的是后半段
        if(fast != null) slow = slow.next; //[1,0,1]
        ListNode cur = phead.next;
        while(slow != null){
            if(cur.val != slow.val)
                return false;
            cur = cur.next;
            slow = slow.next;
        }

        //将链表恢复原状
        fast = phead.next;
        slow = phead.next;
        while(slow != null && fast != null && fast.next != null){
            fast = fast.next.next;
            ListNode next = phead.next;
            ListNode tmp = slow.next;
            slow.next = next;
            phead.next = slow;
            slow = tmp;
        }
        return true;
    }

    //4 leetcode-cn
    public boolean isPalindrome4(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
