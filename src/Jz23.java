import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz55
 * @Description: [链表中环的入口结点]
 * @Author: [clh]
 * @Date: 2021/10/11 22:21
 **/
public class Jz23 {
    //solution 1 from bilibili 剑指offer-链表中环的入口结点-Java版
    //就是遍历链表走路一圈 遇到重复的说明有环
    //新版的newcoder编译这种算法也对
    public ListNode solution1(ListNode head){
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode cur = head;
        while(cur != null){
            map.put(cur, map.getOrDefault(cur,0) + 1);
            if(map.get(cur) == 2)
                return cur;
            cur = cur.next;
        }
        return null;
    }

    //solution 2 from newcode & github
    //利用双指针
    //看github 更好懂 但是也费脑
    //新版本的newcoder会将这种解法报错  当{1,2},{}
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null)
            return null;
        ListNode slow = pHead, fast = pHead;
        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        fast = pHead;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
