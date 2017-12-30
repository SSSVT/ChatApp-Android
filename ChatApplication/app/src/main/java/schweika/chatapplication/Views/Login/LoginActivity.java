package schweika.chatapplication.Views.Login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.R;
import schweika.chatapplication.Repositories.TokenSingleton;
import schweika.chatapplication.Repositories.UsersRepository;
import schweika.chatapplication.ViewModels.LoginViewModel;
import schweika.chatapplication.ViewModels.LoginViewModelWrapper;
import schweika.chatapplication.Views.Home.HomeActivity;
import schweika.chatapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginViewModelListener
{
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LoginViewModelWrapper viewModelWrapper = ViewModelProviders.of(this).get(LoginViewModelWrapper.class);

        if (viewModelWrapper.viewModel == null)
        {
            viewModelWrapper.viewModel = new LoginViewModel(this);
        }

        binding.setViewModel(viewModelWrapper.viewModel);
    }

    @Override
    public void onLoginSuccess(Token token)
    {
        String jsonJWT = new Gson().toJson(token);

        SharedPreferences sharedPref = getSharedPreferences("login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("JWT",jsonJWT);
        editor.apply();

        TokenSingleton.getInstance().setToken(token);

        //TODO: do this in view model

        new UsersRepository(token).getCurrentUser(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {
                TokenSingleton.getInstance().setUser(response.body());
                openHomeActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {

            }
        });
    }

    private void openHomeActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailure()
    {
    }
}

