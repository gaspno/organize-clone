package com.shambles.ntworkenterprice.organise.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirabase {


    private static FirebaseAuth auth;
    private static DatabaseReference reference;

    public static FirebaseAuth getAuth(){


        if(auth==null) {

            auth = FirebaseAuth.getInstance();

        }
        return  auth;
    }
    public static DatabaseReference getFirebase(){

        if(reference==null){

            reference= FirebaseDatabase.getInstance().getReference();
        }
        return  reference;
    }

}
