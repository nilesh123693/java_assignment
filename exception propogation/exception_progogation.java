//exception propogation example
import java.util.*;
public class exception_progogation {
    public double divide(int no1 , int no2) throws ArithmeticException {
         return (float)no1/no2;
    }
    public double result(int no1 , int no2) throws ArithmeticException {
        double res = divide(no1, no2);
        return res;
        
    }
    public static void main(String[] args){
        exception_progogation obj = new exception_progogation();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter two number to divide each other");
        int no1  = sc.nextInt();
        int no2 = sc.nextInt();
        try {
            double res = obj.divide(no1, no2);
            System.out.println("A res of " + no1 + "/" + no2 + " is  : " + res);
        }catch(Exception e) {
            System.out.println("the number cant not be divide by zero its result is infinity");
        }
    }

}
