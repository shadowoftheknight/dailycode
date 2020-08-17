package lab;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberFlipTest {

    @ParameterizedTest
    @ValueSource(ints = { 123 }) // six numbers
    void shouldFlip(int n){
        int result = new Solution().solve(n);

        assertTrue(result == 323,"" +result);
    }

    static class Solution {
        public int solve(int n) {
            String amt = null;
            amt = String.valueOf(n).replaceFirst("[0-2]","3");
            return Integer.parseInt(amt);
        }
    }
    
}

