package fr.eni.ecole.marmitonsnous.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.marmitonsnous.DAO.EtapeDAO;
import fr.eni.ecole.marmitonsnous.DAO.RecetteDAO;
import fr.eni.ecole.marmitonsnous.R;
import fr.eni.ecole.marmitonsnous.adapter.ListeEtapeAdapter;
import fr.eni.ecole.marmitonsnous.beans.Etape;
import fr.eni.ecole.marmitonsnous.beans.Ingredient;
import fr.eni.ecole.marmitonsnous.beans.Recette;
import fr.eni.ecole.marmitonsnous.component.NonScrollListView;

public class CreateRecetteActivity extends AppCompatActivity {

    Recette recette;
    Etape etape;
    Ingredient ingredient;
    ViewHolder holder;
    int nbEtape;
    List<Etape> listEtape;
    NonScrollListView listViewEtape;
    ListeEtapeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recette);
        nbEtape = 0;
        listEtape = new ArrayList<Etape>();
        holder = new ViewHolder();

        holder.titre = (TextView) findViewById(R.id.editTitre);
        holder.note = (RatingBar) findViewById(R.id.editNote);
        holder.temps = (TextView) findViewById(R.id.editTemps);
        holder.nombrePersonne = (TextView) findViewById(R.id.editPersonnes);
        holder.difficulte = (TextView) findViewById(R.id.editDifficulte);

        listViewEtape = (NonScrollListView) findViewById(R.id.listeEtape);

        adapter = new ListeEtapeAdapter(this, R.layout.add_etape_line_adapter ,listEtape);
        listViewEtape.setAdapter(adapter);

        Button photo = (Button) findViewById(R.id.uploadImage);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        ImageButton addEtape = (ImageButton) findViewById(R.id.addEtape);
        addEtape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbEtape++;
                EditText ed = (EditText) findViewById(R.id.addDescriptionEtape);
                Etape etape = new Etape(ed.getText().toString(), nbEtape);
                listEtape.add(etape);
                adapter.notifyDataSetChanged();
                ed.setText("");
            }
        });

        recette = (Recette) getIntent().getSerializableExtra("recette");

        if (recette != null) setRecetteInView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_create_recette, menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                return true;
            case R.id.menu_valider_changement:
                boolean isNew = false;
                if (recette == null) {
                    recette = new Recette();
                    isNew = true;
                }
                recette.setTitre(holder.titre.getText().toString());
                recette.setNote(Float.valueOf(holder.note.getRating()));
                recette.setTemps(Integer.parseInt(holder.temps.getText().toString()));
                recette.setNbrePersonne(Integer.parseInt(holder.nombrePersonne.getText().toString()));
                recette.setDifficulte(Float.valueOf(holder.difficulte.getText().toString()));
                //recette.setPhoto(Integer.parseInt(holder.photo.toString()));
                RecetteDAO recetteDAO = new RecetteDAO(CreateRecetteActivity.this);
                if (isNew) {
                    int idRecette = (int) recetteDAO.insert(recette);
                    EtapeDAO etapeDAO = new EtapeDAO(CreateRecetteActivity.this);
                    for ( Etape etape : listEtape) {
                        etape.setIdRecette(idRecette);
                        etapeDAO.insert(etape);
                    }
                } else recetteDAO.update(recette);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("recette", recette);
                setResult(RESULT_OK,returnIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    static class ViewHolder{
        TextView titre;
        RatingBar note;
        TextView temps;
        TextView nombrePersonne;
        TextView difficulte;
        TextView numero;
        TextView description;
        TextView ingredients;
        Button photo;
    }

    private void setRecetteInView() {

        holder.titre.setText(recette.getTitre());
        holder.note.setRating(recette.getNote());
        holder.temps.setText(recette.getTemps());
        holder.nombrePersonne.setText(recette.getNbrePersonne());
        holder.difficulte.setText(Float.toString(recette.getDifficulte()));

    }

}
