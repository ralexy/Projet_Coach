package com.example.alexy.coach.vue;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alexy.coach.R;
import com.example.alexy.coach.modele.Profil;
import com.example.alexy.coach.outils.MesOutils;

import java.util.ArrayList;

public class HistoListAdapter extends BaseAdapter {

    ArrayList <Profil> lesProfils;
    LayoutInflater inflater;
    Context context;

    public HistoListAdapter(Context context, ArrayList <Profil> profils) {
        this.lesProfils = profils;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return lesProfils.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // holder est un objet de la petite classe
        ViewHolder holder ;
        // si la ligne active reçue en paramètre n'existe pas encore
        if (view == null) {
            holder = new ViewHolder() ;
            // la ligne est construite à partir de la structure de la ligne (récupérée dans layout_list_histo)
            view = inflater.inflate(R.layout.layout_liste_histo, null) ;
            // chaque propriété de holder (correspondant aux objets graphiques) est liée à un des objets graphiques
            holder.txtListDate = (TextView)view.findViewById(R.id.txtListDate) ;
            holder.txtListIMG = (TextView)view.findViewById(R.id.txtListIMG) ;
            holder.imgListSuppr = (ImageButton)view.findViewById(R.id.imgListSuppr) ;
            // affecte le holder comme tag (étiquette) de la ligne
            view.setTag(holder) ;
        }else{
            // si la ligne existe déjà, holder récupère le holder de la ligne (précédemment mémorisé)
            holder = (ViewHolder)view.getTag();
        }
        // holder est maintenant lié à la ligne graphique
        // valorisation des propriétés de holder avec le profil de lesProfils (à un indice précis : position)
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(i).getDateMesure())) ;
        holder.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(i).getImg())) ;
        // mémorisation de l'indice de ligne en étiquette de imgListSuppr pour ensuite récupérer cet indice dans l'événement
        holder.imgListSuppr.setTag(i) ;
        // gestion de l'événement clic sur le bouton de suppression
        holder.imgListSuppr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // code a exécuter sur le clic d'un bouton suppr
            }
        }) ;
        // retour de la vue construite
        return view ;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private class ViewHolder {
        TextView txtListDate;
        TextView txtListIMG;
        ImageButton imgListSuppr;
    }
}