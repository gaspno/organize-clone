package com.shambles.ntworkenterprice.organise.activity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.shambles.ntworkenterprice.organise.R;
import com.shambles.ntworkenterprice.organise.config.ConfiguracaoFirabase;
import com.shambles.ntworkenterprice.organise.helper.Base64Custom;
import com.shambles.ntworkenterprice.organise.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome,campoEmail,campoSenha;
    private Button buttonCadastrar;
    private FirebaseAuth auth;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycadastro);

        campoNome=findViewById(R.id.editName);
        campoEmail=findViewById(R.id.editEmail);
        campoSenha=findViewById(R.id.editPassword);
        buttonCadastrar=findViewById(R.id.buttonCadastrar);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome=campoNome.getText().toString();
                String email=campoEmail.getText().toString();
                String password=campoSenha.getText().toString();

                if(!nome.isEmpty()){
                    if(!email.isEmpty()){
                        if(!password.isEmpty())
                        {
                            usuario=new Usuario(nome,email,password);

                            cadastrarUsuario();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Digite uma senha",Toast.LENGTH_SHORT).show();

                        }
                    }

                    else{
                        Toast.makeText(getApplicationContext(),"Digite um email",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Digite um nome",Toast.LENGTH_LONG).show();

                }



            }
        });


    }

    private void cadastrarUsuario() {

            auth= ConfiguracaoFirabase.getAuth();
            auth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Sucesso",Toast.LENGTH_SHORT).show();

                        String idUsuario= Base64Custom.codificarBase64(usuario.getEmail());
                        usuario.setIdUsuario(idUsuario);
                        usuario.salvar();
                        finish();
                    }
                    else{
                        String mensagemDeException;
                        try{

                            throw task.getException();

                        }catch(FirebaseAuthWeakPasswordException e){
                            mensagemDeException="Digite uma senha mais firme";
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            mensagemDeException="Digite um email válido";
                        }
                        catch (FirebaseAuthUserCollisionException e){
                            mensagemDeException="Conta ja registrada";
                        }
                        catch (Exception e){
                            mensagemDeException="Erro ao cadastrar o usaurio"+e.getMessage();
                        }
                        Toast.makeText(getApplicationContext(),mensagemDeException,Toast.LENGTH_SHORT).show();
                    }


                }
            });


    }
}
