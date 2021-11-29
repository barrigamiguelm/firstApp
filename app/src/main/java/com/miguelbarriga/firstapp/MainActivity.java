package com.miguelbarriga.firstapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
    ImageView foto1;


    //para el dialogo


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foto1 = (ImageView) findViewById(R.id.foto1);

        registerForContextMenu(foto1);


    }


//----------------alert box


    public void showAlertDialogButtonClicked(MainActivity main) {

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
        if (id == R.id.item2) {
            showAlertNevera(MainActivity.this);
        }
        if (id == R.id.item4) {
            showAlertDialogButtonClicked(MainActivity.this);
        }
        return super.onOptionsItemSelected(item);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Pulse aqui para ir al link");
        menu.add(0, 1, 0, "Pulsa aqui para descargar");
    }
        /*
            public boolean onContextItemSelected(MenuItem item) {
                if (item.getTitle() == "Pulsa aqui para descargar") {

            }*/
}






/*//guardar la foto

 public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Menu");
        menu.add(0, v.getId(), 0, "Pulse aqui para ir al link");
        menu.add(0, 1, 0, "Pulsa aqui para descargar");
    }


    public boolean onContextItemSelected(MenuItem item) {
        final WebView.HitTestResult webViewHitTestResult = miVisorWeb.getHitTestResult();

        if (item.getTitle() == "Pulsa aqui para descargar") {
            String DownloadImageURL = webViewHitTestResult.getExtra();

            if (URLUtil.isValidUrl(DownloadImageURL)) {

                DownloadManager.Request mRequest = new DownloadManager.Request(Uri.parse(DownloadImageURL));
                mRequest.allowScanningByMediaScanner();
                mRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                mDownloadManager.enqueue(mRequest);

                Toast.makeText(MainActivity.this, "Imagen descargada corectamente...", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Sorry.. Something Went Wrong...", Toast.LENGTH_LONG).show();
            }
            return false;
        }
        if (item.getTitle() == "Pulse aqui para ir al link") {
            String webUrl = miVisorWeb.getUrl();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)));
        }
        return false;
    }
*/


//webview y swipe

   /* WebView mycontext = (WebView) findViewById(R.id.vistaweb);
        registerForContextMenu(mycontext);*//*

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);

        //La vista dentro es un webview con permiso para zoom
     *//*   miVisorWeb = (WebView) findViewById(R.id.vistaweb);*//*
        //  miVisorWeb.getSettings().setJavaScriptEnabled(true);
      *//*  miVisorWeb.getSettings().setBuiltInZoomControls(true);
        miVisorWeb.loadUrl("https://thiscatdoesnotexist.com/");*/


//refresh

  /*  protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Toast toast0 = Toast.makeText(MainActivity.this, "Hi there! I don't exist :)", Toast.LENGTH_LONG);
            miVisorWeb.reload();
            swipeLayout.setRefreshing(false);
            swipeLayout.setProgressBackgroundColorSchemeResource(R.color.boton);
        }
    };


    //menu contextual pero con bajar foto




//modo normal sin descargas

/* @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        final WebView.HitTestResult result = miVisorWeb.getHitTestResult();
        MenuItem.OnMenuItemClickListener handler = new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                // handle on context menu click
                return true;
            }
        };
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Toast toast = Toast.makeText(this, "Item copied",
                        Toast.LENGTH_LONG);
//                toast.show();

                final ConstraintLayout mLayout =  findViewById(R.id.myMainConstraint);

                Snackbar snackbar = Snackbar
                        .make(mLayout, "fancy a Snack while you refresh?", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(mLayout, "Action is restored!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                snackbar.show();


                return true;

            case R.id.item2:
                Toast toast2 = Toast.makeText(this, "Downloading item...",
                        Toast.LENGTH_LONG);
                toast2.show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }
*/


