package com.ritika.pokemonand


import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    var ACCESSLOCATION=143
    fun checkPermission()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)


                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), ACCESSLOCATION)

        }
        getLocation()
    }
           fun getLocation()

           {
                  Toast.makeText(this,"User Location access is on",Toast.LENGTH_LONG).show()

               var mylocation=MyLocationListener()

               var LocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager

               LocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,mylocation)
               var thred=myThread()
               thred.start()
           }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?) {
        when(requestCode)
        {
            ACCESSLOCATION->
            {
                if(grantResults!![0]==PackageManager.PERMISSION_GRANTED)

                    getLocation()
                else
                {
                    Toast.makeText(this,"Access not granted to your location",Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }







    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera



        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10f))
    }
}

var mlocation:Location?=null

//to get the user location

  class MyLocationListener: LocationListener
{

    constructor()
    {
        mlocation=Location("Start")
        mlocation!!.longitude=0.0
        mlocation!!.latitude=0.0

    }

 override fun onLocationChanged(location: Location?) {
     mlocation=location
 }

 override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
     //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
 }

 override fun onProviderEnabled(provider: String?) {
    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
 }

 override fun onProviderDisabled(provider: String?) {
    // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
 }

}

class myThread: Thread
{
 constructor():super()
 {

 }

    override fun run() {
        while (true)
        {

            try {
                runOnUiThread{
                    mMap!!.clear()
                val sydney = LatLng(mlocation!!.longitude, mlocation!!.latitude)
                mMap!!.addMarker(MarkerOptions()
                        .position(sydney)
                        .title("Me")
                        .snippet("Here's my location")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.p1)
                }
                Thread.sleep(1000)
            }catch (ex:Exception){}

        }
    }
}
