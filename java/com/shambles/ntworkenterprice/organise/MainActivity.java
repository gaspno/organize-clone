package com.shambles.ntworkenterprice.organise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.shambles.ntworkenterprice.organise.activity.CadastroActivity;
import com.shambles.ntworkenterprice.organise.activity.LoginActivity;
import com.shambles.ntworkenterprice.organise.activity.PrincipalActivity;
import com.shambles.ntworkenterprice.organise.config.ConfiguracaoFirabase;

public class MainActivity extends IntroActivity {

    private FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();
        verify();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);



        addSlide(new FragmentSlide.Builder().background(android.R.color.holo_blue_dark).fragment(R.layout.intro_1).build());
        addSlide(new FragmentSlide.Builder().background(android.R.color.holo_blue_dark).fragment(R.layout.intro_2).build());
        addSlide(new FragmentSlide.Builder().background(android.R.color.holo_blue_dark).fragment(R.layout.intro_3).build());
        addSlide(new FragmentSlide.Builder().background(android.R.color.holo_blue_dark).fragment(R.layout.intro_4).build());
        addSlide(new FragmentSlide.Builder().background(android.R.color.holo_blue_dark).fragment(R.layout.intro_cadastro).canGoForward(false).build());





    }
    public void btCadastro(View view){


        startActivity(new Intent(this, CadastroActivity.class));



    }
    public void btEntrar(View view){

        startActivity(new Intent(this, LoginActivity.class));




    }
    public void verify() {

        auth = ConfiguracaoFirabase.getAuth();

        if (auth.getCurrentUser() != null) {

            abrirTelaPrincipal();


        }
    }
        private void abrirTelaPrincipal() {

            startActivity(new Intent(this, PrincipalActivity.class));


        }





}
