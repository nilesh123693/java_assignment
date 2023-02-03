/* write a program to calculate the sum of all odd and even numbers in given range is sum of even number is 
greater than 2000 then stop the loop or if sum of odd number is divisible by 11 then add 1 to odd number. if loop is
 fully executed then print  even and odd  only*/ 

import java.util.*;

class ControlFlow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter number");
        int no = sc.nextInt();
        int even = 0;
        int odd = 0;
        boolean flag = true;
        for(int i=1 ; i<=no ; i++) {
            if(i%2==0) {
                even += i;
                if(even>2000) {
                    System.out.println("The sum of even number is greater than 2000");
                    flag = false;
                    break;
                    
                }
            }
            else {
                odd += i;
                if(odd%11==0) {
                    odd+=1;
                    break;
                }
            }
        }
        if(flag) {
            System.out.println("loops executed sucessfully");
            System.out.println("The sum of even number in range is : " + even);
            System.out.println("The sum of odd numbers in range is :" +odd);
        }
    }
}