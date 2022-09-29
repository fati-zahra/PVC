package AlgoritmeGenetique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourtChemin {
	
	public static int maxGenerations = 15000;
	
	
	public double meilleureDistance;
	public Ville villes[];
	
	//-------------- Getters && Setters -----------------
	
	public double getMeilleureDistance() {
		return meilleureDistance;
	}
	
	public void setMeilleureDistance(double meilleureDistance) {
		this.meilleureDistance = meilleureDistance;
	}
	

	
	public Ville[] getVilles() {
		return villes;
	}
	public void setVilles(Ville[] villes) {
		this.villes = villes;
	}
	
	
	

	
	//-------- Retourne un tableau des villes dont la distance entre eux est minimale ---
	public Ville[] getPlusCourteDist(List<Ville> villesList) {
		
		//- tableau des villes à partir de la liste donnée
		this.villes = villesList.toArray(new Ville[villesList.size()]);
		
		AlgorithmeGenetique aGenit = new AlgorithmeGenetique(100, 0.001, 0.9, 2, 5);
		
		
		Sol sol = aGenit.initSolutions(villes.length);
		
		//définir le score du chemin
		aGenit.definitScoreChemin(sol, villes);
		
		Distance debutDist = new Distance(sol.CheminApte(0), villes);
	
		//une génération
		int generation = 1;
		
		while(aGenit.conditionFin(generation, maxGenerations) == false) {
			
			//score du chemin à partir de la solution
			Distance d = new Distance(sol.CheminApte(0), villes);
			
			sol = aGenit.crossoverSolution(sol);
			
			sol = aGenit.muterSolution(sol);
			
			
			aGenit.definitScoreChemin(sol, villes);
			
		
			generation++;
			
		}
		
		//--- La meilleure distance ----
		Distance d = new Distance(sol.CheminApte(0), villes);
		meilleureDistance = d.getDistance();
		
		List<Ville> lv = new ArrayList<Ville>(Arrays.asList(d.getParcours()));
		
		lv.add(lv.get(0));
		
		return lv.toArray(new Ville[villesList.size()]);
		
		
	}

}
