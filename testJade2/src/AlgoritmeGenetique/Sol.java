package AlgoritmeGenetique;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Sol {
	
	/* Cette classe est cree pour l'ensemble des solutions ou l'ensemble des chemins trouves   */
	
	private Chemin chemins[];     //---- liste des chemins possibles
	private double score = -1;    //---- represente le fitness de chaque chemin

	

	//Initialisation  des solutions (Chemins)
	public Sol(int taille) {
		this.chemins = new Chemin[taille];
	}
	
	
	public Sol(int taille, int tailleVilleParcourus) {
	
		this.chemins = new Chemin[taille];
		
		
		//Rempllissage  des solutions
		for(int i = 0; i< taille; i++) {
			
		
			Chemin chemin = new Chemin(tailleVilleParcourus);
			
			//----- Ajouter le chemin aux solutions
			this.chemins[i] = chemin;
			
		}
	}


	//------- Getters & Setters
	public Chemin[] getChemins() {
		return chemins;
	}


	public void setChemins(Chemin[] chemins) {
		chemins = chemins;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}
	
	//----- la taille de l'ensemble des solutions
	public int taille() {
		return this.chemins.length;
	}
	
	//----- ????????????????????????
	public Chemin setChemin(Chemin chemin, int offset) {
		return this.chemins[offset] = chemin;
	}
	
	
	//----- retourner le chemin ???????????
	public Chemin getChemin(int offset) {
		return chemins[offset];
	}
	
	
	//-------- Trouver le chemin le plus optimale
	public Chemin CheminApte(int offset ) {
		
		//----- Ordonner  solutions selon le score
		
		Arrays.sort(this.chemins, new Comparator<Chemin>() {
			
			@Override
			public int compare(Chemin ch1, Chemin ch2) {
				if(ch1.getScore() > ch2.getScore()) 
					return -1; 
							
				else if(ch1.getScore() < ch2.getScore()) 
					return 1;
							
				return 0;
				}
			});
		
		return chemins[offset];
	}
	
	
	//----- Melanger l'enseble des solutions d'une maniere aleatoire
	public void Melanger() {
		Random random = new Random();
		for(int i = this.taille() - 1; i > 0; i--) {
			
			int j = random.nextInt(i+1);
			Chemin chemin = this.chemins[j];
			this.chemins[j] = this.chemins[i];
			this.chemins[i] = chemin;
		}
	}
		
				
		
		
	
		
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
