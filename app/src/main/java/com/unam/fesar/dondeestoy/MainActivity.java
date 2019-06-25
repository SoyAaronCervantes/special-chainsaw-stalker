package com.unam.fesar.dondeestoy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager GPS;
    MediaPlayer cancion;
    Boolean bandera;
    TextView txtMensaje;
    SmsManager aviso = SmsManager.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bandera = false;
        txtMensaje = (TextView) findViewById(R.id.txtMensaje);

        try {
            GPS = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                return;
            }
            GPS.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3, this);

        }catch (Exception e){

        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Location escuela = new Location("Escuela");
        escuela.setLatitude(19.474913);
        escuela.setLongitude(-99.046150);
        double distancia = escuela.distanceTo(location);

        if (distancia > 10.0 && bandera == false){
            txtMensaje.setText("Zorro no te la lleves");
            bandera = true;
            aviso.sendTextMessage("5536492686",null,"Se escapa la huerca!!!... Digo zorro no te la lleves",null,null);
        }else{
            bandera = false;
            txtMensaje.setText("");
        }
    }

}
