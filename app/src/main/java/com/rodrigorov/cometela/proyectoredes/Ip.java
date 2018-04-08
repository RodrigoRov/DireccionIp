package com.rodrigorov.cometela.proyectoredes;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Cometela on 6/4/2018.
 */

public class Ip {
    private int [] ip = new int[4];
    private int [] mascara = new int[4];
    private int [] netId = new int[4];
    private int expo;
    private int [] negacion;
    private int hosts;
    EditText Temporal;
    TextView Temp2;

    //Sacante la cantidad de Host
    public void CantidaddeHost(Activity v){
        Temporal = v.findViewById(R.id.Textomascara);
        Temp2 = v.findViewById(R.id.TextoHost);
        expo = Integer.parseInt(Temporal.getText().toString());
        hosts = (int) Math.pow(2,(32-expo))-2;
        if(hosts<0)
            hosts=1;
        Temp2.setText(String.valueOf(hosts));
    }
    //Sacando direccion broadcast
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
        //metodo para sacar mascara de red en base a exponentes
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
        //negando mascara de red
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
    //sacando NetID
    public void NetId(Activity v){
        for(int i=0;i<mascara.length;i++){
            netId[i] = mascara[i]&ip[i];
        }
        Temp2 = v.findViewById(R.id.TextoNetId);
        String res="";
        for(int i=0; i<netId.length;i++){
            if(i==3)
                res=res+String.valueOf(netId[i]);
            else
                res = res+String.valueOf(netId[i])+".";

        }
        Temp2.setText(res);
    }
    //Sacando parte de Red
    public void ParteRed(Activity v){
        String red = "";
        for(int i = 0;i<mascara.length;i++){
            if((mascara[i]&ip[i]) !=0){
                if(i==3)
                    red=red + String.valueOf(ip[i]);
                else
                    red=red + String.valueOf(ip[i]+".");
            }
            else
                break;
        }
        if(expo%8 != 0){
            red = red + " /" + String.valueOf(expo);
        }
        Temp2 = v.findViewById(R.id.TextoRed);
        Temp2.setText(red);
    }
    //Sacando parte de Host
    public void ParteHost(Activity v){
        int [] aux = new int [4];
        String host ="";
        for(int i = 0;i<4;i++){
            aux [i] = negacion[i]&ip[i];
        }
        for(int i = 0;i<4;i++){
            if(aux[i] != 0 )
                host = host +"."+ String.valueOf(aux[i]);
        }
        if(expo%8 != 0){
            host = host + " /" + String.valueOf(expo);
        }
        Temp2 = v.findViewById(R.id.TextoParteHost);
        Temp2.setText(String.valueOf(host));
    }
    //Verificar si la Ip o mascara contiene algo
    public boolean Verify(Activity v){
        Temporal = v.findViewById(R.id.TextoIp);
        EditText Temp = v.findViewById(R.id.Textomascara);
        if(Temporal.getText().length() == 0 || Temp.getText().length()==0){
            Toast toast = Toast.makeText(v.getApplicationContext(),"Por Favor ingresar IP y Mascara de red",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        else {
            return true;
        }
    }

}


