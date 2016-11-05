import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by lukasz on 04.11.16.
 */
public class NodeComparator implements Comparator<String>{
    HashMap<String, Node> nodes;

    public NodeComparator(HashMap<String, Node> nodes){
        this.nodes = nodes;
    }

    public int compare(String node1, String node2){
        double dst1 = nodes.get(node1).getDistance();
        double dst2 = nodes.get(node2).getDistance();
        if(dst1 < dst2){
            return -1;
        }
        if(dst1 > dst2){
            return 1;
        }
        return 0;
    }
}
