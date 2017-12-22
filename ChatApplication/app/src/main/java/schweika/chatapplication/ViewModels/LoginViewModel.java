package schweika.chatapplication.ViewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import schweika.chatapplication.Views.Login.LoginViewModelListener;

public class LoginViewModel
{
    private String username;
    private String password;
    private LoginViewModelListener listener;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public LoginViewModel(LoginViewModelListener listener)
    {
        this.listener = listener;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public View.OnClickListener onLoginClick()
    {
        //TODO: Login

        return new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(view.getContext(), username + " Logged in", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
