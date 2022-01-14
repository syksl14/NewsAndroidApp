package com.helloworld.selahattin.helloworld;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import Models.Haberler;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 04.10.2017.
 */

public class OzelAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Haberler> haberler;

    public OzelAdapter(Activity activity, List<Haberler> haberler) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        this.haberler = haberler;
    }
    @Override
    public int getCount() {
        return haberler.size();
    }

    @Override
    public Object getItem(int position) {
        return haberler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.habersutun, null);
        TextView baslik =
                (TextView) satirView.findViewById(R.id.textBaslik);
        TextView kaynak =
                (TextView) satirView.findViewById(R.id.textKaynak);
        TextView zaman =
                (TextView) satirView.findViewById(R.id.textZaman);

        //TextView bilgi = (TextView) satirView.findViewById(R.id.textBilgi);
        Haberler haber = haberler.get(position);

        baslik.setText(haber.getBaslik());
        kaynak.setText(haber.getKaynak());
        zaman.setText(haber.getEklenmeTarihi());
        if(haber.getResim().length() > 0 || haber.getResim() != null){
           ImageView resim = (ImageView) satirView.findViewById(R.id.imageHaber);
            resim.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(satirView.getContext()).load(haber.getResim()).into(resim);
           /* TableRow satirBilgi = (TableRow) satirView.findViewById(R.id.satirBilgi);
            Bitmap bitmap = ((BitmapDrawable)resim.getDrawable()).getBitmap();
            satirBilgi.setBackgroundColor(getDominantColor(bitmap));*/
        }
       // bilgi.setText(haber.getOkunma() + " kez okundu. - " + haber.getKaynak());
        return satirView;
    }

}
