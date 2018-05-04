import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.awt.BorderLayout;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Graph {
	static Vertex temp;


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
        Graph graph = new Graph(18);
        JFrame frame = new JFrame("Line Draw");
        frame.setLayout(new BorderLayout());
        frame.setSize(800,800);
        Canvas canvas = new Canvas(graph);
        canvas.addMouseListener(new MouseListener(){
        	@Override
        	public void mouseClicked(MouseEvent e){
        		System.out.println("click");
        		int x = e.getX();
        		int y = e.getY();
        	
        		int index = 0;
        		for (Shape a:Canvas.shapes.keySet()){      
        			index++;
        			if (a.contains(x, y)&&Canvas.usedvertexes.isEmpty()){
        				 temp = Canvas.shapes.get(a);
        				Canvas.usedvertexes.add(temp);
        			}
        			else if (a.contains(x, y)) {
        				System.out.println(temp.adjacencyList.get(1));
        				for (int z =0; z<temp.adjacencyList.size(); z++) {
        					if (temp.adjacencyList.get(z).v2.equals(Canvas.shapes.get(a))) {
        						temp = Canvas.shapes.get(a);
            					Canvas.usedvertexes.add(temp);
        					}else {
            					System.out.println("Cant do this!");
            				}
        				}
        					
        				
        				
        				}
        			}
        	}
        		
       

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        frame.add(canvas,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}