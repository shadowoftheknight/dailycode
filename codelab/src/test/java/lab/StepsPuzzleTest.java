package lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * There exists a staircase with N steps, 
 * and you can climb up either 1 or 2 steps at a time. 
 * Given N, write a function that returns the number of unique ways you can climb the staircase. 
 * The order of the steps matters.
 */
public class StepsPuzzleTest {
    List<String> outcomes = new ArrayList<String>();
    StringBuilder ways = null;
   
   @Test
   void shouldStepBy(){

        //N=1 -> {1} 1
        //N=2 -> {1,1} {2} 3
        //N=3 -> {1,1,1} {1,2} {2,1} 7
        //N=4 -> {1,1,1,1} {2,1,1} {1,2,1} {1,1,2} {2,2}
                //{1,2}{1,2}{1,2}{1,2}
                // 1    3    5    7
                //   2    4    6    8   
        int N = 4;
        int[] walk = {1,2};
        int[] cartesian = new int[walk.length*N]; //{1,2}{1,2}{1,2}{1,2}
        
        int i=0;
        while(i<cartesian.length){ //create set of options
           for(int j=0; j<walk.length; j++){
               cartesian[i] = walk[j];
               i++;
           }
        }

       

        System.out.println(Arrays.toString(cartesian));

        int n = 4; 
        System.out.println(findStep(n)); 

   }

   // Returns count of ways to reach 
    // n-th stair using 1 or 2 or 3 steps. 
    public static int findStep(int n) 
    { 
        if (n == 1 || n == 0) 
            return 1; 
        else if (n == 2) 
            return 2; 
  
        else
            return findStep(n - 3) + findStep(n - 2) + findStep(n - 1); 
    } 
  
    // Driver function 
    public static void main(String argc[]) 
    { 
        int n = 4; 
        System.out.println(findStep(n)); 
    } 
    
}