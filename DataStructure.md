数据结构

==结合pdf文件一起看==

## 链表反转

head-1-2-3-4

1. 调用reverse(Node cur)反转每一个结点，从结点1开始
2. 如果发现cur有下一个结点，则递归调用reverse(cur.next)对下一个结点翻转
3. 最终递归的出口是元素4结点，因为没有下一个元素了，到了出口处，让head指向元素4；共递归调用4次
4. 递归开始返回

第四次递归：head-4-null

第三次递归：head-4-3-null

第二次递归：head-4-3-2-null

第一次递归：head-4-3-2-1-null

## 快慢指针

定义两个指针，两个指针的移动速度一快一慢，以此来制造出自己想要的差值，这个差值可以让我们找到链表上相应的节点。一般情况下，快指针的移动步长为慢指针的两倍。

### 1 中间值问题

找出链表的中间元素值并返回。

当fast走完链表后，slow刚好是链表的中间节点

```java
public String getMid(Node<String> first){
    //定义两个指针
    Node<String> fast = first;
    Node<String> slow = first;
    //使用两个指针遍历链表，当快指针指向的节点没有下一个结点即可结束
    while(fast != null && fast.next != null){
        //变化fast和slow的值
        fast = fast.next.next;
        slow = slow.next;
    }
    return slow.data;
    //慢指针指向的节点就是中间值
}
```

### 2 单向链表是否有环

利用快慢指针，两个人有速度差，在一个圆环跑道，迟早两个人会相遇，只要相遇就说明有环。

```java
public boolean isCircle(Node<String> first){
    //定义快慢指针
    Node<String> fast = first;
    Node<String> slow = first;
    
    //如果快慢指针指向同一个节点 说明有环
    while(fast != null && fast.next != null){
        fast = fast.next.next;
        slow = slow.next;
        if(fast == slow){return true;}
    }
    return false;
}
```

### 3 有环链表入口问题

当快慢指针相遇时，我们可以判断到链表中有环，这时重新设定一个新指针指向链表的起点，且步长与慢指针一样为1，则慢指针与新指针相遇的地方就是环的入口。

```java
public Node getEntrance(Node<String> first){
    //定义快慢指针
    Node<String> fast = first;
    Node<String> slow = first;
    Node<String> tmp = null;
    //遍历链表 先找到环，快慢指针相遇
    while(fast != null && fast.next != null){
        fast = fast.next.next;
        slow = slow.next;
        //判断是否相遇
        if(fast.equals(slow)){
            //让临时指针指向首节点
            tmp = first;
            continue;
        }
        if(tmp != null){
            tmp = tmp.next;
            //判断tmp和slow是否相遇
            if(tmp.equals(slow)){
                break;
            }
        }
    }
    return tmp;
}
```

## 约瑟夫环

41个人从1开始报数，报到3的那个人就退出环，由退出的人的下一个人从1开始继续报数，求最后剩下的那个人。

```java
public static void main(String[] args){
    // 1 构造约瑟夫环 包含41个结点 分别存储1-41之间的值
    //头结点
    Node<Integer> first = null;
    //记录前一个结点
    Node<Integer> pre = null;
    for(int i = 1; i <= 41; i++){
        //如果第一个结点
        if(i == 1){
            first = new Node<>(i, null);
            pre = first;
            continue;
        }
        //如果不是第一个结点
        Node<Integer> cur = new Node<>(i, null);
        pre.next = cur;
        pre = cur;
        //如果是最后一个结点 要让最后一个结点指向第一个结点
        if(i == 41){
            pre.next = first;
        }
    }
    // 2 需要cnt计数器用于报数
    int cnt = 0;
    // 3 遍历循环链表
    Node<Integer> n = first;
    //记录当前结点的上一个结点
    Node<Integer> before = null;
    while(n != n.next){
        //模拟报数
        cnt++;
        //判断当前报数==3？
        if(cnt == 3){
            //如果是3 删除当前结点并打印，重置cnt，让当前结点后移
            before.next = n.next;
            System.out.print(n.item+" ");
            cnt = 0;
            n = n.next;
        }else{
            //如果不是3 让before=当前结点并让当前结点后移
            before = n;
            n = n.next;
        }
    }
    //打印最后一个元素
    System.out.print(n.item);
}
```

## 栈相关案例

### 括号匹配

```java
public boolean isMatch(String str){
    //1创建栈对象 存储左括号
    Stack<String> stack = new Stack<>();
    //2 从左往右遍历字符串
    for(int i = 0; i < str.length(); i++){
    	String c = str.charAt(i) + "";
        if(c == "("){
            stack.push(c);
        }
        if(c == ")"){
            String top = stack.pop();
            //3 如果是右括号，从栈弹出，判断弹出是否为null
            if(top == null)
                return false;
        }
    }
    //4 判断栈是否为空 有则说明不匹配
    return stack.isEmpty();
}
```

### 逆波兰表达式

```java
public static int calculate(String[] notation){
    //1 定义一个栈 用来存储操作数
    Stack<Integer> stack = new Stack<>();
    //2 从左往右遍历逆波兰表达式 得到每一个元素
    for(int i = 0; i < notation.length; i++){
        String cur = notation[i];
        //3 判断当前元素是运算符还是操作数
        switch(cur){
            Integer o1;
            Integer o2;
            Integer res;  
            case "+":
                //4 运算符 从栈中弹出2个操作数 完成运算 运算完的结果再放入栈中
                o1 = stack.pop();
                o2 = stack.pop();
                res = o1 + o2;
                stack.push(res);
                break;
            case "-":
                o1 = stack.pop();
                o2 = stack.pop();
                //注意顺序
                res = o2 - o1;
                stack.push(res);
                break;
            case "*":
                o1 = stack.pop();
                o2 = stack.pop();
                res = o1 * o2;
                stack.push(res);
                break;
            case "/":
                o1 = stack.pop();
                o2 = stack.pop();
                //注意顺序
                res = o2 / o1;
                stack.push(res);
                break;
            default:
                //5 操作数 把操作数放入栈中
                stack.push(Integer.parseInt(cur));
                break;
        }
    }
    int res = stack.pop();
    
}
```

## 二叉树遍历

### 前序遍历

```java
//获取整个树中的所有键
public Queue<Key> preErgodic(){
    Queue<Key> keys = new Queue<>();
    preErgodic(root, keys)
    return keys;
}

//获取指定树k的所有键，并放到keys队列中
private void preErgodic(Node x, Queue<Key> keys){
    if(x == null)
        return;
    //把x的key放入keys中
    keys.add(x);
    //递归遍历x结点的左子树
    if(x.left != null){
        preErgodic(x.left, keys);
    }
    //递归遍历x结点的右子树
    if(x.right != null){
        preErgodic(x.right, keys);
    }
}
```

### 中序遍历

```java
//使用中序遍历获取树中的所有键
public Queue<Key> midErgodic(){
    Queue<Key> keys = new Queue<>();
    midErgodic(root, keys);
    return keys;
}

//使用中序遍历，获取指定树x中所有的键并放入keys中
private vod midErgodic(Node x, Queue<Key> keys){
    if(x == null)
        return;
    //先递归 把左子树中的键放入keys
    if(x.left != null)
        midErgodic(x.left, keys);
    //把当前x的键放入keys
    keys.add(x);
    //再递归，把右子树中的键放到keys中
    if(x.right != null)
        midErgodic(x.right, keys);
} 
```

### 后序遍历

```java
public Queue<Key> afterErgodic(){
    Queue<Key> keys = new Queue<Key>();
    afterErgodic(root, keys);
    return keys;
}

private void afterErgodic(Node x, Queue<Key> keys){
    if(x == null)
        return;
    if(x.left != null)
        afterErgodic(x.left, keys);
    if(x.right != null)
        afterErgodic(x.right, keys);
    keys.add(x, keys);
}
```

### 层次遍历

```java
public Queue<Key> layerErgodic(){
    //定义2个队列 存储树中的键和结点
    Queue<Key> keys = new Queue<>();
    Queue<Node> nodes = new Queue<>();
    
    nodes.add(root);
    while(!nodes.isEmpty()){
        //弹出结点，把key放入keys
        Node n = nodes.poll();
        keys.add(n.key);
        if(n.left != null){
			nodes.add(n.left);
        }
        if(n.right != null){
            nodes.add(n.right);
        }
    }
    return keys;
}
```

### 最大深度问题

```java
//获取整个树的最大深度
public int maxDepth(){
    return maxDepth(root);
}

//获取指定x的最大深度
private int maxDepth(Node x){
    if(x == null)
        return 0;
    int max = 0;
    int maxL = 0;
    int maxR = 0;
    //计算x结点左子树的最大深度
    if(x.left != null){
        maxL = maxDepth(x.left);
    }
    //计算x结点右子树最大深度
    if(x.right != null){
        maxR = maxDepth(x.right);
    }
    //比较左右最大深度 + 1即可
	return maxL > maxR? maxL + 1: maxR + 1;
}
```

### 折纸问题

请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时 折痕是凹下去的，即折
痕突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2 次，压出折痕后展开，此时有三条折痕，从上
到下依次是下折痕、下折痕和上折痕

给定一 个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向 例如：N=1时，打
印： down；N=2时，打印： down down up

```java
//通过模拟对折n次纸产生树
public static Node create(int n){
   //定义根结点
    Node<String> root = null;
    for(int i = 0; i < n; i++){
        // 1 当前是第一次对折 为空树
        if(i == 0){
            root = new Node<>("down", null, null);
            continue;
        }
            
        //2 不是第一次对折
        //定义一个辅助队列 通过层次遍历，找到叶子结点，给叶子结点添加结点
        Queue<Node> que = new Queue<>();
        que.add(root);
        
        //循环遍历队列
        while(!que.isEmpty()){
            Node tmp = que.poll();
            if(tmp.left != null){
                que.add(tmp.left);
            }
            if(tmp.right != null){
                que.add(tmp.right);
            }
            if(tmp.left == null && tmp.right == null){
			   tmp.left = new Node("down", null, null);
                tmp.right = new Node("down", null, null);
            }
        }
    }
    return root;
}

public static void printTree(Node<String> root){
    //中序遍历完成
    if(root == null)
        return;
    //打印左子树
    if(root.left != null)
        printTree(root.left);
    //打印当前结点
    System.out.println(root.item + " ");
    //打印右子树
    if(root.right != null)
        printTree(root.right);
}
```

## 堆

Heap<T extends Comparable\<T>>

### 堆排序

