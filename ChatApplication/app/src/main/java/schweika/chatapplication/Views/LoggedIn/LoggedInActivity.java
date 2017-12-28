package schweika.chatapplication.Views.LoggedIn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import schweika.chatapplication.R;
import schweika.chatapplication.Views.LoggedOff.LoggedOffActivity;

public class LoggedInActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
    }

    public void logOut(View view)
    {
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("JWT");
        editor.apply();

        Intent intent = new Intent(this, LoggedOffActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }
}
