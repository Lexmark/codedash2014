import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Element {
    public Element(String name) {this.name = name;}
    public String name;
    public String parent = null;
    public List<String> links = new ArrayList<>();
}

public class Application {
    public static void main(String[] args) throws Exception {
        Map<String, Element> graph = new HashMap<>();
        // Keep order for later linking
        List<String> elementNames = new ArrayList<>();

        // No safety checking.  Assuming input is always good
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Get starting and ending elements
        String[] choices = br.readLine().split(",");
        String starting = choices[0];
        String ending = choices[1];

        List<String> recipes = new ArrayList<>();

        // Read all input from stdin
        String stdInLine;
        while ((stdInLine = br.readLine()) != null) {
            recipes.add(stdInLine);
        }

        // Create Elements
        recipes.forEach(line -> {
            String name = line.substring(0, line.indexOf(','));
            elementNames.add(name);
            graph.put(name, new Element(name));
        });

        // Link Elements
        recipes.forEach(line -> {
            String[] record = line.split(",");

            Element element = graph.get(record[0]);
            for (int field = 1; field < record.length; field++) {
                if (record[field].equals("1")) {
                    element.links.add(elementNames.get(field - 1));
                }
            }
        });

        // Breadth First Search to our target
        Element targetFound = null;
        Queue<Element> bfsQueue = new ArrayDeque<>();

        Element startElement = graph.get(starting);
        startElement.parent = "**FIRST_ELEMENT**";
        bfsQueue.add(startElement);

        while (!bfsQueue.isEmpty()) {
            Element current = bfsQueue.remove();

            if (current.name.equals(ending)) {
                targetFound = current;
                break;
            }

            for (String entry : current.links) {
                Element neighbor = graph.get(entry);

                if (neighbor.parent != null) {
                    continue;
                }

                neighbor.parent = current.name;
                bfsQueue.add(neighbor);
            }
        }

        // Traverse back to the beginning
        List<String> result = new ArrayList<>();

        while (targetFound != null) {
            result.add(0, targetFound.name);
            targetFound = graph.get(targetFound.parent);
        }

        System.out.println(String.join(",", result));
    }
}