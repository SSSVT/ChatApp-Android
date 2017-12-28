package schweika.chatapplication.Views.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import schweika.chatapplication.Models.User;
import schweika.chatapplication.R;
import schweika.chatapplication.Views.LoggedIn.LoggedInActivity;
import schweika.chatapplication.Views.LoggedOff.LoggedOffActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);

        String jwt = sharedPreferences.getString("JWT","");

        if (jwt == "")
        {
            Intent intent = new Intent(this, LoggedOffActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, LoggedInActivity.class);
            startActivity(intent);
        }
    }
}
