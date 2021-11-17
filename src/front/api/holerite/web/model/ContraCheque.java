/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.model;

import java.util.List;

/**
 *
 * @author andre
 */
public class ContraCheque {

    private String id;
    private Funcionario funcionario;
    private String competencia;
    private List<ProventoDescontoNeutro> proventos;
    private List<ProventoDescontoNeutro> descontos;

    public ContraCheque() {
    }

    public ContraCheque( Funcionario funcionario, Orgao orgao, String competencia) {
        this.funcionario = funcionario;
        this.competencia = competencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProventoDescontoNeutro> getProventos() {
        return proventos;
    }

    public void setProventos(List<ProventoDescontoNeutro> proventos) {
        this.proventos = proventos;
    }

    public List<ProventoDescontoNeutro> getDescontos() {
        return descontos;
    }

    public void setDescontos(List<ProventoDescontoNeutro> descontos) {
        this.descontos = descontos;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }
}
