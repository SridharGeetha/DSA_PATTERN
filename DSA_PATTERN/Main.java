package DSA_PATTERN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int[] arr1 = {5,10,15,40};
        int[] arr2 = {2,3,20};
        Node head1 = LinkedList.convertArrayToLinkedList(arr1);
        Node head2= LinkedList.convertArrayToLinkedList(arr2);
        Node ans = LinkedList.sortedMerge(head1, head2);
        LinkedList.printLL(ans);
        // String s1 = "ab";
        // String s2 = "eidaooo";
        // System.out.println(SlidingWindow.checkPermutaion(s1, s2));
        // int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        // int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
        // List<int[]> ans = TwoPointer.intervalIntersection(firstList,secondList);
        // System.out.println(ans);
        // for(int[] num : ans){
        //     System.out.print("["+num[0]+" "+num[1]+"] ");
        // }
        // String s = "aabcdaabc";
        // Solution sol = new Solution();
        // int res = sol.getLPSLength(s);
        // System.out.println(res);
        // // int x = -1;
        // System.out.println(1-x);
    }

    
}
 class Pair {
int a;
int b;
public Pair(int a, int b) {
    this.a = a;
    this.b = b;
}

    
}
class Solution {
    int getLPSLength(String s) {
        int n = s.length();
        int window = n;
        // code here
        if(n==1)return 0;
        if(n==2)return s.charAt(0)==s.charAt(1)?1:0;
        if(n<3)return s.substring(0,2).equals(s.substring(1,3))?2:s.substring(0,1).equals(s.substring(2,3))?1:0;
        while (window >= 0) {
            if (window < n) {
                System.out.printf("{(%d ,%d) + (%d ,%d) [%d]} ", 0, window, n - window, n, window);
                System.err.println(s.substring(0, window)+" "+(s.substring(n - window, n)));
                if (s.substring(0, window).equals(s.substring(n - window, n))) {
                    // System.out.println("found");
                    return window;
                }
            }
            window--;
        }
        // System.out.println();
        return -1;
    }
}

//[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]


/* Node class of the linked list
class Node {
    int data;
    Node next;
    Node(int key) {
        data = key;
        next = null;
    }
}
*/
