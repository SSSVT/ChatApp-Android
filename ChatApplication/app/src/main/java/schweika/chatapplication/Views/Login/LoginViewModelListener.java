package schweika.chatapplication.Views.Login;

import schweika.chatapplication.Models.Token;

/**
 * Created by patri on 22.12.2017.
 */

public interface LoginViewModelListener
{
    void onLoginSuccess(Token token);

    void onLoginFailure();
}
