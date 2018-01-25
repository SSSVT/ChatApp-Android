package schweika.chatapplication.Views.Register;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.Interfaces.ViewModelListener;
import schweika.chatapplication.ViewModels.RegisterViewModel;
import schweika.chatapplication.ViewModels.RegisterViewModelWrapper;
import schweika.chatapplication.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements ViewModelListener
{
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RegisterViewModelWrapper viewModelWrapper = ViewModelProviders.of(this).get(RegisterViewModelWrapper.class);

        if (viewModelWrapper.viewModel == null)
        {
            viewModelWrapper.viewModel = new RegisterViewModel(this);
        }

        binding.setViewModel(viewModelWrapper.viewModel);
    }

    @Override
    public void onActionFailure(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActionSuccess()
    {
        finish();
    }
}

