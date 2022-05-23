package com.example.hesapmakinesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaSession2Service;
import android.mtp.MtpConstants;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity
{

    private EditText tv;
    private float deger;

    private String sayi;

    private boolean toplamaKontrol;
    private boolean cikarmaKontrol;
    private boolean carpmaKontrol;
    private boolean bolmeKontrol;
    private boolean hesaplamaKontrol;

    private boolean noktaKontrol;
    private boolean islemKontrol;




    private final String sifir="0";
    private final String bir="1";
    private final String iki="2";
    private final String uc="3";
    private final String dort="4";
    private final String bes="5";
    private final String alti="6";
    private final String yedi="7";
    private final String sekiz="8";
    private final String dokuz="9";

    private final String dNokta=".";

    private final String dArti="+";
    private final String dEksi="-";
    private final String dCarpi="*";
    private final String dBolu="/";
    private final String dEsittir="=";

    Button arti,esittir,eksi,bol,carp,sil;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv =(EditText) findViewById(R.id.cozum);

        sil=(Button) findViewById(R.id.temizle);

        arti=(Button) findViewById(R.id.topla);
        eksi=(Button) findViewById(R.id.cikar);
        carp=(Button) findViewById(R.id.carp);
        bol=(Button) findViewById(R.id.bolme);

        esittir=(Button)findViewById(R.id.hesap);


        deger=0;
        sayi="";

        noktaKontrol=false;
        islemKontrol=false;


        toplamaKontrol=false;
        cikarmaKontrol=false;
        carpmaKontrol=false;
        bolmeKontrol=false;
        hesaplamaKontrol=false;



        arti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(islemKontrol==false) {
                    toplaIslem();
                    tv.append(dArti);
                    noktaKontrol = false;
                    toplamaKontrol = true;
                    sayi = "";
                    islemKontrol=true;
                }

            }
        });
        eksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(islemKontrol==false) {
                    cikarIslem();

                    tv.append(dEksi);
                    noktaKontrol = false;
                    cikarmaKontrol = true;
                    sayi = "";
                    islemKontrol=true;
                }
            }
        });

        carp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(islemKontrol==false) {
                    carpIslem();

                    tv.append(dCarpi);
                    noktaKontrol = false;
                    carpmaKontrol = true;
                    sayi = "";
                    islemKontrol=true;
                }
            }
        });

        bol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(islemKontrol==false) {
                    bolIslem();

                    tv.append(dBolu);
                    noktaKontrol = false;
                    bolmeKontrol = true;
                    sayi = "";
                    islemKontrol=true;
                }
            }
        });

        esittir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (islemKontrol==false) {
                    if (toplamaKontrol) {
                        deger += Float.parseFloat(sayi);
                        toplamaKontrol = false;
                        tv.append(" " + dEsittir + " " + deger);

                    }
                    if (cikarmaKontrol) {
                        deger -= Float.parseFloat(sayi);
                        cikarmaKontrol = false;

                        tv.append(" " + dEsittir + " " + deger);
                    }
                    if (carpmaKontrol) {

                        deger *= Float.parseFloat(sayi);
                        carpmaKontrol = false;

                        tv.append(" " + dEsittir + " " + deger);

                    }
                    if (bolmeKontrol) {


                        if (Float.parseFloat(sayi) > 0) {

                            deger /= Float.parseFloat(sayi);
                            bolmeKontrol = false;

                            tv.append(" " + dEsittir + " " + deger);
                        } else {
                            deger = 0;
                            bolmeKontrol = false;
                            tv.setText("");
                            tv.append("MATH ERROR");
                        }
                    }

                    sayi = "";
                    deger = 0;
                    hesaplamaKontrol = true;
                    noktaKontrol = false;
                    islemKontrol = true;
                }
            }
        });

        sil.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                tv.setText("");
            }
        });

    }

    private void toplaIslem()
    {
        if (!sayi.equals("")) {

            if (deger == 0) {
                deger = Float.parseFloat(sayi);

            } else if (cikarmaKontrol) {
                deger -= Float.parseFloat(sayi);
                cikarmaKontrol = false;

            } else if (carpmaKontrol) {
                deger *= Float.parseFloat(sayi);
                carpmaKontrol = false;

            } else if (bolmeKontrol) {
                deger /= Float.parseFloat(sayi);
                bolmeKontrol = false;

            } else {
                deger += Float.parseFloat(sayi);
            }


        }



    }


    private void cikarIslem()
    {
        if (!sayi.equals("")) {

            if (deger == 0) {
                deger = Float.parseFloat(sayi);

            } else if (toplamaKontrol) {
                deger += Float.parseFloat(sayi);
                toplamaKontrol = false;

            } else if (carpmaKontrol) {
                deger *= Float.parseFloat(sayi);
                carpmaKontrol = false;

            } else if (bolmeKontrol) {
                deger /= Float.parseFloat(sayi);
                bolmeKontrol = false;

            } else {
                deger -= Float.parseFloat(sayi);
            }


        }
    }


    private void carpIslem()
    {
        if (!sayi.equals("")) {

            if (deger == 0) {
                deger = Float.parseFloat(sayi);

            } else if (toplamaKontrol) {
                deger += Float.parseFloat(sayi);
                toplamaKontrol = false;

            } else if (cikarmaKontrol) {
                deger -= Float.parseFloat(sayi);
                cikarmaKontrol = false;

            } else if (bolmeKontrol) {
                deger /= Float.parseFloat(sayi);
                bolmeKontrol = false;

            } else {
                deger *= Float.parseFloat(sayi);
            }


        }

    }


    private void bolIslem()
    {
        if (!sayi.equals("")) {

            if (deger == 0) {
                deger = Float.parseFloat(sayi);

            } else if (toplamaKontrol) {
                deger += Float.parseFloat(sayi);
                toplamaKontrol = false;

            } else if (carpmaKontrol) {
                deger *= Float.parseFloat(sayi);
                carpmaKontrol = false;

            } else if (cikarmaKontrol) {
                deger -= Float.parseFloat(sayi);
                cikarmaKontrol = false;

            } else {

                deger /= Float.parseFloat(sayi);

            }


        }

    }

    public void onClickButton9(View view){
        onClickButtons(this.dokuz);
        islemKontrol=false;
    }

    public void onClickButton8(View view){
        onClickButtons(this.sekiz);
        islemKontrol=false;
    }

    public void onClickButton7(View view){
        onClickButtons(this.yedi);
        islemKontrol=false;
    }

    public void onClickButton6(View view){
        onClickButtons(this.alti);
        islemKontrol=false;
    }

    public void onClickButton5(View view){
        onClickButtons(this.bes);
        islemKontrol=false;
    }

    public void onClickButton4(View view){
        onClickButtons(this.dort);
        islemKontrol=false;

    }

    public void onClickButton3(View view){
        onClickButtons(this.uc);
        islemKontrol=false;

    }

    public void onClickButton2(View view){
        onClickButtons(this.iki);
        islemKontrol=false;

    }

    public void onClickButton1(View view){
        onClickButtons(this.bir);
        islemKontrol=false;

    }

    public void onClickButton0(View view){
        onClickButtons(this.sifir);
        islemKontrol=false;

    }
    public void onClickButtonNokta(View view) {
        if(noktaKontrol==false) {
            onClickButtons(this.dNokta);
            noktaKontrol=true;
            islemKontrol=false;
        }
    }



    private  void onClickButtons(String deger){
        if(tv.getText().toString().equals(sifir)||hesaplamaKontrol)
        {

            hesaplamaKontrol = false;
            tv.setText(deger);

            sayi = deger;


        }
        else
        {
            tv.append(deger);
            sayi+=deger;
        }

    }


}


