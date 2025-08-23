package DSA_PATTERN;

import java.util.HashMap;
import java.util.Map;

class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
    }
}
class ListNode {
    int data;
    ListNode next;
    ListNode(int data) {
        this.data = data;
    }
}
public class LinkedList {

    //<<<<<<<<< Fast and Slow Pointer >>>>>>>>>

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
    
}
