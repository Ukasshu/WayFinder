import java.util.*;

/**
 * Class which provides finding the shortest way on graph
 *
 * @author Łukasz Mielczarek
 * @version 04.11.2016
 */
public class WayFinder {
    /**
     * HashMap holding all Nodes
     */
    private HashMap<String, Node> graph;
    /**
     * PriorityQueue used in Dijksrta's algorithm
     */
    private PriorityQueue<Node> queue;
    /**
     * ArrayList that holds the shortest way found on graph
     */
    private ArrayList<Node> foundWay;
    /**
     * Comparator used to determine the Nodes' priority in the PriorityQueue
     */
    private NodeComparator comparator;

    /**
     * WayFinder's construcyor
     * @param graph HashMap with all read Nodes
     */
    public WayFinder(HashMap<String, Node> graph){
        this.graph = graph;
        this.comparator = new NodeComparator();
        this.foundWay = new ArrayList<>();
    }

    /**
     * Starts the work of the WayFinder
     * @param start ID of the start Node
     * @param goal ID of the goal Node
     */
    public void runFinder(String start, String goal){
        this.queue = new PriorityQueue<>(comparator);
        for(Node n: graph.values()){
            n.setDistance(Double.MAX_VALUE);
        }
        graph.get(start).setDistance((double) 0);
        this.queue.add(graph.get(start));
        dijkstra();
        extractShortestWay(start, goal);
    }

    /**
     * Implements working of the Djikstra's algorithm
     */
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

    /**
     * Extracts the path which is the shortest way from start to goal
     * @param start ID of the start Node
     * @param goal ID of the goal Node
     */
    private void extractShortestWay(String start, String goal){
        Node current = graph.get(goal);
        foundWay = new ArrayList<>();
        while(current != null){
            foundWay.add(current);
            current = current.getPreviousNode();
        }
        Collections.reverse(foundWay);
        if(!foundWay.get(0).equals(graph.get(start))){
            foundWay = null;
        }
    }

    /**
     * Returns ArrayList with the shortest way from start to goal
     * @return
     */
    public ArrayList<Node> getFoundWay(){
        return foundWay;
    }
}
