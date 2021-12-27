/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author andre
 */
public class Cargo {
    
    private String nomeCargo;
    private Date admissao;


    public Cargo() {
    }

    public Cargo(String nomeCargo, String admissao) throws ParseException {
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.admissao = format.parse(admissao);
        this.nomeCargo = nomeCargo;
    }
    
    public Cargo(String nomeCargo, Date admissao) {
        this.nomeCargo = nomeCargo;
        this.admissao = admissao;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
