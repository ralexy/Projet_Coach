package com.example.alexy.coach.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alexy.coach.R;
import com.example.alexy.coach.controleur.Controle;
import com.example.alexy.coach.modele.Profil;
import com.example.alexy.coach.outils.MesOutils;

import java.util.ArrayList;

/**
 * Created by emds on 28/02/2018.
 */

public class HistoListAdapter extends BaseAdapter {

    // propriétés
    private ArrayList<Profil> lesProfils;
    private LayoutInflater inflater;
    private Context context;

    /**
     * Constructeur
     * @param context
     * @param lesProfils
     */
    public HistoListAdapter(Context context, ArrayList<Profil> lesProfils){
        this.lesProfils = lesProfils;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * Nombre de lignes de lal iste
     * @return nombre de lignes
     */
    @Override
    public int getCount() {
        return lesProfils.size();
    }

    /**
     * Item correspondant à un id
     * @param i id
     * @return item correspondant à l'id
     */
    @Override
    public Object getItem(int i) {
        return lesProfils.get(i);
    }

    /**
     * id correspondant à un id
     * @param i id
     * @return un id
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Construction d'une vue complète de la ligne correspondant à un id
     * @param i id
     * @param view vue
     * @param viewGroup
     * @return vue construite
     */
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
                // code a exécuter
                int position = (int) v.getTag();
                Controle controle = Controle.getInstance(null);
                controle.delProfil(lesProfils.get(position));
                // rafraichi la liste visuelle
                notifyDataSetChanged() ;
            }
        }) ;
        // mémorisation de l'indice de ligne en étiquette de txtListDate pour ensuite récupérer cet indice dans l'événement
        holder.txtListDate.setTag(i) ;
        // gestion de l'événement clic sur la zone de texte
        holder.txtListDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // code a exécuter
                int position = (int) v.getTag();
                ((HistoActivity)context).afficheProfil(lesProfils.get(position));
            }
        }) ;
        // mémorisation de l'indice de ligne en étiquette de txtListIMG pour ensuite récupérer cet indice dans l'événement
        holder.txtListIMG.setTag(i) ;
        // gestion de l'événement clic sur la zone de texte
        holder.txtListDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // code a exécuter
                int position = (int) v.getTag();
                ((HistoActivity)context).afficheProfil(lesProfils.get(position));
            }
        }) ;
        // retour de la vue construite
        return view ;
    }

    /**
     * Classe des 3 objets graphiques de la ligne
     */
    private class ViewHolder{
        ImageButton imgListSuppr;
        TextView txtListDate;
        TextView txtListIMG;
    }

}
