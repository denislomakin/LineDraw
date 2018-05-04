import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LineDraw{

	
	public static void main (String[] args) {
		JFrame frame = new JFrame("Line Draw");
		frame.setSize(800,800);
		Graph graph = new Graph(18);
		Canvas canvas = new Canvas(graph);
		frame.add(canvas,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		JButton refresh = new JButton("Refresh");
		JButton restore = new JButton("Restart");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph g = new Graph(18);
				canvas.refresh(g);
			}
		});
		
		restore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.restore();
			}
		});
		panel.add(refresh);
		panel.add(restore);
		frame.add(panel, BorderLayout.NORTH);
		frame.setVisible(true);
	
	}

	
}
