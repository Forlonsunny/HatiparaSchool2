package theoaktroop.hatiparaschool;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasan Abdullah on 16-Jul-15.
 */
public class PostMethod extends AsyncTask<String,Void,String> {
    private Context context;
    public MyInterface myInterface = null;

    public PostMethod(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... arg0) {

        String packageName = "theoaktroop.hatiparaschool";
        String link = "http://apitest.oaktroop.com/api/v1/feedback"; //API link
//        String link = "http://apitest.oaktroop.com/api/v1/test"; //Testing post request


        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(link);
        HttpResponse response = null;
        String line = "";

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);


        nameValuePair.add(new BasicNameValuePair("package_name", packageName));
        nameValuePair.add(new BasicNameValuePair("name", arg0[0]));
        nameValuePair.add(new BasicNameValuePair("email", arg0[1]));
        nameValuePair.add(new BasicNameValuePair("phone", arg0[2]));
        nameValuePair.add(new BasicNameValuePair("feedback", arg0[3]));

//        nameValuePair.add(new BasicNameValuePair("username", "Hasan Abdullah"));
//        nameValuePair.add(new BasicNameValuePair("password", "zxzxzx"));


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            response = httpClient.execute(httpPost);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            while ((line = rd.readLine()) != null) {
//                System.out.println("Response from Our API: " + line);
                break;
            }

        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    @Override
    protected void onPostExecute(String result){
        myInterface.processFinish(result);
    }
}
