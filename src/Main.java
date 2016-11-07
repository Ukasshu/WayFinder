import Exceptions.GraphNotReadYetException;

import java.io.FileNotFoundException;

/**
 * Created by lukasz on 05.11.16.
 */
public class Main {
    public static void main(String[] args){
        GraphReader graphReader = new GraphReader();
        try {
            graphReader.openFile("/home/lukasz/public_html/output.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            graphReader.readGraph();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            WayFinder wayFinder = new WayFinder(graphReader.returnGraph());
            wayFinder.runFinderDijkstra("251691138", "251689105");
            for(Node n: wayFinder.getFoundWay()){
                System.out.println(n);
            }
            System.out.println();
            System.out.println();
            wayFinder.runFinderAStar("251691138", "251689105");
            for(Node n: wayFinder.getFoundWay()){
                System.out.println(n);
            }
        }catch (GraphNotReadYetException e){
            e.printStackTrace();
        }

    }
}
