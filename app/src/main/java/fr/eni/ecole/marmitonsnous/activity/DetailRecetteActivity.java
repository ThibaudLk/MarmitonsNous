package fr.eni.ecole.marmitonsnous.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.eni.ecole.marmitonsnous.R;
import fr.eni.ecole.marmitonsnous.beans.Recette;
import fr.eni.ecole.marmitonsnous.fragment.DetailRecetteFragment;

/**
 * Created by tlarriere2016 on 10/04/2018.
 */

public class DetailRecetteActivity extends AppCompatActivity implements DetailRecetteFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recette);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Intent intention = getIntent();
        DetailRecetteFragment detailsFragment = (DetailRecetteFragment) getFragmentManager().findFragmentById(R.id.second);
        if(detailsFragment.isInLayout())
        {
            Recette recette = (Recette) intention.getSerializableExtra("recette");
            detailsFragment.setRecetteInView(recette);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
