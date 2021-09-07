package com.shambles.ntworkenterprice.organise.model;

import com.google.firebase.database.DatabaseReference;
import com.shambles.ntworkenterprice.organise.config.ConfiguracaoFirabase;
import com.shambles.ntworkenterprice.organise.helper.Base64Custom;

import java.text.SimpleDateFormat;

public class Movimentacao {

    private String data,configuration,description,tipe;
    private double valor;


    public Movimentacao(String data, String configuration, String description, String tipe, double valor) {
        this.data = data;
        this.configuration = configuration;
        this.description = description;
        this.tipe = tipe;
        this.valor = valor;
    }

    public Movimentacao() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public  void salvar(String data){
        DatabaseReference reference= ConfiguracaoFirabase.getFirebase();

        String date[]=data.split("/");
        String[] ano=date[2].split(" ");
        String mesAno=date[1]+ano[0];


        reference.child("movimentação").child(Base64Custom.codificarBase64(ConfiguracaoFirabase.getAuth().getCurrentUser().getEmail())).child(mesAno).push().setValue(this);




    }
}
