package com.helloworld.selahattin.helloworld;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Models.Haberler;
import WebApiServis.ServiceGen;
import WebApiServis.TestServis;
import it.sephiroth.android.library.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Haber extends AppCompatActivity {

    TestServis test = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber);
        Intent intent = getIntent();
        final String HaberID = intent.getStringExtra(AnaEkran.HaberID);
        test =  ServiceGen.createService(TestServis.class);
        System.out.println("HaberID= " + HaberID);
        test.getHaber(new Integer(HaberID)).enqueue(new Callback<Haberler>() {
            @Override
            public void onResponse(Call<Haberler> call, final Response<Haberler> response) {
                if(response.isSuccessful()) {
                    setTitle(response.body().getBaslik());
                    TextView baslik =
                            (TextView) findViewById(R.id.textBaslik);
                    TextView bilgi =
                            (TextView) findViewById(R.id.textBilgi);
                    baslik.setText(response.body().getBaslik());
                    bilgi.setText(response.body().getAd() + " " + response.body().getSoyad() +" - "  + response.body().getOkunma() + " kez okundu.");
                    TextView ozet =
                            (TextView) findViewById(R.id.textOzet);
                    ozet.setText(response.body().getOzet());
                    ImageView resim = (ImageView) findViewById(R.id.imageHaber);
                    System.out.println("resim url=" + response.body().getResim());
                    resim.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Picasso.with(getBaseContext()).load(response.body().getResim()).into(resim);
                    TextView bilgi2 =
                            (TextView) findViewById(R.id.textBilgi2);
                    bilgi2.setText(response.body().getKaynak() + " - " + response.body().getEklenmeTarihi());
                    ozet.setText(response.body().getOzet());
                    Button btnDetay= (Button) findViewById(R.id.btnHaberiGoruntule);
                    btnDetay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getURL()));
                            startActivity(intent);
                        }
                    });
                     //response.body().getBaslik()

                } else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Haberler> call, Throwable throwable) {

            }
        });
    }
}
