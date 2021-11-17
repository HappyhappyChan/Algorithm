import java.util.Stack;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc445
 * @Description: [445. Add Two Numbers II (Medium) 链表求和]
 * @Author: [clh]
 * @Date: 2021/11/17 10:06
 **/
public class Lc445 {
    // 我写的 一直报错……
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        int len1 = 0; //l1长度
        int len2 = 0; //l2长度
        ListNode p1 = l1, p2 = l2;
        while(p1 != null){
            len1++;
            p1 = p1.next;
        }

        while(p2 != null){
            len2++;
            p2 = p2.next;
        }
        //是否需要进位
        boolean flag = false;
        //长度差
        int diff = 0;
        if(len1 >= len2){
            p1 = l1;
            p2 = l2;
            diff = len1 - len2;
        }else{
            p1 = l2;
            p2 = l1;
            diff = len2 - len1;
        }
        ListNode res1 = new ListNode(0);
        ListNode cur1 = res1;
        ListNode res2 = new ListNode(0);
        ListNode cur2 = res2;
        if(diff > 0) {
            while(diff > 0 && p1 != null){
                cur1.next = new ListNode(p1.val);
                cur2.next = new ListNode(0);
                p1 = p1.next;
                cur1 = cur1.next;
                cur2 = cur2.next;
                diff--;
            }
        }
        while(p1 != null && p2!= null){
            cur1.next = new ListNode((p1.val+p2.val)%10);
            cur2.next = new ListNode((p1.val+p2.val)/10);
            if((p1.val+p2.val)/10 == 1)
                flag = true;
            cur1 = cur1.next;
            cur2 = cur2.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        int g = 0;
        while(flag){
            p1 = res1.next;
            p2 = res2.next;
            flag = false;
            while(p1 != null && p2!= null){
                g += p1.val+p2.val;
                cur1.next = new ListNode((g)%10);
                cur2.next = new ListNode((g)/10);
                if(g/10 == 1)
                    flag = true;
                g /= 10;
                cur1 = cur1.next;
                cur2 = cur2.next;
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        if(g == 1){
            ListNode tmp = new ListNode(1);
            tmp.next = res1.next;
            res1.next = tmp;
        }
        return res1.next;
    }

    //2 github stack
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        Stack<Integer> s1 = buildStack(l1);
        Stack<Integer> s2 = buildStack(l2);
        ListNode head = new ListNode(-1);
        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty() || carry != 0){
            int x = s1.isEmpty()? 0 : s1.pop();
            int y = s2.isEmpty()? 0 : s2.pop();
            int sum = x + y + carry;
            ListNode node = new ListNode(sum % 10);
            node.next = head.next;
            head.next = node;
            carry = sum /10;
        }
        return head.next;
    }

    private Stack<Integer> buildStack(ListNode l) {
        Stack<Integer> stack = new Stack<>();
        while(l != null){
            stack.push(l.val);
            l = l.next;
        }
        return stack;
    }

    //3 leetcode-cn 反转
    //我根据思路写的
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2){
        ListNode h1 = reverse(l1);
        ListNode h2 = reverse(l2);
        int carry = 0;
        ListNode phead = new ListNode(-1);
        while(h1 != null || h2 != null || carry != 0){
            int x = h1 == null ? 0 : h1.val;
            int y = h2 == null ? 0 : h2.val;
            int sum = x + y + carry;
            ListNode node = new ListNode(sum%10);
            node.next = phead.next;
            phead.next = node;
            carry = sum / 10;
            h1 = h1 == null ? h1 : h1.next;
            h2 = h2 == null ? h2 : h2.next;
        }
        return phead.next;
    }

    private ListNode reverse(ListNode l) {
        if(l == null || l.next == null)
            return l;
//        ListNode p = new ListNode(-1);
        ListNode next = l.next;
        ListNode newhead = reverse(l.next);
        next.next = l;
//        p.next = newhead;
        l.next = null;
        return newhead;
    }

    //4 leetcode-cn上面的
    public ListNode addTwoNumbers4(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode newHead = new ListNode(0);
        ListNode ptr = newHead;
        while(l1 != null && l2 != null){
            ptr.val = l1.val + l2.val + ptr.val;
            if(ptr.val > 9){
                ptr.val -= 10;
                ptr.next = new ListNode(1);
            }else{
                ptr.next = new ListNode(0);
            }
            ptr = ptr.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            ptr.val = ptr.val + l1.val;
            if(ptr.val > 9){
                ptr.val -= 10;
                ptr.next = new ListNode(1);
            }else{
                ptr.next = new ListNode(0);
            }
            ptr = ptr.next;
            l1 = l1.next;
        }
        while(l2 != null){
            ptr.val = ptr.val + l2.val;
            if(ptr.val > 9){
                ptr.val -= 10;
                ptr.next = new ListNode(1);
            }else{
                ptr.next = new ListNode(0);
            }
            ptr = ptr.next;
            l2 = l2.next;
        }
        ListNode res = reverse(newHead);
        return res.val == 0? res.next : res;
    }

}
