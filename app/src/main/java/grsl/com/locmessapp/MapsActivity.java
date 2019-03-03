package grsl.com.locmessapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    NavigationView leftMenu;
    ImageButton actionBarButton;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        drawer = findViewById(R.id.drawer_layout);
        leftMenu = findViewById(R.id.leftmenu);
        actionBarButton= findViewById(R.id.drawer_button);
        final EditText editText = findViewById(R.id.leave_message_edittext);

        leftMenu.setNavigationItemSelectedListener(this);

        View drawerHeader = leftMenu.getHeaderView(0);
        LinearLayout followers = drawerHeader.findViewById(R.id.nav_header_followers_layout);
        LinearLayout following = drawerHeader.findViewById(R.id.nav_header_following_layout);
        LinearLayout messages = drawerHeader.findViewById(R.id.nav_header_messages_layout);
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, FollowersActivity.class);
                startActivity(intent);
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, FollowingActivity.class);
                startActivity(intent);
            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        });

        //prevent keyboard open
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //edit text onClick
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, WriteMessageActivity.class);
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(
                                MapsActivity.this, editText,
                                Objects.requireNonNull(ViewCompat.getTransitionName(editText))
                        );
                startActivity(intent, optionsCompat.toBundle());
            }

        });

        //drawer button onClick
        actionBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        //Map Fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1020);
        } else {
            googleMap.setMyLocationEnabled(true);
            LatLng myLocation = new LatLng(45.640934, 25.587899);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,18), 5000, null);
        }

        try{
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style));
        }catch (Resources.NotFoundException e){
            Log.d("Map Style", "Can't find style. Error: "+ e);
        }

        googleMap.setTrafficEnabled(false);
        googleMap.setBuildingsEnabled(true);
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_search: {
                Intent intent = new Intent(MapsActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            }
        }
        //close navigation drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //onBack pressed. if drawer is open close it first, if not exit application
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(Gravity.LEFT);
        }else{
            finish();
        }
    }
}
