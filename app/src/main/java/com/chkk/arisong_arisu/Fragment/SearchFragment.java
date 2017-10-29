package com.chkk.arisong_arisu.Fragment;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chkk.arisong_arisu.GPSInfo;
import com.chkk.arisong_arisu.MainActivity;
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
public class SearchFragment extends Fragment implements OnMapReadyCallback {


    private LinearLayout linearlayout,OptionView;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private GoogleMap mMap;
    private MapView mapView = null;

    private String Locationname;
    private String Locationsmallname;
   // private double Latitude;
    private double Hardness;
    MarkerOptions myLocation,nowLocation;
    private boolean isFirst = true;
    private GPSInfo gpsInfo;
    private double Latitude = 0,Longitude = 0;
    private LatLng Position;

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
        linearlayout = (LinearLayout) layout.findViewById(R.id.getmore);
        mapView = (MapView) layout.findViewById(R.id.googlemap);
        mapView.getMapAsync(this);

        gpsInfo = new GPSInfo(getActivity().getApplicationContext());
        gpsInfo.getLocation();
        getLongitude();
        getLatitude();
        setPosition();

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearlayout.setVisibility(View.GONE);
            }
        });

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
        gpsInfo.stopUsingGPS();
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
        gpsInfo.stopUsingGPS();
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
            MainActivity mia  = new MainActivity();
            mia.ProgaressDialogoff();
        }
    }

    public void  getLatitude(){
        this.Latitude  = gpsInfo.getLatitude();
    }
    public void  getLongitude(){
        this.Longitude = gpsInfo.getLongitude();
    }
    public void setPosition() {
        if (this.Position == null) {
            Position = new LatLng(Latitude, Longitude);
            //  Log.i("tlqkf","종하1~~");
            // Log.i("SDS",isFirst+"");
            myLocation = new MarkerOptions();
            myLocation.position(Position);
            return ;
        }
        if(isFirst){
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Position, 15));
            isFirst = false;
        }
        Position = new LatLng(Latitude, Longitude);
        Log.i(Position+"","sdfsdsdsdsdddsdsdsddddddddddd");
        //    Log.i("SDSD", "DSDS12");
        myLocation.position(Position);
        nowLocation = myLocation;
        this.mMap.addMarker(nowLocation.title("내 위치")).showInfoWindow();
        nowLocation.visible(false);
    }


    @Override
    public  void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        getLongitude();
        getLatitude();
        setPosition();
        mapView.getMapAsync(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("LocationDB").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Location,15));
                 //   mMap
                    mMap.setOnInfoWindowClickListener(infoWindowClickListener);
                    mMap.setOnMarkerClickListener(markerClickListener);
                }

            }
            @Override public void onCancelled(DatabaseError databaseError) {
                Log.i("SDSDSDSSDSDSDSDS","SDSDSDSDSDSDSDSDSDS");
            }
        }

        );
    }


    //정보창 클릭 리스너
    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            String markerId = marker.getId();
            Toast.makeText(getContext(), "정보창 클릭 Marker ID : "+markerId, Toast.LENGTH_SHORT).show();
        }
    };

    //마커 클릭 리스너
    GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            String markerId = String.valueOf(Integer.parseInt(marker.getId())+1);
            //선택한 타겟위치
            LatLng location = marker.getPosition();
            Toast.makeText(getContext(), "마커 클릭 Marker ID : "+markerId+"("+location.latitude+" "+location.longitude+")", Toast.LENGTH_SHORT).show();
            linearlayout.setVisibility(View.VISIBLE);

            return false;
        }
    };

}


