package schweika.chatapplication.Views.LoggedOff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.StringReader;
import java.util.Date;

import schweika.chatapplication.DateDeserializer;
import schweika.chatapplication.Views.Login.LoginActivity;
import schweika.chatapplication.Views.RecyclerView.RecyclerViewActivity;
import schweika.chatapplication.Views.Register.RegisterActivity;
import schweika.chatapplication.R;

public class LoggedOffActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_off);
    }

    /*private void tryDate()
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class,new DateDeserializer())
                .create();

        String jsonDate = "1990-02-15T00:00:00";

        java.util.Date date = gson.fromJson(jsonDate,Date.class);
    }*/

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }

    public void Login(View view)
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void Register(View view)
    {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void RecyclerView(View view)
    {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }
}
