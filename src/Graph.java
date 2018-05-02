import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

    List<Vertex> vertices;
    Vertex[][] board;

    public Graph(int numVertices){
        board = new Vertex[5][5];
        Random rand = new Random();
        boolean isEulerPath = rand.nextBoolean(); //if true, 2 odd degree vertices, else all even degree vertices

        vertices = new ArrayList<>(numVertices);
        for(int i = 0; i < numVertices; i++){
            Vertex v = new Vertex();
            if (isEulerPath && i < 2) v.odd = true;
            vertices.add(v);

            while (true) { //initialize random points in 2D array
                int randY = rand.nextInt(5);
                int randX = rand.nextInt(5);
                if (board[randY][randX] == null) {
                    board[randY][randX] = v;
                    v.y = randY;
                    v.x = randX;
                    break;
                }
            }
        }

        //generate walk across all vertices
        for (int i = 0; i < vertices.size()-1; i++) vertices.get(i).connect(vertices.get(i + 1));

        for (int i = 0; i < vertices.size(); i++){
            Vertex v1 = vertices.get(i);
            if (v1.odd){
                Vertex v2 = vertices.get(rand.nextInt(vertices.size()));
                if (!v2.odd){
                    v1.connect(v2);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        JFrame frame = new JFrame("Line Draw");
        frame.setSize(1000,1000);
        Canvas canvas = new Canvas(graph);
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
