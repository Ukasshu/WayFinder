import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by lukasz on 04.11.16.
 */
public class NodeComparator implements Comparator<Node>{
    HashMap<String, Node> nodes;

    public int compare(Node node1, Node node2){
        double dst1 = node1.getDistance();
        double dst2 = node2.getDistance();
        if(dst1 < dst2){
            return -1;
        }
        if(dst1 > dst2){
            return 1;
        }
        return 0;
    }
}
