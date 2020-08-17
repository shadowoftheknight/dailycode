package stoneydata.com;


/**
 * Face of the cube
 */
public final class Face {
    /**
     * 
     */
    Cube.S side;
    
    /**
     * 
     */
    Cube.C colour;

    /**
     * 
     */
    Cube cube;

    /**
     * 
     * @param side
     * @param colour
     */
    public Face(Cube.S side, Cube.C colour,Cube cube) {
        this.side = side;
        this.colour = colour;
        this.cube = cube;
    }    


    public Cube.S getSide() {
        return this.side;
    }

    public Cube.C getColour() {
        return this.colour;
    }

    public Cube getCube() {
        return this.cube;
    }

  
    /**
     * 
     * @param s
     * @return
     */
    boolean is(Cube.S s) {
        return this.side.equals(s);
     }

}
