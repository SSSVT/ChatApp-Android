package schweika.chatapplication.ViewModels.Interfaces;

import schweika.chatapplication.Models.API.Token;

public interface LoginViewModelListener
{
    void onLoginSuccess(Token token);

    void onLoginFailure(String message);
}
