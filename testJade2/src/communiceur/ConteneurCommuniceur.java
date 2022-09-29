package communiceur;

import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.core.Runtime;

public class ConteneurCommuniceur {
	
	public static  void main(String[] args){
        try{
           
            Runtime runtime=Runtime.instance();
            
            ProfileImpl profileImpl=new ProfileImpl(false);
            
            profileImpl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
         
            AgentContainer agentContainer = runtime.createAgentContainer(profileImpl);
            
            AgentController agentController = agentContainer.createNewAgent("communiceur", AgentCommuniceur.class.getName(), new Object[]{});
            
            agentController.start();

        } catch (ControllerException e){
            e.printStackTrace();
        }
    }
	
}
