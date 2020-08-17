package lab;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given an array of integers, return a new array such that each element at 
 * index i of the new array is the product of all the numbers in the original array 
 * except the one at i.
 * For example, if our input was [1, 2, 3, 4, 5], 
 * the expected output would be [120, 60, 40, 30, 24]. 
 * If our input was [3, 2, 1], the expected output would be [2, 3, 6]
 */
public class ProductArrayPuzzle {
    
    @ParameterizedTest(name = "\"{0}\" should be {1}")
    @CsvSource({ "1:2:3:4:5, 120:60:40:30:24","3:2:1,2:3:6"})
    @DisplayName("ProductArrayPuzzle")
    void sol(String input, String expected){

        List<Integer> inputs =  Solution.conv(input.split(":"));
        List<Integer> expects = Solution.conv(expected.split(":")) ;

        List<Integer> output = new ArrayList<Integer> ();
        
        for(int i=0; i<inputs.size();i++){
            Integer product = Solution.multiplyExcept(inputs, i);
            output.add(product);
        }

        assertTrue(expects.containsAll(output), Arrays.toString(output.toArray()));

    }


    static class Solution {

        public static List<Integer> conv(String[] data) {
            List<Integer> lst = new ArrayList<Integer>();
            for (String d : data) {
                lst.add(Integer.parseInt(d));
            }

            return lst;
        }

        public static Integer multiplyExcept(List<Integer> lst,int index){
            Integer product = 1;
            
            for(int j=0; j<lst.size();j++){
                if(j != index ) {
                    product = Math.multiplyExact(product.intValue(), lst.get(j).intValue());                    
                }
            }
            return product;
        }
    }
}