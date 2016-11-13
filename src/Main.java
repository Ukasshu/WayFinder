import Exceptions.GraphNotReadYetException;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Created by lukasz on 05.11.16.
 */
public class Main {
    public static void main(String[] args){
        GraphReader graphReader = new GraphReader();
        try {
            graphReader.openFile("/home/lukasz/public_html/output.json");
            graphReader.readGraph();
            WayFinder wayFinder = new WayFinder(graphReader.returnGraph());
            long startTime = System.currentTimeMillis();
            wayFinder.runFinderDijkstra("251691138", "251689105");
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
            System.out.println();
            System.out.println();
            startTime = System.currentTimeMillis();
            wayFinder.runFinderAStar("251691138", "251689105");
            endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            /*for(Node n: wayFinder.getFoundWay()){
                System.out.println(n);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
