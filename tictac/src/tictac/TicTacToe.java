package tictac;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToe {
	TicTacToe(){
		new TicTacGame();
	}
	//Git test commit
}

class TicTacGame{
	private boolean circleTurn;
	private boolean squareTurn;
	TicTacGame(){
		this.circleTurn = true;
		this.squareTurn = false;
		new TicTacWindow(100, this);
	}
	
	public boolean isCircleTurn(){
		return circleTurn;
	}
	
	public boolean isSquareTurn(){
		return squareTurn;
	}
	
	public void setCircleTurn(boolean circleTurn){
		this.circleTurn = circleTurn;
		this.squareTurn = !circleTurn;
	}
	
	public void setSquareTurn(boolean squareTurn){
		this.squareTurn = squareTurn;
		this.circleTurn = !squareTurn;
	}
}

@SuppressWarnings("serial")
class TicTacWindow extends JFrame{
	int boxSize;
	TicTacGame Game;
	
	private void addComponents(){
		getContentPane().add(new BoxGrid());
	}

	TicTacWindow(int boxSize, TicTacGame Game){
		this.boxSize = boxSize;
		this.Game = Game;
		setLayout(new FlowLayout());
		addComponents();
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	class SquareJPanel extends JPanel {
		private Owner OwnerObject;
		private boolean owned;
		private SquareJPanel(Color color, int size){
			super(new CardLayout());
			setBackground(color);
			setMinimumSize(new Dimension(size, size));
			setMaximumSize(new Dimension(size, size));
			setPreferredSize(new Dimension(size, size));
		}
		
		public void addOwnedPanel(String owner){
			addOwner(new Owner(owner, this));
			if(OwnerObject.toString().equals("X")){
				Game.setCircleTurn(true);
			}
			else if(OwnerObject.toString().equals("O")){
				Game.setSquareTurn(true);
			}
		}

		public boolean isOwned(){
			return owned;
		}

		public Owner getOwner(){
			return OwnerObject;
		}
		
		public String getOwnerAsString(){
			return OwnerObject.toString();
		}

		private void setOwned(boolean isOwned){
			owned = isOwned;
		}

		public void addOwner(Owner OwnerObject){
			this.OwnerObject = OwnerObject;
			setOwned(true);
		}
	}
	
	class Owner{
		String owner;
		SquareJPanel OwnerPanel;
		SquareJPanel ContainerPanel;
		Owner(String owner, SquareJPanel ContainerPanel){
			this.owner = owner;
			this.ContainerPanel = ContainerPanel;
			if(owner.equals("X")){
				OwnerPanel = new XPanel(ContainerPanel.getBackground(), boxSize);
			}
			else if(owner.equals("O")){
				OwnerPanel = new OPanel(ContainerPanel.getBackground(), boxSize);
			}
			ContainerPanel.add(OwnerPanel, "");
		}
		
		public String toString(){
			return owner;
		}
	}

	class XPanel extends SquareJPanel{
		private XPanel(Color color, int size){
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
		private OPanel(Color color, int size){
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
		private JPanel[][] boxarr = new JPanel[3][3];
		private BoxGrid(){
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
	}

	class PanelListener implements MouseListener{
		private PanelListener(){
			//Make constructor private
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			SquareJPanel source = (SquareJPanel) e.getSource();
			if(!source.isOwned()){
				if(Game.isCircleTurn()){
					source.addOwnedPanel("O");
				}
				else{
					source.addOwnedPanel("X");
				}
				CardLayout layout = (CardLayout) (source.getLayout());
				layout.next(source);
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
}

