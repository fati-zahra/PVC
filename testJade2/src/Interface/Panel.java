package Interface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import AlgoritmeGenetique.Ville;



public class Panel extends JPanel {
	private List<Ville> villes = new ArrayList<Ville>(); 
	private List<Ville> distances = new ArrayList<Ville>(); 
	public int WIDTH_MAP = 900;
	public int HEIGHT_MAP = 300;
	public Image imageVille;
	public Image imageBackground;
	private boolean plusCourt = false;
	//private int annuler;
	public static int s=0;
	public Panel() {
		setBackground(new Color(245, 255, 250));
		this.setBounds(0, 0, WIDTH_MAP, HEIGHT_MAP);
		setLayout(null);
		
		

}
	/* ----- L'arriere plan (la carte) du panel ----- */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		this.imageBackground = new ImageIcon(Panel.class.getResource("/images/maroc.jpg")).getImage();

		if (imageBackground != null) {
			g.drawImage(imageBackground, 0, 0, this);
		}
	}	
	/* ----- Getters & Setters ----- */
	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public List<Ville> getDistances() {
		return distances;
	}

	public void setDistances(List<Ville> distances) {
		this.distances = distances;
	}

	public boolean isPlusCourt() {
		return plusCourt;
	}

	public void setPlusCourt(boolean plusCourt) {
		this.plusCourt = plusCourt;
	}

	/*public int getAnnuler() {
		return annuler;
	}

	public void setAnnuler(int etapeActuelle) {
		this.annuler = etapeActuelle;
	}*/

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);

		for (Ville ville : villes) {
		      dessinerVille(g2d, ville);
		}
		
		dessinerPlusCourtDistance(g2d);
	}



	public void dessinerDistance(Graphics2D g, Ville ville1, Ville ville2) {
		if (ville1 != null && ville2 != null) {
			int x1 = (int) ville1.getPosX();
			int y1 = (int) ville1.getPosY();
			int x2 = (int) ville2.getPosX();
			int y2 = (int) ville2.getPosY();
            //s++;
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(2));
			g.drawLine(x1, y1, x2, y2);
			 g.fillOval(x1-10, y1-10,20,20);
			 g.setColor(Color.BLACK);
			  
		}
	}
	public void dessinerVille(Graphics2D g, Ville ville) {
		int x = (int) ville.getPosX();
		int y = (int) ville.getPosY();

		
		g.setColor(Color.RED);
		g.fillOval(x - 20, y - 5, 1, 1);
		               
	               
	}
	
	public void dessinerPlusCourtDistance(Graphics2D g2d) {
		List<Ville> mesDistances = this.distances;
	
		for (int i = 0; i < mesDistances.size() - 1; i++) {
			/*if (annuler ==100) {
				System.out.println("Annuler: " + annuler );
				return;
			}*/
	            			dessinerDistance(g2d, mesDistances.get(i), mesDistances.get(i + 1));
	            		}
	              
	}
}
	
