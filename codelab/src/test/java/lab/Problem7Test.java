package lab;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import com.sun.tools.javac.util.List;

import org.junit.jupiter.api.Test;

/**
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the
 * number of ways it can be decoded. For example, the message '111' would give
 * 3, since it could be decoded as 'aaa', 'ka', and 'ak'. You can assume that
 * the messages are decodable. For example, '001' is not allowed.
 */
public class Problem7Test {
    String[] alpha = {"", "a","b","c","d","e","f","g",
                        "h","i","j","k","l","m","n",
                        "o","p","q","r","s","t","u","v",
                        "w","x","y","z" };

   @Test
   void solv(){
       String msg = "111";
       char[] msgs = msg.toCharArray();
       ArrayList messages = new ArrayList();
       for(int i=0;i<msgs.length;i++){
            messages.add(decode(msgs[i]+""));
       }
       System.out.println("msg " + Arrays.toString(messages.toArray()));
   }

   private String decode(String code) {
       int seq = Integer.parseInt(code);
       System.out.println("seq " + seq);
       return alpha[seq];
   }
}