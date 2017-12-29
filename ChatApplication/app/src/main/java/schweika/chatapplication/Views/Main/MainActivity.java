package schweika.chatapplication.Views.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;
import schweika.chatapplication.R;
import schweika.chatapplication.Repositories.TokenSingleton;
import schweika.chatapplication.Repositories.UsersRepository;
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
            startLoggedOffActivity();
        }
        else
        {
            //TODO: try login with token

            Token token = new Gson().fromJson(jwt,Token.class);

            TokenSingleton tokenSingleton = TokenSingleton.getInstance();

            tokenSingleton.setToken(token);

            UsersRepository repository = new UsersRepository(token);

            repository.getCurrentUser(new Callback<User>()
            {
                @Override
                public void onResponse(Call<User> call, Response<User> response)
                {
                    if (response.isSuccessful())
                    {
                        tokenSingleton.setUser(response.body());
                        startLoggedInActivity();
                    }
                    else
                    {
                        tokenSingleton.setToken(null);
                        deleteToken();
                        startLoggedOffActivity();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t)
                {
                    startLoggedOffActivity();
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

    private void startLoggedOffActivity()
    {
        Intent intent = new Intent(this, LoggedOffActivity.class);
        startActivity(intent);
    }

    private void startLoggedInActivity()
    {
        Intent intent = new Intent(this, LoggedInActivity.class);
        startActivity(intent);
    }
}
