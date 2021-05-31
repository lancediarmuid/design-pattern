import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 */
public class CycleLinkList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode current = head;
        Set<ListNode> listNodePool = new HashSet<>();
        while (true) {
            if (listNodePool.contains(current)) {
                return true;
            }
            if (current == null) {
                return false;
            }
            listNodePool.add(current);
            current = current.next;
        }
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 使用快慢指针的方法
        ListNode fast = head;
        ListNode slow = head;

        while (true) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null || fast.next == null) {
                return false;
            }
            if (fast == slow || slow == fast.next) {
                return true;
            }
        }
    }

    public ListNode genCase() {
        int n = 10;
        ListNode headNode = new ListNode(1);
        ListNode node2 = new ListNode(2);
        headNode.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;

        // 环形连表，node5 -> node2
        node5.next = node2;

        return headNode;
    }

    public static void main(String[] args) {
        CycleLinkList cycleLinkList = new CycleLinkList();
        ListNode head = cycleLinkList.genCase();
        // 打印循环链表
//        for (int i = 0; i < 100; i++) {
//            System.out.println(head.val);
//            head = head.next;
//        }

        System.out.println(cycleLinkList.hasCycle2(head));
    }


}
