package com.shambles.ntworkenterprice.organise.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.shambles.ntworkenterprice.organise.config.ConfiguracaoFirabase;

public class Usuario {

    private String nome,email,senha,idUsuario;
    private double receitaTotal,despesaTotal=0.00;

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }
    public void salvar(){

        DatabaseReference databaseReference= ConfiguracaoFirabase.getFirebase();
        databaseReference.child("usuários").child(this.idUsuario).setValue(this);

    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
