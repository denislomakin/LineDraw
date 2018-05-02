import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    Graph graph;
    Vertex[][] board;

    public Canvas(Graph graph){
        this.graph = graph;
        this.board = graph.board;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(5));
        for (Vertex v1: graph.vertices) {
            int x1 = toX(v1.x);
            int y1 = toY(v1.y);
            for (Edge e: v1.adjacencyList){
                Vertex v2 = e.partner(v1);
                int x2 = toX(v2.x);
                int y2 = toY(v2.y);
                g2.drawLine(x1, y1, x2, y2);
            }
            g2.fillOval(x1-10,y1-10,20,20);
        }
    }

    int toY(int y){
        int interval = (this.getHeight()-200)/board.length;
        return 100+(y*interval);
    }

    int toX(int x){
        int interval = (this.getWidth()-200)/board.length;
        return 100+(x*interval);
    }
}
