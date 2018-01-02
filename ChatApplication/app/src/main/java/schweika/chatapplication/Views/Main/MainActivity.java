package schweika.chatapplication.Views.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.R;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.Repositories.UserRepository;
import schweika.chatapplication.Views.Home.HomeActivity;
import schweika.chatapplication.Views.Login.LoginActivity;

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
            startLoginActivity();
        }
        else
        {
            Token token = new Gson().fromJson(jwt,Token.class);

            UserRepository repository = new UserRepository(token);

            repository.getCurrentUser(new Callback<User>()
            {
                @Override
                public void onResponse(Call<User> call, Response<User> response)
                {
                    if (response.isSuccessful())
                    {
                        TokenSingleton tokenSingleton = TokenSingleton.getInstance();

                        tokenSingleton.setToken(token);
                        tokenSingleton.setUser(response.body());
                        startHomeActivity();
                    }
                    else
                    {
                        deleteToken();
                        startLoginActivity();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t)
                {
                    startLoginActivity();
                }
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
