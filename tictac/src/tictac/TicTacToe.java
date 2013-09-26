package tictac;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToe {
	TicTacToe(){
		new TicTacWindow(100);
	}
	//Git test commit
}

class TicTacWindow extends JFrame{
	final int boxSize;
	boolean circleTurn = true;

	TicTacWindow(int boxSize){
		this.boxSize = boxSize;
		setLayout(new FlowLayout());
		addComponents();
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	class SquareJPanel extends JPanel {
		private char owner;
		private boolean owned;
		SquareJPanel(Color color, int size){
			super(new CardLayout());
			setBackground(color);
			setMinimumSize(new Dimension(size, size));
			setMaximumSize(new Dimension(size, size));
			setPreferredSize(new Dimension(size, size));
		}

		boolean isOwned(){
			return owned;
		}

		char getOwner(){
			return owner;
		}

		void setOwned(boolean isOwned){
			owned = isOwned;
		}

		void setOwner(char own){
			setOwned(true);
			owner = own;
		}
	}

	class XPanel extends SquareJPanel{
		XPanel(Color color, int size){
			super(color, size);
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			g2.setColor(Color.RED);
			g2.drawLine(0, 0, boxSize, boxSize);
			g2.drawLine(0, boxSize, boxSize, 0);
			g2.dispose();
		}
	}

	class OPanel extends SquareJPanel{
		OPanel(Color color, int size){
			super(color, size);
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			super.paintComponent(g);
			g2.setColor(Color.BLUE);
			Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, boxSize, boxSize);
			g2.draw(circle);
			g2.dispose();
		}
	}

	class BoxGrid extends JPanel{
		JPanel[][] boxarr = new JPanel[3][3];
		BoxGrid(){
			setLayout(new GridLayout(3,3));
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if ((j + i) % 2 == 0){
						boxarr[i][j] = new SquareJPanel(Color.black, boxSize);
					}
					else{
						boxarr[i][j] = new SquareJPanel(Color.gray, boxSize);
					}
					boxarr[i][j].addMouseListener(new PanelListener());
					add(boxarr[i][j]);
				}
			}
		}
		
		int inRow(int row, char owner){
			int total = 0;
			for(int i = 0; i < 2; i++){
				//if (boxarr[0][]);
			}
			return 0;
		}
	}

	class PanelListener implements MouseListener{
		PanelListener(){

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			SquareJPanel source = (SquareJPanel) e.getSource();
			if(!source.isOwned()){
				if(circleTurn){
					source.add(new OPanel(source.getBackground(), boxSize), "");
					source.setOwner('O');
				}
				else{
					source.add(new XPanel(source.getBackground(), boxSize), "");
					source.setOwner('X');
				}
				CardLayout layout = (CardLayout) (source.getLayout());
				layout.next(source);
				circleTurn = !circleTurn;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}



	private void addComponents(){
		getContentPane().add(new BoxGrid());
	}
}

