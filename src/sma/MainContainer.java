/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;
/**
 *
 * @author Octopus
 */
public class MainContainer {
    public static void main(String[] args) {
		try {
			Runtime runtime=Runtime.instance();
			Properties properties=new ExtendedProperties();
			//pour afficher l'interface de démarrage
			properties.setProperty(Profile.GUI, "true");
			Profile profile= new ProfileImpl(properties);
			AgentContainer mainContainer = runtime.createMainContainer(profile);
			mainContainer.start();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
