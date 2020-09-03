package lab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
/**
 * Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
 */
public class JobSchedulerTest {
    String x[] = new String[5];
    @Test
    void shouldScheduleAfter() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        int y =3;
        y +=4;

        Arrays.fill(x, "hello");
        try {
            new Solution().
                schedule(() -> System.out.println("Job " + dtf.format(LocalDateTime.now())), 4000);
            new Solution().
                schedule(() -> System.out.println("Job " + dtf.format(LocalDateTime.now())), 10000);
            new Solution().
                schedule(() -> System.out.println("Job " + dtf.format(LocalDateTime.now())), 4000);


        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
	class Solution {
        
        void schedule(Job k, int n) throws InterruptedException {
            Thread t = new Thread(new Runnable(){
                @Override
                public void run() {
                    k.run();  
                }
            });
            Thread.sleep(n);
            t.run();
            
        }
    }
}