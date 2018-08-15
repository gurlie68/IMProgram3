/*Project 3
 *Nancy C Fain
 * July 1, 2018
 *Calculate the terms of the following sequence of
 *numbers: 0 1 2 5 12 29 ... where each term of the sequence is twice the previous term plus the second
 *previous term. The 0th term of the sequence is 0 and the 1st term of the sequence is 1.
 * Write Efficiency to File for calculation 1 - 10
 */

import java.awt.*;

public class Sequence {

    private static int efficiency;

    /*Constructor for instance variable */
    public Sequence(){
        efficiency = 0;
    }

    /*Iterative Method*/
    public static int iterativeSequence(int n){

        efficiency = 0;
        int[] value = new int[n+1];

        for(int i = 0; i <= n; i++){
            if(i==0){
                value[i]=0;
            }
            else if(i==1){
                value[i]=1;

            }else{
                value[i] = ((value[i - 1] * 2) + value[i - 2]);
            }
            efficiency++;
        }
        return value[n];

    }

    /*Helper method for recursive*/

    public static int computeRecursive(int n){
        efficiency = 0;
        return recursive(n);
    }

    /*Recursive Method*/

    private static int recursive(int n) {
        efficiency++;
        if (n <= 1) {
            return n;
        }
        return recursive ( n - 1 ) * 2 + recursive ( n - 2 );
            }

     /* Efficiency getter method to return efficiency counter*/
    public static int getEfficiency(){
        return efficiency;
    }

} // end Sequence