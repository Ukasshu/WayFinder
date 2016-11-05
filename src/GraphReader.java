import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;

import Exceptions.*;

/**
 * Class which provides reading graph from file
 *
 * @author Łukasz Mielczarek
 * @version 04.11.2016
 */
public class GraphReader {
    private File file;
    private Scanner input;
    private HashMap<String, Node> graph;
    private String currentLine;
    private boolean graphRead;

    public GraphReader(){
        this.file = null;
        this.input = null;
        this.graph = null;
    }

    public void openFile(String filepath) throws FileNotFoundException{
        if(input!=null){
            input.close();
        }
        file = new File(filepath);
        graphRead = false;
        input = new Scanner(file);
        graph = new HashMap<>();
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
                String current = idIterator.next();
                Node previousNode = null;
                Node currentNode = null;
                Double dist = null;
                if(!graph.containsKey(current)){
                    currentNode = new Node(current);
                    graph.put(current, currentNode);
                }
                else{
                    currentNode = graph.get(current);
                }
                while(idIterator.hasNext()){
                    previousNode = currentNode;
                    current = idIterator.next();
                    dist = distanceIterator.next();
                    if(!graph.containsKey(current)) {
                        currentNode = new Node(current);
                        graph.put(current, currentNode);
                    }
                    else{
                        currentNode = graph.get(current);
                    }
                    currentNode.addEdge(previousNode, dist);
                    previousNode.addEdge(currentNode, dist);
                }
            }
            currentLine = input.nextLine();
        }
    }

    private void readNodes(){
        String id;
        double longitude;
        double latitude;
        while(input.hasNextLine()){
            if(currentLine.matches("(.*)[{](.*)")){
                currentLine = input.nextLine();
                id = currentLine.replaceAll("[^\\d.]","");
                currentLine = input.nextLine();
                latitude = Double.parseDouble(currentLine.replaceAll("[^\\d.]",""));
                currentLine = input.nextLine();
                longitude = Double.parseDouble(currentLine.replaceAll("[^\\d.]",""));
                if(graph.containsKey(id)) {
                    graph.get(id).setCoordinates(longitude, latitude);
                }
            }
            currentLine = input.nextLine();
        }
    }

    public HashMap<String, Node> returnGraph() throws GraphNotReadYetException {
        if(!graphRead){
            throw new GraphNotReadYetException("Graph has not been already read!");
        }
        return graph;
    }

    public void readGraph(){
        this.readEdges();
        this.readNodes();
        this.graphRead = true;
    }

}
