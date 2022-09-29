package optimiseur;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import voyageur.AgentVoyageur;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import AlgoritmeGenetique.CourtChemin;
import AlgoritmeGenetique.Ville;

public class AgentOptimiseur extends Agent {

	
	@Override
	protected void setup() {

		System.out.println("Bonjour Optimiseur");

	
		ParallelBehaviour comportementparallele = new ParallelBehaviour();
		
		addBehaviour(comportementparallele);

	
		comportementparallele.addSubBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {

				
				MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
						MessageTemplate.MatchOntology("Calcul"));
				
				ACLMessage reponse1 = receive(mt1);

				if (reponse1 != null) {

					try {
						//  reponse1 
						List<Ville> villes = (List<Ville>) reponse1.getContentObject();

					
						CourtChemin courtChemin = new CourtChemin();

						//les villes dont la distance entre eux est minimale
						Ville[] villesOrdonnees = courtChemin.getPlusCourteDist(villes);

						ACLMessage reponse2 = new ACLMessage(ACLMessage.INFORM);
						
						reponse2.addReceiver(new AID("communiceur", AID.ISLOCALNAME));
						
						reponse2.setContentObject(villesOrdonnees);
						reponse2.setOntology("chemin optimal");
						
						send(reponse2);

					} catch (UnreadableException e) {

						System.out.println(e);
					} catch (IOException e) {

						System.out.println(e);
					}
				}

			
				else
					block();

			}
		});
	}
	       

}
