package fr.eni.ecole.marmitonsnous.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.eni.ecole.marmitonsnous.R;
import fr.eni.ecole.marmitonsnous.beans.Recette;
import fr.eni.ecole.marmitonsnous.fragment.ListeRecetteActivityFragment;

/**
 * Created by tlarriere2016 on 09/04/2018.
 */

public class RecetteAdapter extends RecyclerView.Adapter<RecetteAdapter.RecetteViewHolder> {

private List<Recette> recettes;
private ListeRecetteActivityFragment.OnListFragmentInteractionListener onItemClickListener;

    public RecetteAdapter(List<Recette> arrayRecette,
                          ListeRecetteActivityFragment.OnListFragmentInteractionListener listener) {
        recettes = arrayRecette;
        onItemClickListener = listener;
    }

    @Override
    public RecetteViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.adapter_line_recette;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        RecetteViewHolder viewHolder = new RecetteViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecetteViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return recettes.size();
    }

    // View holder pour transporter les infos
    public class RecetteViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitre;
        TextView txtNote;

        View itemView;
        Recette recette;


        public RecetteViewHolder(View itemView) {
            super(itemView);
            txtTitre = (TextView) itemView.findViewById(R.id.txtTitre);
            txtNote = (TextView) itemView.findViewById(R.id.txtNote);
            this.itemView = itemView;
        }

        void bind(int index) {
            recette = recettes.get(index);
            txtTitre.setText(recette.getTitre());
            txtNote.setText(Float.toString(recette.getNote()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListener) {
                        onItemClickListener.onListFragmentInteraction(recette);
                    }
                }
            });
        }
    }
    public void add(Recette recette){
        this.recettes.add(recette);
        notifyDataSetChanged();
    }
}
