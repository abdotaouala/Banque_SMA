/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.table.DefaultTableModel;
import sma.agents.BanqueCentraleAgent;
import sma.entities.Demande;
/**
 *
 * @author Octopus
 */
public class BanqueCentralContainer extends javax.swing.JFrame {

    /**
     * Creates new form BanqueCentralContainer
     */
    HashMap<Long, Demande> ds;
    String col1[] = {"Num demande","Date Création","Code compte", "Code chéque", "Montant", "Code Commerçant", "Statut"};
    
    private BanqueCentraleAgent CentraleAgent;


    public BanqueCentraleAgent getCentraleAgent() {
        return CentraleAgent;
    }

    public void setCentraleAgent(BanqueCentraleAgent CentraleAgent) {
        this.CentraleAgent = CentraleAgent;
    }
    
    public BanqueCentralContainer() {
        initComponents();
        startContainer();
        
        ds = new HashMap<>();
    }
    public void startContainer() {
		try {
			Runtime rt=Runtime.instance();
			ProfileImpl profile=new ProfileImpl(false); 
			profile.setParameter(ProfileImpl.MAIN_HOST, "localhost"); 
			AgentContainer ac=rt.createAgentContainer(profile);
			/*deployer un agent*/
			AgentController agentController=ac.createNewAgent
					("central", "sma.agents.BanqueCentraleAgent", new Object[]{this});
			agentController.start();
			
			} catch (ControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    
    public void viewMessage(GuiEvent guiEvent) {
        DefaultTableModel tableM = new DefaultTableModel(col1, 0);
        table_ds.setModel(tableM);
        Demande aa = (Demande)guiEvent.getParameter(0);
        ds.put(aa.getCodeDemande(), aa);
        String r="Accepté";
        for(Demande a : ds.values())
        {
            if(a.isStatut())
            {
               r="Réfusé";
            }
            Object[] ob = {a.getCodeDemande(), a.getDateDemande().toString(), 
                a.getCodeCompte(), a.getCodeCheque(), a.getMontant(), a.getCommercant().getCodeCommercant(), r};
                tableM.addRow(ob);
        }       
	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_ds = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Banque Centrale");

        table_ds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_ds);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanqueCentralContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanqueCentralContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanqueCentralContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanqueCentralContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanqueCentralContainer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_ds;
    // End of variables declaration//GEN-END:variables
}