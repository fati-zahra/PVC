package communiceur;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import AlgoritmeGenetique.Ville;

public class AgentCommuniceur extends Agent {


    @Override
    protected void setup(){

        System.out.println("Bonjour Communiceur");

    
        ParallelBehaviour comportementparallele = new ParallelBehaviour();
        //ajoueter Behaviour
        addBehaviour(comportementparallele);

        //CyclicBehaviour affiche les messages
        comportementparallele.addSubBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {

                //template est utlisé pour recevoir des messages
                MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ok"));
               
                //Recevoir les messages des autres agents
                ACLMessage reponse1 = receive(mt1);
                
                MessageTemplate mt2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("chemin optimal"));
                ACLMessage reponse2 = receive(mt2);

                if(reponse1 != null){
                    try{
                        // reponse1 
                        List<Ville> villes = (List<Ville>)reponse1.getContentObject();

                        ACLMessage reponse3 = new ACLMessage(ACLMessage.REQUEST);

                       
                        reponse3.addReceiver(new AID("optimiseur", AID.ISLOCALNAME));
                     
                        reponse3.setContentObject((Serializable) villes);
                        reponse3.setOntology("Calcul");
                       
                        send(reponse3);
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(reponse2 != null){
                    try {

                        Ville[] villesOrdonnees = (Ville[]) reponse2.getContentObject();

                        ACLMessage reponse3 = new ACLMessage(ACLMessage.INFORM);

                        //Modification des paramétres de la requete ACLMessage
                        reponse3.addReceiver(new AID("Voyageur", AID.ISLOCALNAME));
                        //On met le tableau des villes ordonnï¿½es dans le message
                        reponse3.setContentObject((Serializable) villesOrdonnees);
                        reponse3.setOntology("villes ordonnees");
                        //Envoi de message
                        send(reponse3);

                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //On bloque le Behaviour pour ne pas planifier son execution
                else block();

            }
        });
    }
}
