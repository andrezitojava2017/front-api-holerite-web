/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.model;

import java.time.LocalDate;

/**
 *
 * @author andre
 */
public class Cargo {
    
    private String nomeCargo;
    private LocalDate admissao;


    public Cargo() {
    }

    public Cargo(String nomeCargo, LocalDate admissao) {
        this.nomeCargo = nomeCargo;
        this.admissao = admissao;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
