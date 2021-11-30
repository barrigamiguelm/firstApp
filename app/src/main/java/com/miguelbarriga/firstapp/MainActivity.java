package com.miguelbarriga.firstapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {


    private SwipeRefreshLayout swipeLayout;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    ImageView foto1, foto2, foto3, foto4, foto5, foto6, foto7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foto1 = (ImageView) findViewById(R.id.foto1);
        foto2 = (ImageView) findViewById(R.id.foto2);
        foto3 = (ImageView) findViewById(R.id.foto3);
        foto4 = (ImageView) findViewById(R.id.foto4);
        foto5 = (ImageView) findViewById(R.id.foto5);
        foto6 = (ImageView) findViewById(R.id.foto6);
        foto7 = (ImageView) findViewById(R.id.foto7);

        /*linea = (LinearLayout) findViewById(R.id.linear);*/

        registerForContextMenu(foto1);
        registerForContextMenu(foto2);
        registerForContextMenu(foto3);
        registerForContextMenu(foto4);
        registerForContextMenu(foto5);
        registerForContextMenu(foto6);
        registerForContextMenu(foto7);


    }


//----------------alert box


    public void showAlertDesconectar(MainActivity main) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);


        builder.setTitle("Desconectar");
        builder.setMessage("¿Seguro que deseas desconectarte?");
        ;

        // builder.setView(getLayoutInflater().inflate(R.layout.alert, null));


        builder.setPositiveButton("Si", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("No", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //-----ALERT DIALOG DE LA NEVERA---------

    public void showAlertNevera(MainActivity main) {

        // Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añade ingredientes a tu nevera");

        // Los checkboxes
        String[] comida = {"Cebolla", "Pimientos", "Puerro", "Brocoli", "Aguacate"};
        boolean[] checkedItems = {false, false, false, false, false};
        builder.setMultiChoiceItems(comida, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            }
        });

        //botones de añadir y cancelar
        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                Toast.makeText(MainActivity.this, "Añadido a tu nevera correctamente", Toast.LENGTH_LONG).show();

            }
        });
        builder.setNegativeButton("Cancelar", null);

        // Empezar el alert
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showAlertAboutus(MainActivity main) {


        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

        builder.setView(getLayoutInflater().inflate(R.layout.alert, null));

        builder.setPositiveButton("contactar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] recipients = {"aBocados@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Enviar mail"));
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    //---------------appbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=40.40478410353114, -3.7062342886300623"));
            startActivity(intent);


        }
        if (id == R.id.item3) {
            showAlertAboutus(MainActivity.this);
        }
        if (id == R.id.item2) {
            showAlertNevera(MainActivity.this);
        }
        if (id == R.id.item4) {
            showAlertDesconectar(MainActivity.this);
        }
        return super.onOptionsItemSelected(item);
    }


    //-----------contextmenu imagenes


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Pulse aqui para ir al link");
        menu.add(0, 1, 0, "Pulsa aqui para descargar");
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Pulsa aqui para descargar") {
            Toast.makeText(MainActivity.this, "Funcionalidad aun no añadida", Toast.LENGTH_LONG).show();
        }
        if (item.getTitle() == "Pulse aqui para ir al link") {
            Toast.makeText(MainActivity.this, "Funcionalidad aun no añadida", Toast.LENGTH_LONG).show();
        }
        return false;
    }

}




