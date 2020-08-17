package stoneydata.com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Hello Cube!
 */
public final class App {
    static String[] HEADER = {"F","B","L","R","T","Bottom"};
    

    public App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        App app = new App();
        List<Cube> cubes = app.processInput(args);
        app.print(cubes);
    }

    /**
     *  
     * @param args
     * @return
     */
    public List<Cube> processInput(String[] cubeAsArray) {
        
        List<Cube> cubes = new ArrayList<Cube>(cubeAsArray.length);
        for(String cubeDesc:cubeAsArray){
             cubes.add(new Cube(cubeDesc.toCharArray()));
        }
        return cubes;
         
    }

    /**
     * 
     * @param cubes
     */
    public void print(List<Cube> cubes) {
        int row =1;
        System.out.println("\nCube \t|  Front  | Back  | Left  | Right |  Top | Bottom |");
        System.out.println("\t--------------------------------------------------|");
         for(Cube c:cubes){
             System.out.println(row+"\t|   "+flatten(c.getFaces(),"   |  "));
             row++;
         }
         System.out.println("\t--------------------------------------------------|");
    }

    
    public String flatten(List<Face> orgFaces){  
       return flatten(orgFaces, null);
    }

    /**
     * 
     * @param c
     * @return get the faces/sides as a flatten structure not 3D
     */
    public String flatten(List<Face> orgFaces, String delimiter){  
        
        List<Face> cpfaces = new ArrayList<Face>(orgFaces);
        
        cpfaces.sort(Comparator.comparing((t) -> {
            return CubeResolver.sidemap.indexOf(((Face)t).side);
        }));

        StringBuilder sb = new StringBuilder();
        if(null != delimiter){
            sb.append(" "); 
        }
        for (Face f : cpfaces) {
            sb.append(f.colour);
            if(null != delimiter){
                sb.append(" "+delimiter); 
            }
            
        }        
        
        return sb.toString();
    }

    /**
     *  TOP 
     *  (4) 
     * FRONT RIGHT BACK LEFT 
     *  (0)    1    2   3 
     * BOTTOM 
     *  (5)
     */
	public String run(String options, List<Cube> cubes) {     
        String side = options.substring(0, options.indexOf("{"));   
        options = options.replace("}", "");
        options = options.substring(options.indexOf("{")+1);       

        String[] moves = options.split(",");
        Random r = new Random();
        String move = moves[r.nextInt(moves.length)];
        String[] cmds = move.split(":");
         
        int row = Integer.parseInt(cmds[0]);
        String target = cmds[1];
        
        //Stored Commands
        return execute(side,target,row, cubes);


	}

    /**
     *  TOP 
     *  (4) 
     * FRONT RIGHT BACK LEFT 
     *  (0)    1    2   3 
     * BOTTOM 
     *  (5)
     */
    private String execute(String side, String target, int row, List<Cube> cubes) {
        Cube c = cubes.get(row-1);
        // System.out.println("Cube " + row + " target(" + target+")" + " for " + side);
        side = side.trim();
        target = target.trim();

        if(side.equals("TOP")){
            if(target.equals("LEFT")){
                c.move("flip90",1);  
            }else
            if(target.equals("RIGHT")){
                c.move("flip90",-1);
            }else
            if(target.equals("BACK")){
                c.move("flip180",-1); 
            }else
            if(target.equals("FRONT")){
                c.move("flip180",1); 
            }else{
                throw new IllegalAccessError("No move for " + side + " side found -> " + target);
            }   
        }

        if(side.equals("BOTTOM")){
            if(target.equals("LEFT")){
                c.move("flip90",-1);  
            }else
            if(target.equals("RIGHT")){
                c.move("flip180",1);
            }else
            if(target.equals("BACK")){
                c.move("flip180",1); 
            }else
            if(target.equals("TOP")){
                c.move("flip180",-1).move("flip180",-1); 
            }else
            if(target.equals("FRONT")){
                c.move("flip180",-1); 
            }else{
                throw new IllegalAccessError("No move for " + side + " side found -> " + target);
            }   
        }

        if(side.equals("RIGHT")){
            if(target.equals("LEFT")){
                c.move("rotate",1); 
            }else
            if(target.equals("BACK")){
                c.move("rotate",-1); 
            }else            
            if(target.equals("BOTTOM")){
                c.move("flip90",-1); 
            }else
            if(target.equals("TOP")){
                c.move("flip90",1); 
            }else
            if(target.equals("FRONT")){
                c.move("rotate",1); 
            }else{
                throw new IllegalAccessError("No move for " + side + " side found -> " + target);
            }
        }

        if(side.equals("LEFT")){
            if(target.equals("RIGHT")){
                c.move("rotate",-1); 
            }else
            if(target.equals("BACK")){
                c.move("rotate",1); 
            }else            
            if(target.equals("BOTTOM")){
                c.move("flip90",1); 
            }else
            if(target.equals("TOP")){
                c.move("flip90",-1); 
            }else
            if(target.equals("FRONT")){
                c.move("rotate",-1); 
            }else{
                throw new IllegalAccessError("No move for " + side + " side found -> " + target);
            }
        }
        //*No move for FRONT side found -> TOP
        if(side.equals("FRONT")){
            if(target.equals("LEFT")){
                c.move("rotate",1);  
            }else
            if(target.equals("RIGHT")){
                c.move("rotate",-1);
            }else
            if(target.equals("BACK")){
                c.move("rotate",-1).move("rotate",-1); 
            }else
            if(target.equals("TOP")){
                c.move("flip180",-1); 
            }else
            if(target.equals("BOTTOM")){
                c.move("flip180",1); 
            }else{
                throw new IllegalAccessError("*No move for " + side + " side found -> " + target);
            }   
        }

        if(side.equals("BACK")){
            if(target.equals("LEFT")){
                c.move("rotate",-1);  
            }else
            if(target.equals("RIGHT")){
                c.move("rotate",1);
            }else
            if(target.equals("TOP")){
                c.move("flip180",-1); 
            }
            else
            if(target.equals("FRONT")){
                c.move("rotate",-1).move("rotate",-1);
            }else
            if(target.equals("BOTTOM")){
                c.move("flip180",1); 
            }else{
                throw new IllegalAccessError("No move for " + side + " side found -> " + target);
            }   
        }

        return side+":"+target;
    }
}
