package fr.eni.ecole.marmitonsnous.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.eni.ecole.marmitonsnous.R;
import fr.eni.ecole.marmitonsnous.beans.Etape;

/**
 * Created by tlarriere2016 on 11/04/2018.
 */

public class ListeEtapeAdapter extends ArrayAdapter<Etape> {
    private List<Etape> etapes;
    private Context context;
    private int res;

    public ListeEtapeAdapter(@NonNull Context context, int resource, @NonNull List<Etape> objects) {
        super(context, resource, objects);
        this.context = context;
        etapes = objects;
        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        TextView txtNumero;
        TextView txtDesciption;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            view = inflater.inflate(res, parent, false);
        }
        else{
            view = convertView;
        }

        Etape etape = getItem(position);


        txtNumero = (TextView) view.findViewById(R.id.numeroEtape);
        txtDesciption = (TextView) view.findViewById(R.id.descriptionEtape);

        txtNumero.setText("Etape " + String.valueOf(etape.getNumero()));
        txtDesciption.setText(etape.getNom());

        return view;
    }
}
