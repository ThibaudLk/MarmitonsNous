package fr.eni.ecole.marmitonsnous.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;

import fr.eni.ecole.marmitonsnous.R;
import fr.eni.ecole.marmitonsnous.beans.Recette;
import fr.eni.ecole.marmitonsnous.fragment.DetailRecetteFragment;
import fr.eni.ecole.marmitonsnous.fragment.ListeRecetteActivityFragment;

public class ListeRecetteActivity extends AppCompatActivity
        implements ListeRecetteActivityFragment.OnListFragmentInteractionListener, DetailRecetteFragment.OnFragmentInteractionListener {

    static final int ADD_RECETTE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste_recette, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_add) {
            Intent intentAdd = new Intent(this, CreateRecetteActivity.class);
            startActivityForResult(intentAdd,1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onListFragmentInteraction(Recette recette) {
        DetailRecetteFragment detailsFragment = (DetailRecetteFragment)getFragmentManager().findFragmentById(R.id.second);
        if(detailsFragment != null && detailsFragment.isInLayout())
        {
            detailsFragment.setRecetteInView(recette);
        }else{
            Intent intent = new Intent(ListeRecetteActivity.this,DetailRecetteActivity.class);
            intent.putExtra("recette", (Serializable) recette);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode ==1) {
             Recette recette =(Recette) data.getSerializableExtra("recette");
            ListeRecetteActivityFragment listeRecetteActivityFragment = (ListeRecetteActivityFragment)getFragmentManager().findFragmentById(R.id.first);
            listeRecetteActivityFragment.addRecette(recette);




        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
