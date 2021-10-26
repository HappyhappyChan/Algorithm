import java.util.HashMap;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz25
 * @Description: [复杂链表的复制]
 * @Author: [clh]
 * @Date: 2021/10/12 10:20
 **/

/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
public class Jz35 {

    //from github
    //newcoder还有其他方法 但没看
    public RandomListNode Clone(RandomListNode phead){
        if(phead == null)
            return null;
        //插入新结点
        RandomListNode cur = phead;
        while(cur != null){
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }

        //建立random链接
        cur = phead;
        while(cur != null){
            RandomListNode clone = cur.next;
            if(cur.random != null){
                clone.random = cur.random.next;
            }
            cur = clone.next;
        }

        //拆分
        cur = phead;
        RandomListNode pclonehead = phead.next;
        while(cur.next != null){
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return pclonehead;
    }

    //solution 2 hashmap from newcoder
    public RandomListNode Clone2(RandomListNode phead){
        if(phead == null)
            return phead;
        RandomListNode p1 = phead;
        RandomListNode p2 = phead;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        //用一个 hashmap 建立新旧链表节点的对应的结点关系
        while(p1 != null){
            map.put(p1, new RandomListNode(p1.label));
            p1 = p1.next;
        }

        while(p2 != null){
            if(p2.next != null){
                map.get(p2).next = map.get(p2.next);
            }else{
                map.get(p2).next = null;
            }
            //迭代旧链表，从而在 hashmap 中更新新链表的 next 与 random 两个字段
            map.get(p2).random = map.get(p2.random);
            p2 = p2.next;
        }
        return map.get(phead);
    }
}
