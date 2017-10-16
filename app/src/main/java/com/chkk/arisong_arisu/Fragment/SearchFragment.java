package com.chkk.arisong_arisu.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chkk.arisong_arisu.R;
import com.chkk.arisong_arisu.locationDTO;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements OnMapReadyCallback{



    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private GoogleMap mMap;
    private MapView mapView = null;
    private String Locationname;
    private String Locationsmallname;
    private double Latitude;
    private double Hardness;

    ArrayList<locationDTO> items;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);



        mapView = (MapView)layout.findViewById(R.id.map);
        mapView.getMapAsync(this);

        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public  void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("LotionDB").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    locationDTO locationDTO =  snapshot.getValue(locationDTO.class);
                    Log.d("asd","값받아옴");
                    Latitude = locationDTO.getWedo();
                    Log.d("asd","값받아옴2");
                    Hardness = locationDTO.getGyoundo();
                    Log.d("asd","값받아옴3");
                    Locationname = locationDTO.getLocationName();
                    Log.d("asd","값받아옴4");
                    Locationsmallname = locationDTO.getLcatesmallName();

                    LatLng Location = new LatLng(Latitude, Hardness);
                    Log.d("asd","경도 설정함"+Latitude+" / "+Hardness);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(Location);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                    markerOptions.title(Locationname);
                    markerOptions.snippet(Locationsmallname);
                    mMap.addMarker(markerOptions);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Location));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }
        );







    }

}


