package com.example.lubna.mytenth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.firebase.client.Firebase;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HOME");
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToSecondActivity();
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gotogroup();
            }
        });
        Firebase.setAndroidContext(this);
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

    public void sendEmail(View view) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//
//        EditText editText = (EditText) findViewById(R.id.editable_text);
//        String emailToBeSent = editText.getText().toString();
//
//        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sujeethjinesh@gmail.com", "hello@pearlhacks.com"});
//        intent.putExtra(Intent.EXTRA_EMAIL, emailToBeSent);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(intent, 0);
//        }
    }

<<<<<<< HEAD
    public void openFacebook(View view) {

=======
    public void facebookMethod(View view) {
        Uri uri = Uri.parse("http://www.facebook.com/sujeethjinesh");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
>>>>>>> parent of bf7ac81... IT WORKS
    }

    private void goToSecondActivity() {
        Intent intent = new Intent(this, myList2.class);
        startActivity(intent);
    }
    private void gotogroup() {
        Intent intent = new Intent(this, group_list.class);
        startActivity(intent);
    }
}