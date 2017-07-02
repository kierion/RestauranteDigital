package trabalhofinal.usj.com.br.restaurantedigital.entity;

import java.io.Serializable;

/**
 * Created by Édipo on 01/07/2017.
 */

public class Menu implements Serializable{

    private Integer id, spinner;
    private double preco;
    private String nomePrato, descricao;

    public Menu() {}

    public Menu(Integer id, Integer spinner, Double preco, String nomePrato, String descricao) {
        this.id = id;
        this.spinner = spinner;
        this.preco = preco;
        this.nomePrato = nomePrato;
        this.descricao = descricao;
    }

    public Menu(String spiner, String valor, String nomePrato, String description) {
    }

    //private byte[] foto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpinner() {
        return spinner;
    }

    public void setSpinner(Integer spinner) {
        this.spinner = spinner;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
