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
    private int [] ip;
    private int [] mascara = new int[4];
    private String netId;
    private String broadcast;
    private int expo;
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

    public void NetId(Activity v){
        for(int i = 0;i<4;i++){
            mascara[i]=0;
        }
        Temporal = v.findViewById(R.id.TextoIp);
        String [] temp = Temporal.getText().toString().split(".");
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
        Temp2 = v.findViewById(R.id.TextoNetId);
        String mascra = String.valueOf(mascara[0])+"."+String.valueOf(mascara[1])+"."+String.valueOf(mascara[2])+"."+String.valueOf(mascara[3]);
        Temp2.setText(mascra);

    }

}


