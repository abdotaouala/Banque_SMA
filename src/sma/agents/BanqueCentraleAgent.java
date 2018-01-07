/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.agents;

import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.wrapper.ControllerException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sma.BanqueCentralContainer;
import sma.entities.Demande;
/**
 *
 * @author Octopus
 */
public class BanqueCentraleAgent extends GuiAgent{
    
    private BanqueCentralContainer gui;
	private AID[] listAgenceBanque;
	@Override
	protected void setup() {
		gui=(BanqueCentralContainer) getArguments()[0];
		gui.setCentraleAgent(this);
		System.out.println("Initialisation de l'agent "+this.getAID().getName());
		
		ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,6000) {
			
			@Override
			protected void onTick() {
                            
				try {
					DFAgentDescription description=new DFAgentDescription();
					ServiceDescription serviceDescription=new ServiceDescription();
					serviceDescription.setType("Verifier");
					DFAgentDescription[] agentDescriptions=DFService.search(myAgent, description);
					listAgenceBanque=new AID[agentDescriptions.length];
					for(int i=0;i<agentDescriptions.length;i++) {
						listAgenceBanque[i]=agentDescriptions[i].getName();
					}
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                                
			}
		});
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {	
			@Override
			public void action() {
				MessageTemplate messageTemplate=MessageTemplate.or(
						MessageTemplate.MatchPerformative(ACLMessage.REQUEST)
						, MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));
				ACLMessage message=receive(messageTemplate);
				if(message!=null) {
                                    switch (message.getPerformative()) {
					case ACLMessage.REQUEST:
                                    {
                                        try {
                                            System.out.println("Reception du message : "+(Demande)message.getContentObject());
                                            //GuiEvent guiEvent=new GuiEvent(this, 1);
                                            Demande d = (Demande)message.getContentObject();
						//guiEvent.addParameter(d);
                                                //gui.viewMessage(guiEvent);
                                                ACLMessage aclMessage=new ACLMessage(ACLMessage.CFP);
						aclMessage.setContentObject(d);
						for(AID aid:listAgenceBanque) {
                                                    System.out.println(aid.getLocalName());
                                                    System.out.println("------------");
                                                    if(aid.getLocalName().equals(d.getCodeBanque()))
                                                        {
                                                          aclMessage.addReceiver(aid);
                                                        }
							
						}
						send(aclMessage);
                                        } catch (Exception ex) {
                                            ex.printStackTrace();;
                                        }
                                    }
						break;
						
					case ACLMessage.ACCEPT_PROPOSAL:
						
						ACLMessage aclMsg=new ACLMessage(ACLMessage.REQUEST);
						try {
                                                    Demande de = (Demande)message.getContentObject();
                                                        
                                                        GuiEvent guiEvent=new GuiEvent(this, 1);
                                                        guiEvent.addParameter(de);
                                                        gui.viewMessage(guiEvent);
                                                        if(de.isStatut()==true)
                                                        {
                                                            aclMsg.setContent("Le Chéque est certifié avec succé");
                                                        }
                                                        else
                                                        {
                                                            aclMsg.setContent("Ce chéque ne peut pas le cirtifié");
                                                        }
							
							aclMsg.addReceiver(new AID(de.getCommercant().getCodeCommercant()+"",AID.ISLOCALNAME));
							send(aclMsg);
                                                        break;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						

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
