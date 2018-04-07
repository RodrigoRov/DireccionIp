package com.rodrigorov.cometela.proyectoredes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DirrecionIP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dirrecion_ip);

    }

    public void Hacer(View v){

        Ip nuevaIp = new Ip();
        nuevaIp.CantidaddeHost(this);
        nuevaIp.NetId(this);
    }


}
