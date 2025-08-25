package DSA_PATTERN;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

// class Node {
//     int data;
//     Node next;
//     Node(int data) {
//         this.data = data;
//     }
// }
class ListNode {
    int data;
    ListNode next;
    ListNode(int data) {
        this.data = data;
    }
}

class Node {
    int data;
    Node next;
    Node random;

    Node(int x) {
        data = x;
        next = null;
        random = null;
    }
}
public class LinkedList {

    public static Node convertArrayToLinkedList(int[] arr){
        Node head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            curr.next = temp;
            curr = curr.next;
        }
        return head;
    }

    //print LL
    public static void printLL(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
    }

    //<<<<<<<<< Fast and Slow Pointer >>>>>>>>>`

    static Node reverseList(Node head) {
      
      Node temp = head;
      Node prev = null;
      while(temp != null){
          Node front = temp.next;
          temp.next = prev;
          prev = temp;
          temp = front;
      }
      
      return prev;
         
    }
    public ListNode reverseList(ListNode head){
        ListNode temp = head;
        ListNode prev = null;
        while(temp != null){
           ListNode nextNode = temp.next;
            temp.next = prev;
            prev = temp;
            temp = nextNode;
        }
        return prev;
    }


    public static Node reverseBetween(Node head, int left, int right) {
        Node temp  = head;
        Node prevlast = null;
        Node frontstart = null;
        Node revstart = null;
        int cnt = 1;
        while(temp != null){
            if(left-1 == cnt){
                prevlast = temp;
                revstart = temp.next;
                prevlast.next = null;
            }
            if(cnt == right){
                frontstart = temp.next;
                temp.next = null;
                break;
            }
            temp = temp.next;
        }
        Node newHead = reverseList(revstart);
        prevlast.next = newHead;
        revstart.next = frontstart;

        return head;
    }
    // start

    public ListNode findKth(ListNode head,int k){
       ListNode temp = head;
       int cnt =0;
        while(temp != null && ++cnt != k){
            temp = temp.next;
        }
        return temp;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode prevlast = null;
        while(temp != null){
            ListNode kthNode = findKth(temp,k);
            if(kthNode == null){
                if(prevlast != null) prevlast.next = temp;
                break;
            }
            ListNode nextNode = kthNode.next;
            kthNode.next = null;
            reverseList(temp);
            if(temp == head){
                head = kthNode;
            }else{
                prevlast.next = kthNode;   
            }
            prevlast = temp;
            temp = nextNode;
        }
        return head;
    }

    //end

    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newH = reverseList(slow.next);
        ListNode second = newH;
        ListNode first =  head; 

        while(second != null){
            if(first.data != second.data){
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;
    }

    //<<<<<<<<< Fast and Slow Pointer >>>>>>>>>

            // Find Middle of Linked List

            // Detect Cycle in Linked List (Floyd’s Cycle Detection)

            // Find Starting Node of Cycle

            // Length of Cycle

            // Remove N-th Node from End

            // Intersection of Two Linked Lists

    //Length of Cycle

    static int findLength(Node slow, Node fast){ 
        int cnt = 1; 
        fast = fast.next; 
        while(slow!=fast){      
            cnt++;
            fast = fast.next;
        }
       
        return cnt;
    }
    static int lengthOfLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {  
            slow = slow.next;     
            fast = fast.next.next; 
            if (slow == fast) {
                return findLength(slow, fast);
            }
        }
        return 0; 
    }    

    //Remove Loop

    public static void removeLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                slow = head;
                if (slow == fast) {//edge case what if loop start from head.
                    while (fast.next != slow) {
                        fast = fast.next;
                    }
                } else {
                    while (slow.next != fast.next) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                }
                fast.next = null;
                return;
            }
        }
    }

//<<<<<<<<<<<<<<<<<<< 4. Classic Transformations>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // Merge Two Sorted Linked Lists

        public static Node sortedMerge(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;
        Node ans = new Node(-1);
        Node curr = ans;
        while(temp1 != null && temp2 != null){
            if(temp1.data < temp2.data){
                Node front = temp1.next;
                curr.next = temp1;
                curr = curr.next;
                curr.next = null;
                temp1 = front;
            }else{
                Node front = temp2.next;
                curr.next = temp2;
                curr = curr.next;
                curr.next = null;
                temp2 = front;
            }
        }
        if(temp1 != null){
            curr.next = temp1;
        }else{
            curr.next = temp2;
        }
        return ans.next;
    }

        // Sort a Linked List (Merge Sort)

        static Node merge(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;
        Node ans = new Node(-1);
        Node curr = ans;
        while(temp1 != null && temp2 != null){
            if(temp1.data < temp2.data){
                Node front = temp1.next;
                curr.next = temp1;
                curr = curr.next;
                curr.next = null;
                temp1 = front;
            }else{
                Node front = temp2.next;
                curr.next = temp2;
                curr = curr.next;
                curr.next = null;
                temp2 = front;
            }
        }
        if(temp1 != null){
            curr.next = temp1;
        }else{
            curr.next = temp2;
        }
        return ans.next;
    }
    static Node findMid(Node head){
        if(head == null || head.next == null) return head;
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return  slow;
    }
    static Node mergeSort(Node head) {
        if(head == null || head.next == null) return head;
        Node left = head;
        Node mid =  findMid(head);
        Node right =     mid.next;
        mid.next = null;
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left,right);
    }

        // Add Two Numbers (as linked lists)

        // Flatten a Multilevel Linked List

        // Rotate Linked List (by k places)
    public Node rotate(Node head, int k) {
        int len = 1;
        Node tail = head;
        while(tail.next != null){
            len++;
            tail = tail.next;
        }
        k = k%len;
        if(k == 0) return head;
        Node kth = head;
        for (int i = 1; i < k; i++) {
            kth = kth.next;
        }
        Node newHead = kth.next;
        kth.next = null;
        tail.next = head;
        return newHead;
    }



    public Node cloneLinkedList(Node head) {
        Node temp = head;
        // code insert copy at middle
        while(temp != null){
            Node front = temp.next;
            Node copy = new Node(temp.data);
            temp.next = copy;
            copy.next = front;
            temp = front;
        }
        temp = head;
        while(temp != null){
            Node copyNode = temp.next;
            if(temp.random != null){
                copyNode.random = temp.random.next;
            }else{
                copyNode.random = null;
            }
            temp = temp.next.next;
        }
        
        Node res = new Node(-1);
        Node curr = res;
        temp = head;
        while(temp != null){
            curr.next = temp.next;
            temp.next = temp.next.next;
            curr = curr.next;
            curr.next = null;
            temp = temp.next;
        }
        return res.next;
    }

//<<<<<<<<<<<<<<<<<<<<<<<<<6. Dummy Node / Pointer Manipulation Trick>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // Partition List (around a value x)
        public ListNode partition(ListNode head, int x) {
        ListNode curr = head;
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode temp1 = dummy1;
        ListNode temp2 = dummy2;
        while(curr != null){
            ListNode front = curr.next;
            if(curr.data < x){
                temp1.next = curr; 
                temp1 = temp1.next;
                temp1.next = null;
                
            }else{
                temp2.next = curr;
                temp2 = temp2.next;
                temp2.next = null;
                
            }
            curr = front;
        }
        temp1.next = dummy2.next;
        return dummy1.next;
    }

        // Odd-Even Linked List

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = head.next;
        while(even != null && even.next != null){
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

        // Separate Even and Odd Nodes

        // Swap Nodes in Pairs

    public ListNode swapPairs(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        while(temp != null){
           if(temp == null || temp.next == null){
                if(prev != null){
                    prev.next = temp;
                }
                break;
           }
           ListNode first = temp;
           ListNode second = temp.next;
           ListNode nextNode = second.next;
           second.next = null;
           ListNode newHead = reverseList(first);
           if(first == head){
                head = newHead;
           }else{
            prev.next = newHead;
           }
           prev = first;
           temp = nextNode;
        }
        return head;
    }

        // Swap Kth Node from Beginning and End

        public ListNode swapNodes(ListNode head, int k) {
        int n = 0;
        ListNode curr = head;
        while(curr!=null){
            n++;
            curr = curr.next;
        }
        ListNode first = head;
        for(int i=0;i<k-1;i++){
            first = first.next;
        }
        
        ListNode second = head;
        for(int i=0;i<n-k;i++){
            second = second.next;
        }
        int temp = first.data;
        first.data = second.data;
        second.data = temp;
        return head;
    }
    
}
