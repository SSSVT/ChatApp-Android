package schweika.chatapplication.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.BR;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.UserCredentials;
import schweika.chatapplication.Repositories.FutureTokenRepository;
import schweika.chatapplication.Repositories.AuthenticationRepository;
import schweika.chatapplication.Views.Login.LoginViewModelListener;

public class LoginViewModel extends BaseObservable
{
    private AuthenticationRepository userRepository = new AuthenticationRepository();
    private FutureTokenRepository futureTokenRepository = new FutureTokenRepository();
    private UserCredentials userCredentials;
    private LoginViewModelListener listener;
    private boolean processingState;

    public LoginViewModel(LoginViewModelListener listener)
    {
        this.listener = listener;
        this.userCredentials = new UserCredentials();
    }

    public LoginViewModel(LoginViewModelListener listener,UserCredentials userCredentials)
    {
        this.listener = listener;
        this.userCredentials = userCredentials;
    }

    @Bindable
    public boolean getProcessingState()
    {
        return this.processingState;
    }

    private void setProcessingState(boolean value)
    {
        this.processingState = value;
        notifyPropertyChanged(BR.processingState);
    }

    @Bindable
    public String getUsername()
    {
        return userCredentials.getUsername();
    }

    public void setUsername(String username)
    {
        userCredentials.setUsername(username);
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword()
    {
        return userCredentials.getPassword();
    }



    public void setPassword(String password)
    {
        userCredentials.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    public void onLoginClick()
    {
        setProcessingState(true);

        /*Future<Response<Token>> futureResponse = futureTokenRepository.loginAsync(userCredentials);

        try
        {
            Response<Token> response = futureResponse.get();

            if (response.isSuccessful())
            {
                listener.onLoginSuccess(response.body());
            }
            else
            {
                //TODO: show error
            }
        }
        catch (Exception e)
        {
            //TODO: show error
        }

        setProcessingState(false);*/

        userRepository.login(userCredentials, new Callback<Token>()
        {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response)
            {
                if (response.isSuccessful())
                {
                    setProcessingState(false);
                    listener.onLoginSuccess(response.body());
                }
                else
                {
                    listener.onLoginFailure();
                    setProcessingState(false);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t)
            {
                setProcessingState(false);
                listener.onLoginFailure();
            }
        });
    }
}
