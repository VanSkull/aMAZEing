package fr.iutlens.vanhaecke.amazeing;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.List;

public class LabyrintheActivity extends Activity {
    // Identifiant de la boîte de dialogue de victoire
    public static final int VICTORY_DIALOG = 0;
    // Identifiant de la boîte de dialogue de défaite
    public static final int DEFEAT_DIALOG = 1;

    // Le moteur graphique du jeu
    private LabyrintheView mView = null;
    // Le moteur physique du jeu
    private LabyrintheEngine mEngine = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mView = new LabyrintheView(this);
        setContentView(mView);

        mEngine = new LabyrintheEngine(this);

        Boule b = new Boule();
        mView.setBoule(b);
        mEngine.setBoule(b);

        List<Bloc> mList = mEngine.buildLabyrinthe();
        mView.setBlocks(mList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mEngine.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEngine.stop();
    }

    @Override
    public Dialog onCreateDialog (int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(id){
            case VICTORY_DIALOG:
                builder.setCancelable(false).setMessage("Bravo, vous avez réussi !").setTitle("Vous vous êtes échappé !!!").setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // L'utilisateur peut recommencer s'il le veut
                                mEngine.reset();
                                mEngine.resume();
                            }
                        });
                break;

            /*case DEFEAT_DIALOG:
                builder.setCancelable(false).setMessage("Vous ne reverez plus jamais la lueur du jour.").setTitle("Vous êtes tombé dans le piège !!!").setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // L'utilisateur peut recommencer s'il le veut
                                mEngine.reset();
                                mEngine.resume();
                            }
                        });*/
        }
        return builder.create();
    }

    @Override
    public void onPrepareDialog (int id, Dialog box){
        // A chaque fois qu'une boîte de dialogue est lancée, on arrête le moteur physique
        mEngine.stop();
    }
}
