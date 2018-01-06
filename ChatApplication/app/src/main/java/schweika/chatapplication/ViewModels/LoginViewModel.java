package schweika.chatapplication.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.BR;
import schweika.chatapplication.Models.UserCredentials;
import schweika.chatapplication.Repositories.RXAuthenticationRepository;
import schweika.chatapplication.ViewModels.Interfaces.LoginViewModelListener;

public class LoginViewModel extends BaseObservable
{
    private RXAuthenticationRepository rxAuthenticationRepository = new RXAuthenticationRepository();
    private UserCredentials userCredentials;
    private LoginViewModelListener listener;
    private boolean processingState;

    public LoginViewModel(LoginViewModelListener listener)
    {
        this.listener = listener;
        this.userCredentials = new UserCredentials();
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

        rxAuthenticationRepository.login(userCredentials)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {setProcessingState(false);})
                .subscribe(listener::onLoginSuccess,
                        throwable ->
                        {
                            listener.onLoginFailure("Login failed");
                        });
    }
}
