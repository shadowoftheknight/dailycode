package stoneydata.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cube!
 */
public final class Cube {

    List<Face> faces = null;

    /**
     * blue (B), red (R), green (G) and yellow (Y).
     */
    enum C {
        B, R, G, Y
    }

    /**
     * FRONT, BACK, LEFT, RIGHT, TOP, BOTTOM.
     */
    enum S {
        FRONT, BACK, LEFT, RIGHT, TOP, BOTTOM
    }

    /*
     * front, back, left, right, top, bottom.
     */
    public Cube(C _front, C _back, C _left, C _right, C _top, C _bottom) {
        faces = Arrays.asList(Face(S.FRONT, _front), // 0
                Face(S.RIGHT, _right), // 1
                Face(S.BACK, _back), // 2
                Face(S.LEFT, _left), // 3
                Face(S.TOP, _top), // 4
                Face(S.BOTTOM, _bottom));// 5
    }

    /**
     * 
     * @param _front
     * @param _back
     * @param _left
     * @param _right
     * @param _top
     * @param _bottom
     */
    private Cube(char _front, char _back, char _left, char _right, char _top, char _bottom) {
        this(C.valueOf(String.valueOf(_front)), C.valueOf(String.valueOf(_back)), C.valueOf(String.valueOf(_left)),
                C.valueOf(String.valueOf(_right)), C.valueOf(String.valueOf(_top)), C.valueOf(String.valueOf(_bottom)));
    }

    public Cube(char[] f) {
        this(f[0], f[1], f[2], f[3], f[4], f[5]);
    }

    /**
     *  TOP 
     *  (4) 
     * FRONT RIGHT BACK LEFT 
     *  (0)    1    2   3 
     * BOTTOM 
     *  (5)
     * 
     * @param direction
     * @return
     */
    public Cube flip180(int direction){
        List<C> coloursList = new ArrayList<C>(faces.size());
        faces.forEach((t) -> {
            coloursList.add(t.colour);
        });

        if (direction == 1) { //UP
            faces.get(0).colour = coloursList.get(5); 
            faces.get(4).colour = coloursList.get(0); 
            faces.get(5).colour = coloursList.get(2); 
            faces.get(2).colour = coloursList.get(4); 
        }else{ //DOWN
            faces.get(0).colour = coloursList.get(4); 
            faces.get(5).colour = coloursList.get(0); 
            faces.get(2).colour = coloursList.get(5); 
            faces.get(4).colour = coloursList.get(2); 
        }
        return this;
    }

        /**
     * 
     * @param direction
     * @return
     */
    public Cube flip90(int direction){
        List<C> coloursList = new ArrayList<C>(faces.size());
        faces.forEach((t) -> {
            coloursList.add(t.colour);
        });

        if (direction == 1) { // right
            faces.get(1).colour = coloursList.get(4); //UP
            faces.get(4).colour = coloursList.get(3); 
            faces.get(3).colour = coloursList.get(5); 
        }else{
            faces.get(4).colour = coloursList.get(1); //UP
            faces.get(1).colour = coloursList.get(5); 
            faces.get(5).colour = coloursList.get(3); 
        }
        return this;
    }

    /**
     *  TOP 
     *  (4) 
     * FRONT RIGHT BACK LEFT 
     *  (0)    1    2   3 
     * BOTTOM 
     *  (5)
     * 
     * @param direction
     * @return
     */
    public Cube rotate(int direction) {
        int index = 0;
        int END = 3;
        List<C> coloursList = new ArrayList<C>(faces.size());
        faces.forEach((t) -> {
            coloursList.add(t.colour);
        });

        if (direction == 1) { // right
            faces.get(index).colour = coloursList.get(END); // rotate the front first switch with end i.e LEFT
            while (index < END) {
                // assign left colour to front
                faces.get(index + 1).colour = coloursList.get(index);
                index++;
            }
        } else { // rotate left -1
            index = 0;
            END = 3;
            faces.get(END).colour = coloursList.get(index); // rotate the front first
            while (index < END) {
                // assign right colour to front
                faces.get(index).colour = coloursList.get(index + 1);
                index++;
            }
        }

        return this;
    }

    private Face Face(S s, C _front) {
        return new Face(s, _front,this);
    }

    public List<Face> getFaces() {
        return faces;
    }

    /**
     * 
     * @return
     */
    public Face getFace(S s) {
        for (Face f : faces) {
            if (f.side.equals(s)) {
                return f;
            }
        }
        throw new IllegalAccessError();
    }

	public Cube move(String ops, int direction) {
        // System.out.println("Ops "+ ops + " direction " + direction);
        if(ops.equals("flip90")){
            this.flip90(direction);
        }else
        if(ops.equals("flip180")){
            this.flip180(direction);
        }else
        if(ops.equals("rotate")){
            this.rotate(direction);
        }else{
            throw new IllegalAccessError("No move found for " + ops); 
        }

        return this;
	}

}
