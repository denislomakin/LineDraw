import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Canvas extends JPanel implements MouseListener {
	
	static Vertex temp;
    Graph graph;
    Vertex[][] board;
    static HashMap<Shape,Vertex> shapes = new HashMap<Shape,Vertex>();

    static ArrayList<Vertex> usedvertexes = new ArrayList<Vertex>();
    public Canvas(Graph graph){
        this.graph = graph;
        this.board = graph.board;
        addMouseListener(this);
    }
    
    public void refresh(Graph g) {
    		this.graph = g;
    		this.board = g.board;
    		usedvertexes.clear();
    		repaint();
    }

    public void restore() {
    		shapes.clear();
    		usedvertexes.clear();
    		repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(5));
        //for each vertex
        int a=0;
        Color col;
        for (Vertex v1: graph.vertices) {
        	col= new Color(a,200-a,a);
        	g2.setColor(col);
            int x1 = toX(v1.x);
            int y1 = toY(v1.y);
            //for every edge adjacent to that vertex
            for (Edge e: v1.adjacencyList){
                Vertex v2 = e.partner(v1);
                int x2 = toX(v2.x);
                int y2 = toY(v2.y);
                g2.drawLine(x1, y1, x2, y2);
            
            }
            shapes.put(new Ellipse2D.Double(x1-10,y1-10,20,20),v1);

            g2.fillOval(x1-10,y1-10,20,20);
            a+=5;
        }
        for (Vertex v2:usedvertexes){
        	g2.setColor(Color.YELLOW);
        	int x1 = toX(v2.x);
            int y1 = toY(v2.y);
            g2.fillOval(x1-10,y1-10,20,20);
        }
        repaint();
    }

    int toY(int y){
        int interval = (this.getHeight()-200)/board.length;
        return 100+(y*interval);
    }

    int toX(int x){
        int interval = (this.getWidth()-200)/board.length;
        return 100+(x*interval);
    }

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
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	

	}