package fr.eni.ecole.marmitonsnous.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import fr.eni.ecole.marmitonsnous.R;
import fr.eni.ecole.marmitonsnous.adapter.ListeEtapeAdapter;
import fr.eni.ecole.marmitonsnous.beans.Recette;
import fr.eni.ecole.marmitonsnous.component.NonScrollListView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailRecetteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailRecetteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailRecetteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecetteViewHolder holder;
    ListeEtapeAdapter adapter;

    public DetailRecetteFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static DetailRecetteFragment newInstance(String param1, String param2) {
        DetailRecetteFragment fragment = new DetailRecetteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_recette, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class RecetteViewHolder {
        TextView viewTitre;
        RatingBar viewNote;
        TextView viewTemps;
        TextView nbrePersonne;
        RatingBar difficulte;
        NonScrollListView listViewEtape;
        //private List<Etape> etapes;
        //private List<Ingredient> ingredients;
        //private int photo;

        public RecetteViewHolder() {
            viewTitre = (TextView) getView().findViewById(R.id.txtTitre);
            viewNote = (RatingBar) getView().findViewById(R.id.ratingNote);
            viewTemps = (TextView) getView().findViewById(R.id.txtTemps);
            nbrePersonne = (TextView) getView().findViewById(R.id.nbPersonnes);
            difficulte = (RatingBar) getView().findViewById(R.id.ratingDifficulte);
            listViewEtape = (NonScrollListView) getView().findViewById(R.id.listeEtape);

        }
    }

    public void setRecetteInView(Recette recette) {

        holder = new RecetteViewHolder();

        adapter = new ListeEtapeAdapter((Context) mListener, R.layout.add_etape_line_adapter ,recette.getEtapes());
        holder.listViewEtape.setAdapter(adapter);

        holder.viewTitre.setText(recette.getTitre());
        holder.viewNote.setRating(recette.getNote());
        holder.viewTemps.setText(recette.getTemps() + " minutes");
        holder.nbrePersonne.setText(recette.getNbrePersonne() + " personnes");
        holder.difficulte.setRating(recette.getDifficulte());
    }

}
