package schweika.chatapplication.Views.LoggedIn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;

import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;
import schweika.chatapplication.R;
import schweika.chatapplication.Repositories.FutureUserRepository;
import schweika.chatapplication.Repositories.TokenSingleton;
import schweika.chatapplication.Views.LoggedOff.LoggedOffActivity;

@RequiresApi(api = Build.VERSION_CODES.N)
public class LoggedInActivity extends AppCompatActivity
{
    private TextView textView_username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        textView_username = findViewById(R.id.textView_username);

        User user = TokenSingleton.getInstance().getUser();

        textView_username.setText(user.getUsername());

        //SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        //Token token = gson.fromJson(sharedPref.getString("JWT",""),Token.class);

        //Token token = gson.fromJson(getIntent().getStringExtra("JWT"),Token.class);

        /*userRepository = new FutureUserRepository(token);

        Future<Response<User>> future = userRepository.getCurrentUserAsync();

        try
        {
            User user = future.get().body();

            textView_username.setText(user.getUsername());
        }
        catch (Exception e)
        {
            textView_username.setText("error");
        }*/



    }

    /*private void test()
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class,new DateDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserService client = retrofit.create(UserService.class);

        client.getCurrentUser(token.type + " " + token.token).enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {
                if (response.isSuccessful())
                {
                    User user = response.body();
                    textView_username.setText(user.getUsername());
                }
                else
                {
                    Log.i("unsuccessful", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                textView_username.setText("Error");
                Log.i("Error",t.getMessage());

                for (StackTraceElement stackTraceElement : t.getStackTrace())
                {
                    Log.i("Error",stackTraceElement.toString());
                }
            }
        });
    }*/

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
