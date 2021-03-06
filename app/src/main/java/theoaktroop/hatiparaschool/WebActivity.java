package theoaktroop.hatiparaschool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import android.support.design.widget.FloatingActionButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class WebActivity extends Activity {

    private WebView webView;
    private TextView txtUnititle;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);
    //private FloatingActionButton backButton,forwardButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);





//
//        FloatingActionButton backButton = (FloatingActionButton) findViewById(R.id.backButton);
//        FloatingActionButton forwardButton = (FloatingActionButton) findViewById(R.id.forwardButton);


        webView = (WebView) findViewById(R.id.webView);







        String  url;

            url = "http://hatiparahighschool.edu.bd/";



            webView.setWebViewClient(new MyBrowser());
            webView.getSettings().setLoadsImagesAutomatically(true);
//            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);

        AdView mAdView = (AdView) this.findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }



        //for progressbar
        public class myWebClient extends WebViewClient
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub

                view.loadUrl(url);
                return true;

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);

                progressBar.setVisibility(View.GONE);
            }
        }



    @Override
    public boolean onKeyDown (int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {

            webView.goBack(); // go back in only the web view
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }
    }

    public void menuAction(View view){
        view.startAnimation(buttonClick);

        if(view.getId()==R.id.homeButton){
            finish();
        }
        else if(view.getId()==R.id.feedbackButton){
            startActivity(new Intent(WebActivity.this, FeedbackActivity.class));
        }
        else if (view.getId()==R.id.developerButton){
            startActivity(new Intent(WebActivity.this, AboutActivity.class));
        }



    }

    public void backAction(View view){
        view.startAnimation(buttonClick);

        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else{
            Toast.makeText(getApplicationContext(), "No web page to go Back!", Toast.LENGTH_SHORT).show();
        }

    }

    public void forwardAction(View view){
        view.startAnimation(buttonClick);

        if(webView.canGoForward())
        {
            webView.goForward();
        }
        else{
//            System.out.println("2. Else!!!");
            Toast.makeText(getApplicationContext(), "No web page to go Forward!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Feedback) {
            startActivity(new Intent(WebActivity.this, FeedbackActivity.class));
        }
        if (id==R.id.action_aboutdev){
            startActivity(new Intent(WebActivity.this, AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
