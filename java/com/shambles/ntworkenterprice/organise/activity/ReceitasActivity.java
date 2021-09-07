package com.shambles.ntworkenterprice.organise.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.shambles.ntworkenterprice.organise.R;
import com.shambles.ntworkenterprice.organise.config.ConfiguracaoFirabase;
import com.shambles.ntworkenterprice.organise.helper.Base64Custom;
import com.shambles.ntworkenterprice.organise.helper.DateCustom;
import com.shambles.ntworkenterprice.organise.model.Movimentacao;
import com.shambles.ntworkenterprice.organise.model.Usuario;

public class ReceitasActivity extends AppCompatActivity {

    private TextInputEditText campoData,campoCategoria,campoDescription;
    private EditText campoValor;
   private Movimentacao movimentacao;
   private Double despesaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        campoValor=findViewById(R.id.editValor);
        campoData=findViewById(R.id.editData);
        campoDescription=findViewById(R.id.editDescription);
        campoCategoria=findViewById(R.id.editCategoria);

        campoData.setText(DateCustom.dataAtual());

        recuperarReceita();

        




    }
    public void salvarDespesas(View view){
if(validarCampo()) {
    movimentacao = new Movimentacao();
    movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));
    movimentacao.setConfiguration(campoCategoria.getText().toString());
    movimentacao.setDescription(campoDescription.getText().toString());
    movimentacao.setTipe("r");
    movimentacao.setData(campoData.getText().toString());

    String data = campoData.getText().toString();

    movimentacao.salvar(data);
}



    }
    public boolean validarCampo(){

        if(!campoValor.getText()
        .toString().isEmpty()){
            if(!campoData.getText() .toString().isEmpty()){
                if(!campoCategoria.getText() .toString().isEmpty()){
                    if(!campoDescription.getText() .toString().isEmpty()){

                        return true;

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"preencha descrição seu tonto",Toast.LENGTH_SHORT).show();
                        return  false;


                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"preencha categoria seu tonto",Toast.LENGTH_SHORT).show();
                    return  false;


                }

            }
            else{
                Toast.makeText(getApplicationContext(),"preencha data seu tonto",Toast.LENGTH_SHORT).show();
                return  false;


            }

        }
        else{
            Toast.makeText(getApplicationContext(),"preencha valor seu tonto",Toast.LENGTH_SHORT).show();
            return  false;


        }



    }
    public void recuperarReceita(){

        String idUsuario= Base64Custom.codificarBase64(ConfiguracaoFirabase.getAuth().getCurrentUser().getEmail());
        DatabaseReference reference= ConfiguracaoFirabase.getFirebase().child("usuários").child(idUsuario);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Usuario usuario=dataSnapshot.getValue(Usuario.class);
                despesaTotal=usuario.getReceitaTotal();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });

    }

}