/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Octopus
 */
public class Banque implements Serializable{
    private String codeBanque;
    private Collection<Compte> comptes= new ArrayList<Compte>();

    public Banque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public Collection<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Compte c) {
        this.comptes.add(c);
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    @Override
    public String toString() {
        return "Banque{" + "codeBanque=" + codeBanque + ", comptes=" + comptes + '}';
    }
    
}
