package com.askme.askmeassignment.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.askme.askmeassignment.R;
import com.askme.askmeassignment.adapter.HomeAdapter;
import com.askme.askmeassignment.entity.ProductEntity;
import com.askme.askmeassignment.utils.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<ProductEntity> productEntities = new ArrayList<>();
    private RecyclerView productlist;
    private HomeAdapter homeAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        productlist = (RecyclerView)findViewById(R.id.mainlist);
        mLayoutManager = new LinearLayoutManager(this);
        productlist.setLayoutManager(mLayoutManager);
        homeAdapter = new HomeAdapter(this,getSupportFragmentManager(),productEntities);
        productlist.setAdapter(homeAdapter);
        new ParserJsonTask(this).execute();
    }

    public  String loadJSONFromAsset() {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open(
                    "f_one.json")));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private static class ParserJsonTask extends AsyncTask<Void, Integer, List<ProductEntity>> {
        private final WeakReference<MainActivity> mainActivityRef;

        public ParserJsonTask(MainActivity mainActivity) {
            this.mainActivityRef = new WeakReference<MainActivity>(mainActivity);
        }
        @Override
        protected List<ProductEntity> doInBackground(Void... params) {

            try{
                if(mainActivityRef.get() != null) {
                    return JsonParser.getInstance().parseJson(mainActivityRef.get().loadJSONFromAsset());
                }


            }catch(Exception e){
                Log.d("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ProductEntity> result) {
            if(result != null) {
                if(mainActivityRef.get() != null) {
                    mainActivityRef.get().productEntities = result;
                    mainActivityRef.get().homeAdapter.setProduct(result);
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
