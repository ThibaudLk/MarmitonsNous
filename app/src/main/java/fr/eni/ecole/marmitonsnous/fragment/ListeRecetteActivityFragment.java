package fr.eni.ecole.marmitonsnous.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.marmitonsnous.DAO.RecetteDAO;
import fr.eni.ecole.marmitonsnous.R;
import fr.eni.ecole.marmitonsnous.activity.ListeRecetteActivity;
import fr.eni.ecole.marmitonsnous.adapter.RecetteAdapter;
import fr.eni.ecole.marmitonsnous.beans.Recette;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListeRecetteActivityFragment extends Fragment {

    // Liste des recettes à afficher
    static List<Recette> listRecettes;

    // Nom d'un argument lié au fragment.
    private static final String ARG_COLUMN_COUNT = "column-count";

    // Valeur que l'on va donner à l'argument lié au fragment.
    private int mColumnCount = 1;

    // Interface pour dialoguer avec l'activité qui utilisera le fragment.
    private OnListFragmentInteractionListener mListener;

    public ListeRecetteActivityFragment() {
    }

    @SuppressWarnings("unused")
    public static ListeRecetteActivityFragment newInstance(int columnCount) {
        ListeRecetteActivityFragment fragment = new ListeRecetteActivityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_liste_recette, container, false);
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView listViewRecette = (RecyclerView) view;
                if (mColumnCount <= 1) {
                    listViewRecette.setLayoutManager(new LinearLayoutManager(context));
                } else {
                    listViewRecette.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                }

                if (listRecettes == null) {
                    listRecettes = new ArrayList<Recette>();
                    listRecettes.add(new Recette(1, "Le potimaron à la crème", 5f, 30, 2, 5f, R.drawable.ohzgour2));
                    listRecettes.add(new Recette(2, "Grosse pizz", 4f, 5, 2, 3f, R.drawable.ohzgour2 ));
                    listRecettes.add(new Recette(3, "Chili con carne", 2f, 8, 2, 1f, R.drawable.ohzgour2 ));
                    listRecettes.add(new Recette(4, "La popotte de Marine", 3f, 5, 20, 2f, R.drawable.ohzgour2 ));
                }

                // on utilise un adapter pour afficher les items de la liste
                RecetteAdapter adapter = new RecetteAdapter(listRecettes, mListener);
                listViewRecette.setAdapter(adapter);
            }
            return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Recette recette);
    }

    public static List<Recette> chargerListeRecette(Context context) {
        listRecettes = new ArrayList<Recette>();
        RecetteDAO recetteDAO = new RecetteDAO(context);
        listRecettes = recetteDAO.selectAll();
        return listRecettes;
    }
}
