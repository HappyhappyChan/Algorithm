/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz1801
 * @Description: [在 O(1) 时间内删除链表节点 只在github有]
 * @Author: [clh]
 * @Date: 2021/10/11 17:29
 **/
public class Jz1801 {
    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if( head == null || tobeDelete == null)
            return null;
        if( tobeDelete.next != null){
            //如果该节点不是尾节点
            // 那么可以直接将下一个节点的值赋给该节点，然后令该节点指向下下个节点
            // 再删除下一个节点，时间复杂度为 O(1)。
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        }else if(head == tobeDelete){
                //只有一个节点 这里不设头节点
                head = null;
            }else{
            //尾结点的话要遍历链表，找到节点前一个节点
            //让前一个节点指向null 时间复杂度O(N)
            ListNode cur = head;
            while(cur.next != tobeDelete){
                cur = cur.next;
            }
            cur.next = null;
        }
        return head;

    }
}
