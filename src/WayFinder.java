import java.util.*;

/**
 * Class which provides finding the shortest way on graph
 *
 * @author Łukasz Mielczarek
 * @version 04.11.2016
 */
public class WayFinder {
    private HashMap<String, Node> graph;
    private PriorityQueue<String> queue;
    private ArrayList<String> foundWay;
    private NodeComparator comparator;

    public WayFinder(HashMap<String, Node> graph){
        this.graph = graph;
        this.comparator = new NodeComparator(graph);
        this.foundWay = new ArrayList<>();
    }

    public void runFinder(String start, String goal){
        graph.get(start).setDistance((double) 0);
        this.queue = new PriorityQueue<>(comparator);
        this.queue.add(start);
        dijkstra(start);
        extractShortestWay(start, goal);
    }

    private void dijkstra(String start){
        Node node, tmp;
        Double dst;
        while(!queue.isEmpty()){
            node = graph.get(queue.poll());
            for(int i = 0; i< node.getEdges().size(); i++){
                tmp = graph.get(node.getEdges().get(i));
                dst = tmp.getDistance();
                if(dst > node.getDistance() + node.getDistances().get(i)){
                    tmp.setDistance(node.getDistance() + node.getDistances().get(i));
                    tmp.setPreviousNode(node.getId());
                    queue.add(tmp.getId());
                }
            }
        }
    }

    private void extractShortestWay(String start, String goal){
        String current = goal;
        while(current != null){
            foundWay.add(current);
            current = graph.get(current).getPreviousNode();
        }
        Collections.reverse(foundWay);
    }

    public ArrayList<String> getFoundWay(){
        return foundWay;
    }
}
