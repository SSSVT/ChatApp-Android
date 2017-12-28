package schweika.chatapplication.Views.Register;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.RegisterViewModel;
import schweika.chatapplication.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements RegisterListener
{
    ActivityRegisterBinding binding;
    RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new RegisterViewModel(this);

        binding.setViewModel(viewModel);
    }

    @Override
    public void registered()
    {
        finish();
    }
}

