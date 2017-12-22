package schweika.chatapplication.Views.Login;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.LoginViewModel;
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
    public void onLoginSuccess()
    {

    }

    @Override
    public void onLoginFailure()
    {

    }
}

