package schweika.chatapplication.Repositories;

import retrofit2.Callback;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.UserCredentials;
import schweika.chatapplication.Repositories.Services.TokenService;

public class AuthenticationRepository extends Repository
{
    TokenService client = retrofit.create(TokenService.class);


    public void register(User user, Callback<Void> callback)
    {
        client.register(user).enqueue(callback);
    }

    public void login(UserCredentials userCredentials, Callback<Token> callback)
    {
        client.login(userCredentials).enqueue(callback);
    }

    public void isUsernameAvailable(String username, Callback<Boolean> callback)
    {
        client.isUsernameAvailable(username).enqueue(callback);
    }
}
