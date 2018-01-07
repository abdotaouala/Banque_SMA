package sma.entities;

import java.io.Serializable;

/**
 *
 * @author Octopus
 */
public class Cheque implements Serializable{
    private String codeCheque;
    private double montant_bloquer;

    public Cheque(String codeCheque) {
        this.codeCheque = codeCheque;
        this.montant_bloquer=0;
    }

    public String getCodeCheque() {
        return codeCheque;
    }

    public void setCodeCheque(String codeCheque) {
        this.codeCheque = codeCheque;
    }

    public double getMontant_bloquer() {
        return montant_bloquer;
    }

    public void setMontant_bloquer(double montant_bloquer) {
        this.montant_bloquer = montant_bloquer;
    }

    @Override
    public String toString() {
        return "Cheque{" + "codeCheque=" + codeCheque + ", montant_bloquer=" + montant_bloquer + '}';
    }
    
    
}
