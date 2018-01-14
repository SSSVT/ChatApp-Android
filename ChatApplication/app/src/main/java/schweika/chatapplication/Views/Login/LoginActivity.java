package schweika.chatapplication.Views.Login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.R;
import schweika.chatapplication.Repositories.RXUserRepository;
import schweika.chatapplication.DataContext;
import schweika.chatapplication.ViewModels.Interfaces.GenericViewModelListener;
import schweika.chatapplication.ViewModels.LoginViewModel;
import schweika.chatapplication.ViewModels.LoginViewModelWrapper;
import schweika.chatapplication.Views.Home.HomeActivity;
import schweika.chatapplication.Views.Register.RegisterActivity;
import schweika.chatapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements GenericViewModelListener<Token>
{
    ActivityLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        LoginViewModelWrapper viewModelWrapper = ViewModelProviders.of(this).get(LoginViewModelWrapper.class);

        if (viewModelWrapper.viewModel == null)
        {
            viewModelWrapper.viewModel = new LoginViewModel(this);
        }

        viewModel = viewModelWrapper.viewModel;

        binding.setViewModel(viewModelWrapper.viewModel);
    }

    @Override
    public void onActionSuccess(Token token)
    {
        String jsonJWT = new Gson().toJson(token);

        SharedPreferences sharedPref = getSharedPreferences("login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("JWT",jsonJWT);
        editor.apply();

        //TODO: do this somewhere else

        new RXUserRepository(token).getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user ->
                {
                    DataContext.getInstance().setUser(user);
                    DataContext.getInstance().setToken(token);
                    openHomeActivity();
                });
    }

    private void openHomeActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onRegisterClick(View view)
    {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActionFailure(String message)
    {
        //TODO: Show something
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }

}

