package schweika.chatapplication.Views.Register;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import schweika.chatapplication.Models.User;
import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.RegistrationViewModel;
import schweika.chatapplication.databinding.ActivityRegisterBinding;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity
{
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);

        binding.setViewModel(new RegistrationViewModel());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

