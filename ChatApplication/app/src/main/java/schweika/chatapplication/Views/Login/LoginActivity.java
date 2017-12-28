package schweika.chatapplication.Views.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import schweika.chatapplication.Models.Token;
import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.LoginViewModel;
import schweika.chatapplication.Views.LoggedIn.LoggedInActivity;
import schweika.chatapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginViewModelListener
{
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        binding.setViewModel(new LoginViewModel(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onLoginSuccess(Token token)
    {
        String jsonJWT = new Gson().toJson(token);

        SharedPreferences sharedPref = getSharedPreferences("login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("JWT",jsonJWT);
        editor.apply();

        openLoggedInActivity();
    }

    private void openLoggedInActivity()
    {
        Intent intent = new Intent(this, LoggedInActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailure()
    {
    }
}

