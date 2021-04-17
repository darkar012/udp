package com.example.clienteudp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMessageListener {
    private Button mango, perro, sandwich, danone;
    private UDPconnection udp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mango = findViewById(R.id.manguito);
        danone = findViewById(R.id.danone);
        perro = findViewById(R.id.perrito);
        sandwich = findViewById(R.id.sandwich);

        udp = UDPconnection.getInstance();
        udp.setObserver(this);

        mango.setOnClickListener(this);
        danone.setOnClickListener(this);
        perro.setOnClickListener(this);
        sandwich.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {




        switch (v.getId()) {
            case R.id.danone:
                serializar("danone");
                break;
            case R.id.perrito:
                serializar("perrito");
                break;
            case R.id.sandwich:
                serializar("sandwich");
                break;
            case R.id.manguito:
                serializar("manguito");
                break;
        }
    }

    @Override
    public void OnMessage(String msg) {

        runOnUiThread(
                ()->{
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                }
        );
    }

    public void serializar (String delivery){
        Calendar c = Calendar.getInstance();
        Date fecha = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formateado = sdf.format(fecha);

        Delivery del = new Delivery(delivery, formateado);
        Gson gson = new Gson();
        String json = gson.toJson(del);
        udp.sendMessage(json);
    }
}