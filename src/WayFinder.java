import java.util.*;

/**
 * Class which provides finding the shortest way on graph
 *
 * @author Łukasz Mielczarek
 * @version 04.11.2016
 */
public class WayFinder {
    private HashMap<String, Node> graph;
    private PriorityQueue<Node> queue;
    private ArrayList<Node> foundWay;
    private NodeComparator comparator;

    public WayFinder(HashMap<String, Node> graph){
        this.graph = graph;
        this.comparator = new NodeComparator();
        this.foundWay = new ArrayList<>();
    }

    public void runFinder(String start, String goal){
        graph.get(start).setDistance((double) 0);
        this.queue = new PriorityQueue<>(comparator);
        this.queue.add(graph.get(start));
        dijkstra();
        extractShortestWay(start, goal);
    }

    private void dijkstra(){
        Node node, tmp;
        Double dst;
        while(!queue.isEmpty()){
            node = queue.poll();
            for(int i = 0; i< node.getEdges().size(); i++){
                tmp = node.getEdges().get(i);
                dst = tmp.getDistance();
                if(dst > node.getDistance() + node.getDistances().get(i)){
                    tmp.setDistance(node.getDistance() + node.getDistances().get(i));
                    tmp.setPreviousNode(node);
                    queue.add(tmp);
                }
            }
        }
    }

    private void extractShortestWay(String start, String goal){
        Node current = graph.get(goal);
        while(current != null){
            foundWay.add(current);
            current = current.getPreviousNode();
        }
        Collections.reverse(foundWay);
    }

    public ArrayList<Node> getFoundWay(){
        return foundWay;
    }

    public boolean isOnGraph(double latitude, double longitude){
        return graph.containsValue(new Node(latitude, longitude));
    }

    public boolean isOnGraph(String id){
        return graph.containsKey(id);
    }
}
