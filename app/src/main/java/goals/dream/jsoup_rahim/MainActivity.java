package goals.dream.jsoup_rahim;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static android.R.attr.data;
import static android.R.attr.name;
import static android.R.attr.permission;

public class MainActivity extends AppCompatActivity {
 //<uses-permission android:name="android.permission.INTERNET"/>
    String url_to_post = "http://dreamgoals.info/cl_post_doug/jsoup_log.php";
    String data_to_php;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void button_click(View view) {

      Toast.makeText(getApplicationContext(), "AsynchTask Run", Toast.LENGTH_LONG).show();
      data_to_php = "whatever you want to post";
      new post_to_log().execute();
    }

    //=======
        public class post_to_log extends AsyncTask<Void,Void,Void> {

        String words;

        @Override
        protected Void doInBackground(Void... params) {
        //<uses-permission android:name="android.permission.INTERNET"/>
        //use this to call
        //new post_to_log().execute();
            try {
              // wv1.loadUrl("http://www.dreamgoals.info/cl_post/send_to_log.php");
               // wv1"http://www.dreamgoals.info/cl_post/send_to_log.php";


                Document doc = Jsoup.connect(url_to_post)
                        .data("data_sent", data_to_php)
                        .data("password", "pass_xxx")
                        .data("log_data", "somedata_xxx")
                        .post();
                //this is the response from the php script
                words = doc.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        //if post is sucessful the this will run
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            TextView my_tv_1 = (TextView)findViewById(R.id.tv_1);
            my_tv_1.setText(words);
        }
    }

}
