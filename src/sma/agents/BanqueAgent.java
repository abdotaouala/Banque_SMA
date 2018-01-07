/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.agents;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import sma.BanqueContainer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import com.sun.crypto.provider.HmacMD5;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.ControllerException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sma.entities.Demande;

/**
 *
 * @author Octopus
 */
public class BanqueAgent extends GuiAgent{
    private BanqueContainer gui;
    
    
    @Override
	protected void setup() {
		
		gui=(BanqueContainer) getArguments()[0];
		gui.setBanqueAgent(this);
		System.out.println("Initialisation de l'agent "+this.getAID().getName());
		ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {
			
			@Override
			public void action() {
				DFAgentDescription dfa=new DFAgentDescription();
				dfa.setName(getAID());
				ServiceDescription sd=new ServiceDescription();
				sd.setType("Verifier");
				sd.setName("Banque");
				dfa.addServices(sd);
				try {
					DFService.register(myAgent, dfa);//dans un comportement on ne peut pas utiliser this et à la place de this on utilise myAgent
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				ACLMessage message=receive();
				if(message!=null) {
					switch (message.getPerformative()) {
					case ACLMessage.CFP:
						GuiEvent guiEvent=new GuiEvent(this,1);
                                            try {
                                                guiEvent.addParameter((Demande)message.getContentObject());
                                                
                                            } catch (UnreadableException ex) {
                                                ex.printStackTrace();
                                            }
                                            
                                            ACLMessage aclMessage=new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
						
                                            try {
                                                //verification de la demande
                                                aclMessage.setContentObject((Demande)gui.verifierCheque((Demande)message.getContentObject()));
                                                
                                                System.out.println((Demande)aclMessage.getContentObject());
                                                aclMessage.addReceiver(new AID("central",AID.ISLOCALNAME));
                                                send(aclMessage);
						
                                            } catch (Exception ex) {
                                                ex.getMessage();
                                            }	
                                            aclMessage.addReceiver(new AID("central",AID.ISLOCALNAME));
                                            send(aclMessage);
						
                                            gui.viewMessage(guiEvent);
                                            break;

					default:
						break;
					}
				}
				
			}
		});
	}
        
    @Override
    protected void onGuiEvent(GuiEvent ge) {
       
    }
    
}
