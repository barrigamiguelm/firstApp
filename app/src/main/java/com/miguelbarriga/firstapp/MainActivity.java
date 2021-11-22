package com.miguelbarriga.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    private SwipeRefreshLayout swipeLayout;
    private WebView miVisorWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView mycontext = (WebView) findViewById(R.id.vistaweb);
        registerForContextMenu(mycontext);

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);

        //La vista dentro es un webview con permiso para zoom
        miVisorWeb = (WebView) findViewById(R.id.vistaweb);
        //  miVisorWeb.getSettings().setJavaScriptEnabled(true);
        miVisorWeb.getSettings().setBuiltInZoomControls(true);
        miVisorWeb.loadUrl("https://thiscatdoesnotexist.com/");

    }


//guardar la foto


    //refresh

    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Toast toast0 = Toast.makeText(MainActivity.this, "Hi there! I don't exist :)", Toast.LENGTH_LONG);
            miVisorWeb.reload();
            swipeLayout.setRefreshing(false);
            swipeLayout.setProgressBackgroundColorSchemeResource(R.color.boton);
        }
    };


    //menu contextual pero con bajar foto


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


    //appbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.item1) {
            Toast toast = Toast.makeText(this, "Infecting", Toast.LENGTH_LONG);
            toast.show();

        }
        if (id == R.id.item2) {
            Toast toast = Toast.makeText(this, "Fixing", Toast.LENGTH_LONG);
            toast.show();
        }
        return super.onOptionsItemSelected(item);
    }


}

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

