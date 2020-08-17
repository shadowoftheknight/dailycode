package stoneydata.com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import stoneydata.com.Cube.C;

/**
 * Unit test for simple App.
 */
class AppTest {

    final String input = "RBGYBY,RGGYBB,YBRGYR,YGBRRR";

    @Test
    // @Parameters({
    // "RBGYBY"
    // })
    void testStackCubes() {

        final String[] cubeAsArray = input.split(",");
        final App app = new App();
        final List<Cube> cubes = app.processInput(cubeAsArray);
        assertEquals(cubes.size(), cubeAsArray.length);
    }

    /**
     * TOP (4) FRONT RIGHT BACK LEFT (0) 1 2 3 BOTTOM (5)
     */
    @Test
    void testRotateFirstCubeRight() {

        final String expected = "GYBRBY";
        final String[] cubeAsArray = input.split(",");
        final App app = new App();
        final List<Cube> cubes = app.processInput(cubeAsArray);
        cubes.get(0).rotate(+1);
        assertEquals(expected, app.flatten(cubes.get(0).getFaces()));
    }

    @Test
    void testRotateFirstCubeLeft() {
        final String expected = "YGRBBY";
        final String[] cubeAsArray = input.split(",");
        final App app = new App();
        final List<Cube> cubes = app.processInput(cubeAsArray);
        cubes.get(0).rotate(-1);
        assertEquals(expected, app.flatten(cubes.get(0).getFaces()));
    }

    @Test
    void testRotateFirstCubeRightLeft() {
        final String expected = "RBGYBY";
        final String[] cubeAsArray = input.split(",");
        final App app = new App();
        final List<Cube> cubes = app.processInput(cubeAsArray);
        cubes.get(0).rotate(+1).rotate(-1);
        assertEquals(expected, app.flatten(cubes.get(0).getFaces()));
    }

    @ParameterizedTest
    @ValueSource(strings = { "RRYY" }) // six numbers
    void testColumnGroupBy(String expected) {

        final String[] cubeAsArray = input.split(",");
        final App app = new App();
        final List<Cube> cubes = app.processInput(cubeAsArray);

        CubeResolver cr = new CubeResolver();
        cr.compute(cubes);

        String colDesc = app.flatten(cr.getColumns().get(Cube.S.FRONT));
        assertEquals(expected, colDesc);

    }

    // @ParameterizedTest
    // @ValueSource(strings = {"C0F1,C1R1,C2R1,C3R+1"}) // six numbers
    void run(String commands) {

        // cubes.get(0).flip(1);
        // cubes.get(1).rotate(1);
        // cubes.get(2).rotate(1);
        // cubes.get(3).rotate(1);

    }

    @Test
    void testFirstColumnMatch() {
        final String input = "RBGYBY,RGGYBB,YBRGYR,YGBRRR";

        final String[] cubeAsArray = input.split(",");
        final App app = new App();
        List<Cube> cubes = app.processInput(cubeAsArray);
        app.print(cubes);

        CubeResolver cr = new CubeResolver();        
        Map<Cube.S, List<C>> prob = cr.compute(cubes);// return FRONT, [R,G]
        
        List<String> wins = new ArrayList<String>();
        final Iterator layout = prob.entrySet().iterator();
        while (layout.hasNext()) {
            int count = 0;
            Entry side = (Entry) layout.next();
            Cube.S columnAsSide = (Cube.S) side.getKey();
            String options = null;
            while (count != -1) { // run until 
                List<Cube.C> odds = prob.get(columnAsSide);// [R,G]
                // System.out.println(columnAsSide + " missing -> " + 
                //     Arrays.toString(odds.toArray()));

                options = cr.findChoices(cubes, prob, odds, columnAsSide);
                // System.out.println("options are -> " + options);

                if (null == options) {
                    count = -1;
                    continue;
                }
                String move = app.run(options, cubes);
                wins.add(move);
                prob = cr.compute(cubes);
                count++;
                // System.out.println("Count " + count);
                if (count > 103 || prob.get(columnAsSide).isEmpty()) {
                    break;
                }
                
            }

        }
        app.print(cubes);
        System.out.println(Arrays.toString(wins.toArray()));
        assertEquals(true, true);

    }

}
