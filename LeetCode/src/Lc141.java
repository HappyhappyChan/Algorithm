import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc141
 * @Description: []
 * @Author: [clh]
 * @Date: 2021/10/27 10:54
 **/
public class Lc141 {
    //我之前写的
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while(head != null) {
            if(nodesSeen.contains(head)) {
                return true;
            }else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    //双指针 我自己写的
    public boolean hasCycle2(ListNode head){
        if(head == null)
            return false;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null && slow != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                return true;
        }
        return false;
    }
}
