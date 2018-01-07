/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.entities;

import java.io.Serializable;
import java.util.Date;
import sma.CommercantContainer;

/**
 *
 * @author Octopus
 */
public class Demande implements Serializable{
    private Long codeDemande;
    private Date dateDemande;
    private double montant;
    private CommercantContainer commercant;
    private String codeCompte;
    private String codeCheque;
    private String codeBanque;
    private boolean statut;
    public static Long incrementDemande=1L;

    public Demande(Date dateDemande, double montant, String codeCompte, String codeCheque, String codeBanque, boolean statut) {
        this.codeDemande=incrementDemande;
        this.dateDemande = dateDemande;
        this.montant = montant;
        this.codeCompte = codeCompte;
        this.codeCheque = codeCheque;
        this.codeBanque = codeBanque;
        this.statut = statut;
        incrementDemande++;
    }
    public Demande(){
        
    }
    public Long getCodeDemande() {
        return codeDemande;
    }

    public void setCodeDemande(Long codeDemande) {
        this.codeDemande = codeDemande;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public CommercantContainer getCommercant() {
        return commercant;
    }

    public void setCommercant(CommercantContainer commercant) {
        this.commercant = commercant;
    }

    public String getCodeCompte() {
        return codeCompte;
    }

    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    public String getCodeCheque() {
        return codeCheque;
    }

    public void setCodeCheque(String codeCheque) {
        this.codeCheque = codeCheque;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Demande{" + "codeDemande=" + codeDemande + ", dateDemande=" + dateDemande + ", montant=" + montant + ", commercant=" + commercant.getCodeCommercant() + ", codeCompte=" + codeCompte + ", codeCheque=" + codeCheque + ", codeBanque=" + codeBanque + ", statut=" + statut + '}';
    }
}
