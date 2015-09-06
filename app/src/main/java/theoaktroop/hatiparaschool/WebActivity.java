package theoaktroop.hatiparaschool;

import android.graphics.Bitmap;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private TextView txtUnititle;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);
    //private FloatingActionButton backButton,forwardButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

//        AdView mAdView = (AdView) findViewById(R.id.adView1);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
//        FloatingActionButton backButton = (FloatingActionButton) findViewById(R.id.backButton);
//        FloatingActionButton forwardButton = (FloatingActionButton) findViewById(R.id.forwardButton);


        webView = (WebView) findViewById(R.id.webView);




        String  url;

            url = "http://www.facebook.com";



            webView.setWebViewClient(new MyBrowser());
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);


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



}
