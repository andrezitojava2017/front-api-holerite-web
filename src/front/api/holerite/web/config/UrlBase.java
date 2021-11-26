/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.config;

import java.util.Properties;



/**
 *
 * @author andre
 */
public class UrlBase {
    
    private static String URL_BASE;
    private static String END_POINT_GET_USUARIO;
    private static String END_POINT_GET_LISTA_USUARIOS;
    private static String END_POINT_GET_LISTA_ORGAOS;
    private static String END_POINT_GET_USUARIO_POR_CPF;
    private static String END_POINT_GET_ORGAO_CNPJ;
    private static String END_POINT_GET_FUNCINARIO_POR_CPF;
    private static String END_POINT_GET_LIST_FUNCIONARIOS;
    private static String END_POINT_GET_CONTRA_CHEQUE_FUNCIONARIO;
    private static String END_POINT_POST_USUARIO;
    private static String END_POINT_POST_ORGAO;
    private static String END_POINT_POST_FUNCIONARIO;
    private static String END_POINT_POST_UPLOAD_ARQ_PROVENTOS;
    private static String END_POINT_POST_UPLOAD_ARQ_FUNCIONARIOS;
    private static String END_POINT_PUT_FUNCIONARIO;
    private static String END_POINT_PUT_ORGAO;
    private static String END_POINT_PUT_USUARIO;
    
    public UrlBase(){
        
        Config cnfg = new Config();
        Properties dados = cnfg.lerPropriedades();
        
        this.URL_BASE = dados.getProperty("URL.base");
        this.END_POINT_GET_USUARIO = dados.getProperty("GET.usuario");
        this.END_POINT_GET_LISTA_USUARIOS = dados.getProperty("GET.usuario");
        this.END_POINT_GET_USUARIO_POR_CPF = dados.getProperty("GET.usuario");
        this.END_POINT_GET_LISTA_ORGAOS = dados.getProperty("GET.lista.orgao");
        this.END_POINT_GET_ORGAO_CNPJ = dados.getProperty("GET.lista.orgao");
        this.END_POINT_GET_FUNCINARIO_POR_CPF = dados.getProperty("GET.funcionario.cpf");
        this.END_POINT_GET_LIST_FUNCIONARIOS = dados.getProperty("GET.lista.funcionario.cnpj");
        this.END_POINT_GET_CONTRA_CHEQUE_FUNCIONARIO = dados.getProperty("GET.contracheque.funcionario=/contracheque/\n" +
"");
        this.END_POINT_POST_USUARIO = dados.getProperty("GET.usuario");
        this.END_POINT_POST_ORGAO = dados.getProperty("POST.orgao");
        
    }

    public static String getURL_BASE() {
        return URL_BASE;
    }


    public static String getEND_POINT_GET_USUARIO() {
        return END_POINT_GET_USUARIO;
    }

    public static String getEND_POINT_GET_LISTA_USUARIOS() {
        return END_POINT_GET_LISTA_USUARIOS;
    }

    public static String getEND_POINT_GET_LISTA_ORGAOS() {
        return END_POINT_GET_LISTA_ORGAOS;
    }

    public static String getEND_POINT_GET_USUARIO_POR_CPF() {
        return END_POINT_GET_USUARIO_POR_CPF;
    }

    public static String getEND_POINT_GET_ORGAO_CNPJ() {
        return END_POINT_GET_ORGAO_CNPJ;
    }

    public static String getEND_POINT_GET_FUNCINARIO_POR_CPF() {
        return END_POINT_GET_FUNCINARIO_POR_CPF;
    }

    public static String getEND_POINT_GET_LIST_FUNCIONARIOS() {
        return END_POINT_GET_LIST_FUNCIONARIOS;
    }

    public static String getEND_POINT_GET_CONTRA_CHEQUE_FUNCIONARIO() {
        return END_POINT_GET_CONTRA_CHEQUE_FUNCIONARIO;
    }

    public static String getEND_POINT_POST_USUARIO() {
        return END_POINT_POST_USUARIO;
    }

    public static String getEND_POINT_POST_ORGAO() {
        return END_POINT_POST_ORGAO;
    }

    public static String getEND_POINT_POST_FUNCIONARIO() {
        return END_POINT_POST_FUNCIONARIO;
    }

    public static String getEND_POINT_POST_UPLOAD_ARQ_PROVENTOS() {
        return END_POINT_POST_UPLOAD_ARQ_PROVENTOS;
    }

    public static String getEND_POINT_POST_UPLOAD_ARQ_FUNCIONARIOS() {
        return END_POINT_POST_UPLOAD_ARQ_FUNCIONARIOS;
    }

    public static String getEND_POINT_PUT_FUNCIONARIO() {
        return END_POINT_PUT_FUNCIONARIO;
    }

    public static String getEND_POINT_PUT_ORGAO() {
        return END_POINT_PUT_ORGAO;
    }

    public static String getEND_POINT_PUT_USUARIO() {
        return END_POINT_PUT_USUARIO;
    }
    
    
    

}
