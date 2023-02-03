import java.util.*;
public class hashset {
    public static void main(String[] args) {
        Set<Integer> s = new HashSet<Integer>();
        s.add(1);
        s.add(4);
        s.add(1);  //not added
        s.add(2);

        System.out.println(s);
        if(s.contains(4)) {
            System.out.println("item present");
        }
        else {
            System.out.println("item not found");
        }
        s.remove(1);
        System.out.println(s);
    }   
}
