package stoneydata.com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import stoneydata.com.Cube.C;
import stoneydata.com.Cube.S;

public class CubeResolver implements ICubeResolver {
    Map<Cube.S,List<Face>> columns;
    /**
     * Ordered list for grid
     * index,Side e.g. 1, Front
     */
    public final static List<Cube.S> sidemap = 
            Arrays.asList(new Cube.S[]{
                Cube.S.FRONT,Cube.S.BACK,Cube.S.LEFT,
                Cube.S.RIGHT,Cube.S.TOP,Cube.S.BOTTOM}) ;

   
    /**
     * TOP (4) FRONT RIGHT BACK LEFT (0) 1 2 3 BOTTOM (5)
     */
    @Override
    public Map<Cube.S, List<C>> compute(List<Cube> cList) {
        // System.out.println("compute() -> probability");
        this.columns = new HashMap<Cube.S, List<Face>>();
        List<Face> rows = null;
        Map<Cube.S, List<C>> probability = new HashMap<Cube.S, List<C>>();
        Iterator<Cube.S> layout = CubeResolver.sidemap.iterator();
        while (layout.hasNext()) {
            Cube.S groupBy = layout.next();
            rows = new LinkedList<Face>();

            List<C> odds = 
            new LinkedList<C>(Arrays.asList(
                    new C[] { C.B, C.G, C.R, C.Y }));// column contains all

            for (int r = 0; r < 4; r++) { // rows
                Face f = cList.get(r).getFace(groupBy);
                odds.remove(f.getColour());

                rows.add(f);
            }

            probability.put(groupBy, odds);
            this.columns.put(groupBy, rows);
        }

        return probability;
    }

    
    public Map<Cube.S,List<Face>> getColumns() {
        return this.columns;
    }
    /**
     * 
     * @param c
     * @param from
     * @return
     */
	public String findChoices(List<Cube> cList, Map<Cube.S,List<C>> prob,List<C> colours, S from) {
        StringBuilder map = new StringBuilder(""+from+"{");
        int cnt =0;
        for(int i=0; i<cList.size(); i++){

           Cube c = cList.get(i);//row
           for(int j=0; j<6; j++){ //col
              if( j != CubeResolver.sidemap.indexOf(from)){ //exclude SIDE
                 Face f = c.getFaces().get(j);
                 if(!locked(prob,f.getSide())){
                    if( colours.contains(f.getColour())){
                        map.append((i+1)+":" + f.getSide()+",");  
                        cnt++;                                      
                    }  
                 }else{
                    // System.out.println("Locked " + f.getSide());
                     return null;
                 }
              }
           }

        }

        if(cnt ==0){
            return null;
        }else
		    return map.append("}") .toString().replaceAll(",}", "}");
    }
    
    /**
     * 
     */
    private boolean locked(Map<S, List<C>> prob, S side) {
		return prob.get(side).isEmpty();
	}
   
}