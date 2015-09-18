package theoaktroop.hatiparaschool;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.rzlts.appinbox.AppInbox;
import com.rzlts.appinbox.model.Gender;
import com.rzlts.appinbox.views.InboxView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        AppInbox.startInbox(this, "eOhxRprN7O6nGUydIBe5eMEZQDIpIcEXNVQJ2MlB2qY", "880690582470", null, null, null, "The", "Oak Troops", "oaktroop2015@gmail.com", Gender.BOTH, 0);

        FrameLayout rlLayout = ( FrameLayout) this.findViewById(R.id.rlLayout);
        final InboxView inbox = new InboxView(this);
        rlLayout.addView(inbox);



    }
    public void btOnclick(View view)
    {
        if(isNetworkAvailable()){
            startActivity(new Intent(MainActivity.this, WebActivity.class));
        }
        else{
        Toast.makeText(getApplicationContext(), "Please check your Internet connection", Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(MainActivity.this,FeedbackActivity.class));
        }
        if (id==R.id.action_aboutdev){
            startActivity(new Intent(MainActivity.this,AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
