package voyageur;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import AlgoritmeGenetique.Ville;
import Interface.Fenaitre;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class AgentVoyageur  extends GuiAgent {
	private static final long serialVersionUID = 1L;
	private Fenaitre interfaceAgent;

	protected void setup() {
		System.out.println("Bonjour Voyageur");

		interfaceAgent = new Fenaitre();
		interfaceAgent.setVisible(true);
		interfaceAgent.setAgentVoyageur(this);
		

		System.out.println("Le Voyageur est pret !");
		  // ParallelBehaviour pour exécuter plusieurs Behaviours 
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		//sous-Behaviour
		addBehaviour(parallelBehaviour);
		 
		// afficher un message chaque fois qu'il s'execute
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {

				MessageTemplate message1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
						//entologie de message
						MessageTemplate.MatchOntology("ok"));
				 //Recevoir les messages
				ACLMessage acl1 = receive(message1);

				MessageTemplate message2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
						MessageTemplate.MatchOntology("villes ordonnees"));
				 //Recevoir les messages 
				ACLMessage acl2 = receive(message2);

				if (acl1 != null) {
					System.out.println("L'emetteur de message : " + acl1.getSender());
					System.out.println("Le destinataire de message : " + acl1.getInReplyTo());
					System.out.println("L'acte de communication : " + acl1.getPerformative());
					System.out.println("LE contenu de message : " + acl1.getContent());
					System.out.println("Langage : " + acl1.getLanguage());
					System.out.println("L'ontology : " + acl1.getOntology());

				} else if (acl2 != null) {

					try {
						Ville[] villeOrdonnee = (Ville[]) acl2.getContentObject();
						interfaceAgent.setVilles(villeOrdonnee);
						interfaceAgent.getPan().setDistances(new ArrayList<Ville>(Arrays.asList(villeOrdonnee)));
						interfaceAgent.getPan().setPlusCourt(true);

					
		              
						interfaceAgent.getPan().repaint();

					} catch (UnreadableException ex) {
						System.out.println(ex);
					}

				} else
					block();

			}
		});
	}

   // methode pour lier notre agent voyageur à l'interface graphique
	@Override
	public void onGuiEvent(GuiEvent event) {
		switch (event.getType()) {
		case 1:
			System.out.println("Dans l'interface");
			Map<String, Object> params = (Map<String, Object>) event.getParameter(0);

			List<Ville> villes = (List<Ville>) params.get("v1");

			ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
			aclMessage.addReceiver(new AID("communiceur", AID.ISLOCALNAME));

			try {
				aclMessage.setContentObject((Serializable) villes);

			} catch (IOException ex) {
				System.out.println(ex);
			}
			aclMessage.setOntology("ok");
			send(aclMessage);
			break;

		default:
			break;

		}
		
	}

	
}