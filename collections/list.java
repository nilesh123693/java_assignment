//insert five item and search the given element if it is found remove it and display the list;
import java.util.*;
class list {
    public static void main(String [] args) {
         Scanner sc = new Scanner(System.in);
         List<Integer> l = new ArrayList<Integer>();
         for(int i=11,j=1 ; i<=15 ; i++, j++) {
            l.add(i+j);
         }
         System.out.println("enter no to search and delete it");
         int no = sc.nextInt();
         if(l.contains(no)) {
            l.remove(no);
         }
         else {
            System.out.println("no not present in list");
         }
         System.out.println(l);
    }
}
