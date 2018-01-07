/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.agents;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sma.CommercantContainer;
import sma.entities.Demande;
/**
 *
 * @author Octopus
 */
public class CommercantAgent extends GuiAgent{
    
    
            
    private CommercantContainer gui;
    protected void setup() {
		gui=(CommercantContainer) getArguments()[0];
		gui.setCommercantAgent(this);
		System.out.println("Initialisation de l'agent "+this.getAID().getName());
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				MessageTemplate messageTemplate=MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
				ACLMessage message=receive(messageTemplate);
				if(message!=null) {
					
					try {
						GuiEvent guiEvent=new GuiEvent(this, 1);
						guiEvent.addParameter(message.getContent());
						gui.viewMessage(guiEvent);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
			}
		});
	}
    
    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
        if(guiEvent.getType()==1) {
		ACLMessage aclMessage=new ACLMessage(ACLMessage.REQUEST);
		Demande d =(Demande)guiEvent.getParameter(0);
            try {
                aclMessage.setContentObject(d);
                aclMessage.addReceiver(new AID("central",AID.ISLOCALNAME));
		send(aclMessage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    }
    
}
