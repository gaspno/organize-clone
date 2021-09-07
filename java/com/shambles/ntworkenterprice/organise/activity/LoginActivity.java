package com.shambles.ntworkenterprice.organise.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.shambles.ntworkenterprice.organise.R;
import com.shambles.ntworkenterprice.organise.config.ConfiguracaoFirabase;
import com.shambles.ntworkenterprice.organise.model.Usuario;


public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail,campoSenha;
    private Button buttonEntrar;
    private FirebaseAuth auth;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylogin);
        campoEmail=findViewById(R.id.editEmail);
        campoSenha=findViewById(R.id.editPass);
        buttonEntrar=findViewById(R.id.buttonEntrar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email=campoEmail.getText().toString();
                String password=campoSenha.getText().toString();

                if(!email.isEmpty()){
                        if(!password.isEmpty())
                        {

            usuario=new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(password);
            validarLogin();
            abrirTelaPrincipal();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Digite uma senha",Toast.LENGTH_SHORT).show();

                        }
                    }

                    else{
                        Toast.makeText(getApplicationContext(),"Digite um email",Toast.LENGTH_SHORT).show();
                    }
                }



        });

    }

    private void abrirTelaPrincipal() {

        startActivity(new Intent(this,PrincipalActivity.class));
        finish();

    }

    private void validarLogin() {

        auth= ConfiguracaoFirabase.getAuth();
        auth.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Sucesso ao faze login",Toast.LENGTH_SHORT).show();
                }else {
                    String mensagemDeException;

                    try {

                        throw task.getException();


                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        mensagemDeException="Senha errada e invalida";

                    }
                    catch (FirebaseAuthInvalidUserException e){

                        mensagemDeException="Digite um email consistente membro da gangue bo bem";
                    }
                    catch (Exception e) {
                        mensagemDeException="Erro ao logat"+e.getMessage();
                    }

                    Toast.makeText(getApplicationContext(),mensagemDeException,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
