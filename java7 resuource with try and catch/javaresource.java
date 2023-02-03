import java.io.*;
public class javaresource {
   public static void main(String[] args) {
        try(FileOutputStream fout = new FileOutputStream("new.txt");
            BufferedReader brd = new BufferedReader(new FileReader("old.txt"))) {
                String str;
                while((str = brd.readLine())!=null) {
                    byte[] arr  = str.getBytes();
                    fout.write(arr);
                }
                System.out.println("data copied sucesfully");
        }catch(Exception e) {
              System.out.println(e);
        }
   }
}
