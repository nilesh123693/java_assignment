import java.util.*;
class NotEligible extends Exception {
    public NotEligible() {
        super();
    }
}

class VoteNow {
    public boolean isPersonElligible(int age) throws NotEligible {
        if(age<18) {
            throw new NotEligible();
        }
        else {
            return true;
        }
    }
    public static void main(String[] args) {
          VoteNow obj = new VoteNow();
          Scanner sc = new Scanner(System.in);
          System.out.println("enter you age");
          int age = sc.nextInt();
          try {
            obj.isPersonElligible(age);
            System.out.println("congratulations : your are eligible for vote :)");
          }catch(Exception e) {
               System.out.println(e + " : Age must be greater than 18");
               sc.close();
          }
    }
}
