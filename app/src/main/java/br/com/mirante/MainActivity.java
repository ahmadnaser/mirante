package br.com.mirante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Create account
    @Bind(R.id.personName) TextView personName;
    @Bind(R.id.email) EditText email;
    @Bind(R.id.password) EditText password;

    // Create channel
    @Bind(R.id.channel) EditText channel;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    /*public void testParse(){
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "barr");
        testObject.saveInBackground();

    }*/

    @OnClick(R.id.createAccountButton) void createUser(View view){
        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(personName.getText().toString());
        parseUser.setEmail(email.getText().toString());
        parseUser.setPassword(password.getText().toString());

        parseUser.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Log.e(LOG_TAG, "Hooray! Let them use the app now");
                    Toast.makeText(getApplicationContext(), "Hooray! Let them use the app now", Toast.LENGTH_SHORT).show();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(LOG_TAG, "Sign up didn't succeed. Look at the ParseException");
                    Toast.makeText(getApplicationContext(), "Sign up didn't succeed. Look at the ParseException", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @OnClick(R.id.createChannelButton) void createChannel(View view){
        String CHANNEL = "Channel";
        String CHANNEL_NAME = "channel_name";

        ParseObject parseObject = new ParseObject(CHANNEL);
        parseObject.put(CHANNEL_NAME, channel.getText().toString());

        parseObject.saveInBackground(new SaveCallback() {
            @Override public void done(ParseException e) {
                if (e == null) {
                    Log.e(LOG_TAG, "Channel " + channel.getText().toString() + " saved");
                    Toast.makeText(getApplicationContext(), "Channel "+channel.getText().toString()
                            + " saved", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e(LOG_TAG, "Error: something went wrong saving Channel " + channel.getText().toString());
                    Toast.makeText(getApplicationContext(), "Error: something went wrong saving Channel "
                            +channel.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }




}
