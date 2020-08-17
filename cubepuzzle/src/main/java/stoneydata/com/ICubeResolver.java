package stoneydata.com;

import java.util.List;
import java.util.Map;

import stoneydata.com.Cube.C;

public interface ICubeResolver {

    Map<Cube.S,List<C>> compute(List<Cube> cList);
}
