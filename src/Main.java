import Exceptions.GraphNotReadYetException;

import java.io.FileNotFoundException;

/**
 * Created by lukasz on 05.11.16.
 */
public class Main {
    public static void main(String[] args){
        GraphReader graphReader = new GraphReader();
        try {
            graphReader.openFile("/home/lukasz/output.json");
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
            wayFinder.runFinder("2110897859", "251691838");
            System.out.println(wayFinder.getFoundWay());
        }catch (GraphNotReadYetException e){
            e.printStackTrace();
        }

    }
}
