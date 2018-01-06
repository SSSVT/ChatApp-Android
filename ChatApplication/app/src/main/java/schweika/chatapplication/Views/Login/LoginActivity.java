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
import com.google.gson.GsonBuilder;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.R;
import schweika.chatapplication.DateDeserializer;
import schweika.chatapplication.Repositories.RXUserRepository;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.ViewModels.Interfaces.LoginViewModelListener;
import schweika.chatapplication.ViewModels.LoginViewModel;
import schweika.chatapplication.ViewModels.LoginViewModelWrapper;
import schweika.chatapplication.Views.Home.HomeActivity;
import schweika.chatapplication.Views.Register.RegisterActivity;
import schweika.chatapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginViewModelListener
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

        //tryDateParse();
    }

    private void tryDateParse()
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create();

        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String stringDate = "\"date\":\"1990-08-20T00:00:00\"";

        Date date = gson.fromJson(stringDate,Date.class);

        /*try
        {
            Date date = format.parse(stringDate);
            Log.i("Date",date.toString());
        }
        catch (Exception e)
        {
            Log.e("PARSE ERROR","COULDNT PARSE");
        }*/
    }

    @Override
    public void onLoginSuccess(Token token)
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
                    TokenSingleton.getInstance().setUser(user);
                    TokenSingleton.getInstance().setToken(token);
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
    public void onLoginFailure(String message)
    {
        //TODO: Show something
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }
}

