package lab;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
/**
 * This problem was recently asked by Google.
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 * for example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 * @author HoHill
 */
class NumberAddUpTest {
    

    @DisplayName("Two Numbers add up to")
    @ParameterizedTest(name = "\"{0}\" should be {1}")
    @CsvSource({ "17, 10:15:3:7", "13, 10:15:3:7","10, 10:15:3:7"})
    void shouldAddUpTo(int k, String range){
        
        List<String> lst = Arrays.asList(range.split(":"));
        
        List<String> newLst = 
                lst.stream().filter((t) -> checksum(lst, t, k))
                        .collect(Collectors.toList());
        
        System.out.println(" lst -> " + newLst);
        int num1 = Integer.parseInt(newLst.get(0));
        int num2 = Integer.parseInt(newLst.get(1));
        
        assertTrue(num1+num2==k);
    }
    
    public static boolean checksum(List<String> lst, String param, int k){
        for(String n:lst){
           if(Integer.parseInt(n) + Integer.parseInt(param) == k){
               return true;
           }
        }
         
       return false;
   }
        
    
}