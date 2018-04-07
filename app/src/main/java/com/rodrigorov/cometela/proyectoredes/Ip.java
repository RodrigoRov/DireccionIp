package com.rodrigorov.cometela.proyectoredes;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Cometela on 6/4/2018.
 */

public class Ip {
    private int [] ip = new int[4];
    private int [] mascara = new int[4];
    private String netId;
    private String broadcast;
    private int expo;
    private int [] negacion;
    private int hosts;
    EditText Temporal;
    TextView Temp2;

    public void CantidaddeHost(Activity v){
        Temporal = v.findViewById(R.id.Textomascara);
        Temp2 = v.findViewById(R.id.TextoHost);
        expo = Integer.parseInt(Temporal.getText().toString());
        hosts = (int) Math.pow(2,(32-expo))-2;
        if(hosts<0)
            hosts=1;
        Temp2.setText(String.valueOf(hosts));
    }

    public void Broadcast(Activity v){
        for(int i = 0;i<4;i++){
            mascara[i]=0;
        }
        Temporal = v.findViewById(R.id.TextoIp);
        String [] temp = Temporal.getText().toString().split("\\.");
        for(int i =0;i<temp.length;i++){
            ip[i]=Integer.parseInt(temp[i]);
        }
        int accu=0;
        for(int i=expo-1,j = 7;i>=0;i--,j--){
            if(i==7){
                j=7;
                accu=0;
                accu = accu + (int)Math.pow(2,j);
                mascara[0] = accu;
            }
            else if(i<7){
                accu = accu + (int)Math.pow(2,j) ;
                mascara[0] = accu;
            }

            else if(i==15){
                j=7;
                accu=0;
                accu = accu + (int)Math.pow(2,j);
                mascara[1] = accu;
            }
            else if(i<15){
                accu =  accu + (int)Math.pow(2,j);
                mascara[1] = accu;
            }
            else if(i==23){
                accu=0;
                j=7;
                accu = accu + (int)Math.pow(2,j);
                mascara[2] = accu;
            }
            else if(i<23){
                accu = accu +(int)Math.pow(2,j);
                mascara[2] = accu;
            }
            else if(i<32){
                accu = accu +(int) Math.pow(2, j);
                mascara[3] = accu;
            }
        }
        negacion = new int[mascara.length];
        int [] resultado = new int [mascara.length];
        for(int i = 0;i<mascara.length;i++){
            negacion[i] = 255- mascara[i];
        }
        for(int i = 0;i<mascara.length;i++){
            resultado[i] = ip[i]|negacion[i];
        }
        Temp2 = v.findViewById(R.id.Textobroadcast);
        String mascra = String.valueOf(resultado[0])+"."+String.valueOf(resultado[1])+"."+String.valueOf(resultado[2])+"."+String.valueOf(resultado[3]);
        Temp2.setText(mascra);

    }

    public void ParteRed(Activity v){
        for(int i = 0;i<mascara.length;i++){
            if(mascara[i]!=0){

            }
        }
    }

}


