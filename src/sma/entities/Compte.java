/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Octopus
 */
public class Compte implements Serializable{
    private String codeCompte;
    private double solde;
    private String nomClient;
    private Date dateCreation;
    private Collection<Cheque> cheques = new ArrayList<Cheque>();
    
    
    public Compte(String codeCompte, double solde, String nomClient, Date dateCreation) {
        this.codeCompte = codeCompte;
        this.solde = solde;
        this.nomClient = nomClient;
        this.dateCreation = dateCreation;
    }
    public Compte() {
        
    }

    public Collection<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(Cheque c) {
        this.cheques.add(c);
    }
    
    public String getCodeCompte() {
        return codeCompte;
    }

    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Compte{" + "codeCompte=" + codeCompte + ", solde=" + solde + ", nomClient=" + nomClient + ", dateCreation=" + dateCreation + ", cheques=" + cheques + '}';
    }
    
}
