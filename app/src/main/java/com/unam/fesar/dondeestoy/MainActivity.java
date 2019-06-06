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

        cancion = MediaPlayer.create(getBaseContext(), R.raw.cancion);
        cancion.start();

        bandera = false;
        txtMensaje = (TextView) findViewById(R.id.txtMensaje);

        try {
            GPS = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
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
        Location casa = new Location("Casa");
        casa.setLatitude(19.47435);
        casa.setLongitude(-99.04606166666666);
        double distancia = casa.distanceTo(location);

        if (distancia > 10.0 && bandera == false){
            txtMensaje.setText("Zorro no te la lleves");
            bandera = true;
            // aviso.sendTextMessage("5523833715",null,"Marco: Se escapa la huerca!!!... Digo zorro no te la lleves",null,null);
        }else{
            bandera = false;
            txtMensaje.setText("");
        }
    }

}
