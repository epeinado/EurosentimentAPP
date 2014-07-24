package com.eurosentiment.EurosentimentAPP;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * User: epeinado
 * Date: 23/07/14
 * Time: 13:32
 */
public class AdapterTweets extends BaseAdapter {

    protected Activity activity;
        protected ArrayList<Tweet> items;

        public AdapterTweets(Activity activity, ArrayList<Tweet> items) {
            this.activity = activity;
            this.items = items;
          }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int arg0) {
            return items.get(arg0);
        }

        @Override
        public long getItemId(int position) {
            return items.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Generamos una convertView por motivos de eficiencia
            View v = convertView;

            //Asociamos el layout de la lista que hemos creado
            if(convertView == null){
                LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inf.inflate(R.layout.tweet, null);
            }

            // Creamos un objeto directivo
            Tweet dir = items.get(position);
            //Rellenamos la fotografía
//            ImageView foto = (ImageView) v.findViewById(R.id.foto);
//            foto.setImageDrawable(dir.getFoto());
            //Rellenamos el user
            TextView nombre = (TextView) v.findViewById(R.id.user);
            nombre.setText(dir.getUser());
            //Rellenamos el tweet
            TextView cargo = (TextView) v.findViewById(R.id.tweetText);
            cargo.setText(dir.getTweetText());

            // Retornamos la vista
            return v;
        }

}
