
import java.util.Map;
import java.util.HashMap;
public class map {
    public static void main(String[] args) {
        Map<Integer, String> m = new HashMap<Integer, String>();
        m.put(1, "nilesh");
        m.put(2, "tarun");
        m.put(3, "vansh");
        m.put(12, "gourav");

        //display the name based on id id is given and display their name
        System.out.println(m.get(1));
        System.out.println(m.get(12));
        
         //updating the value of id 12
         m.put(12, "gaurav");
         System.out.println(m);

         //remove id 2 entry
         m.remove(2);
         System.out.println(m);

         //get all keys
         System.out.println(m.keySet());

         //get all values
         System.out.println(m.values());

    }
}
