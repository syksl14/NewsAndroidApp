package com.helloworld.selahattin.helloworld;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import Models.Haberler;
import WebApiServis.ServiceGen;
import WebApiServis.TestServis;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnaEkran extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public ListView listem = null;
    TestServis test = null;
    SwipeRefreshLayout mSwipeRefreshLayout;
    public ArrayList<Haberler> haberlerList;
   // private DrawerLayout mDrawerLayout;
  //  private ListView mDrawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       /*   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
      mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, menuler));*/
       listem = (ListView) findViewById(R.id.haberListem);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("refreshing data...");
                haberleriGetir();
            }
        });
        haberleriGetir();
    }

    void haberleriGetir(){
        haberlerList = new ArrayList<>();
        test =  ServiceGen.createService(TestServis.class);
        test.getHaberler().enqueue(new Callback<List<Haberler>>() {
            @Override
            public void onResponse(Call<List<Haberler>> call, Response<List<Haberler>> response) {
                if(response.isSuccessful()) {
                    haberlerList= (ArrayList<Haberler>) response.body();
                    OzelAdapter adaptorumuz=new OzelAdapter(AnaEkran.this, haberlerList);
                    listem.setAdapter(adaptorumuz);
                } else {
                    System.out.println(response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<List<Haberler>> call, Throwable throwable) {

            }
        });


        mSwipeRefreshLayout.setRefreshing(false);
    }
    public static final String HaberID = "0";
    public void haberOku(View v){
        Intent intent = new Intent(AnaEkran.this, Haber.class);
        String deger = Integer.toString(haberlerList.get(listem.getPositionForView(v)).getHaberID());
        intent.putExtra(HaberID, deger);
        startActivity(intent);
    /*    test.getHaber(haberlerList.get(listem.getPositionForView(v)).getHaberID()).enqueue(new Callback<Haberler>() {

            @Override
            public void onResponse(Call<Haberler> call, final Response<Haberler> response) {
                if(response.isSuccessful()) {
                    AlertDialog.Builder diyalogOlusturucu =
                            new AlertDialog.Builder(AnaEkran.this);
                    diyalogOlusturucu.setTitle(response.body().getBaslik())
                            .setMessage(Html.fromHtml("<p><b>"+response.body().getAd()
                                    + " " + response.body().getSoyad()+"</b><br>"
                                    +"<span style='color:#a8a8a8;'>"+response.body().getEklenmeTarihi()+"</span></p>"+response.body().getOzet()))
                            .setCancelable(false)
                            .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setNeutralButton("Haberi Görüntüle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getURL()));
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });
                    diyalogOlusturucu.create().show();
                } else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Haberler> call, Throwable throwable) {

            }
        });*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ana_ekran, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
