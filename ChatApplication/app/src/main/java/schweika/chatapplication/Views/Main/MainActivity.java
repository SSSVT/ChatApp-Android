package schweika.chatapplication.Views.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import schweika.chatapplication.Views.List.ListActivity;
import schweika.chatapplication.Views.Login.LoginActivity;
import schweika.chatapplication.Views.Register.RegisterActivity;
import schweika.chatapplication.R;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void List(View view)
    {
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }
}
