package schweika.chatapplication.Repositories;

import io.reactivex.Observable;
import retrofit2.Callback;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.UserCredentials;
import schweika.chatapplication.Repositories.Services.RXTokenService;
import schweika.chatapplication.Repositories.Services.TokenService;

public class RXAuthenticationRepository extends RXRepository
{
    RXTokenService client = retrofit.create(RXTokenService.class);


    public Observable<Void> register(User user)
    {
        return client.register(user);
    }

    public Observable<Token> login(UserCredentials userCredentials)
    {
        return client.login(userCredentials);
    }

    public Observable<Boolean> isUsernameAvailable(String username)
    {
        return client.isUsernameAvailable(username);
    }
}
