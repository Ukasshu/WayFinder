import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Class which provides reading graph from file
 *
 * @author Łukasz Mielczarek
 * @version 01.11.2016
 */
public class GraphReader {
    private File file;
    private Scanner input;
    private HashMap<String, Node> graph;
    private String currentLine;

    public GraphReader(){
        this.file = null;
        this.input = null;
        this.graph =null;
    }

    private void openFile(String filepath){
        file = new File(filepath);
    }

    private void openStream() throws FileNotFoundException{
        input = new Scanner(file);
    }

    private void readEdges(){
        ArrayList<String> ids = null;
        ArrayList<Double> distances = null;
        currentLine = input.nextLine();
        while(!currentLine.matches("(.*)\"nodes\"(.*)") && input.hasNextLine()){
            if(currentLine.matches("(.*)\"nodesRef\":(.*)")){
                ids = new ArrayList<>();
                distances = new ArrayList<>();
                currentLine = input.nextLine();
                while(currentLine.matches("(.*)[0-9]+(.*)")){
                    currentLine = currentLine.replaceAll("\\D+", "");
                    ids.add(currentLine);
                    currentLine = input.nextLine();
                }
                input.nextLine();
                currentLine = input.nextLine();
                while(currentLine.matches("(.*)[0-9]+[.]?[0-9]*(.*)")){
                    currentLine = currentLine.replaceAll("[^\\d.]", "");
                    distances.add(Double.parseDouble(currentLine));
                    currentLine = input.nextLine();
                }
                ListIterator<String> idIterator = ids.listIterator(0);
                ListIterator<Double> distanceIterator = distances.listIterator(0);
                String previous = null;
                String current = idIterator.next();
                Double dist = null;
                graph.put(current, new Node(current));
                while(idIterator.hasNext()){
                    previous = current;
                    current = idIterator.next();
                    dist = distanceIterator.next();
                    graph.put(current, new Node(current));
                    graph.get(current).addEdge(previous, dist);
                    graph.get(previous).addEdge(current, dist);
                }
            }
        }
    }

    private void readNodes(){

    }



    private void readGraph(){
        this.readEdges();
        this.readNodes();
    }
}
