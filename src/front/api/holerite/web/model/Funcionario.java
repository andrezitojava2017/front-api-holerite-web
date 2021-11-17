/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.model;

/**
 *
 * @author andre
 */
public class Funcionario {

    private String id;
    private String nomeFuncionario;
    private String cpf;
    private Cargo cargo;
    private Orgao orgao;


    public Funcionario() {

    }

    public Funcionario(String id, String nomeFuncionario, String cpf, Cargo cargo, Orgao orgao) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.cargo = cargo;
        this.orgao = orgao;
    }

    public Funcionario(String nomeFuncionario, String cpf, Cargo cargo, Orgao orgao) {
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.cargo = cargo;
        this.orgao = orgao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }
}
