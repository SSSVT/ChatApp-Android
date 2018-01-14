package schweika.chatapplication.Views.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.TimeZone;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.R;
import schweika.chatapplication.Repositories.RXUserRepository;
import schweika.chatapplication.DataContext;
import schweika.chatapplication.Views.Home.HomeActivity;
import schweika.chatapplication.Views.Login.LoginActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SharedPreferences sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);

        String jwt = sharedPreferences.getString("JWT","");

        if (jwt == "")
        {
            startLoginActivity();
        }
        else
        {
            Token token = new Gson().fromJson(jwt,Token.class);

            RXUserRepository repository = new RXUserRepository(token);

            repository.getCurrentUser()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user ->
                    {
                        DataContext tokenSingleton = DataContext.getInstance();

                        tokenSingleton.setToken(token);
                        tokenSingleton.setUser(user);
                        startHomeActivity();

                    }, throwable ->
                    {
                        if (throwable instanceof HttpException)
                        {
                            deleteToken();
                        }

                        startLoginActivity();
                    });
        }
    }

    private void deleteToken()
    {
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("JWT");
        editor.apply();
    }

    private void startLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void startHomeActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
