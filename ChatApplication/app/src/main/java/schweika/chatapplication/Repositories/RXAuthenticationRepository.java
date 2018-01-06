package schweika.chatapplication.Repositories;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Models.UserCredentials;
import schweika.chatapplication.Repositories.Services.RXAuthenticationService;

public class RXAuthenticationRepository extends RXRepository
{
    RXAuthenticationService client = retrofit.create(RXAuthenticationService.class);


    public Completable register(User user)
    {
        return client.register(user);
    }

    public Single<Token> login(UserCredentials userCredentials)
    {
        return client.login(userCredentials);
    }

    public Single<Boolean> isUsernameAvailable(String username)
    {
        return client.isUsernameAvailable(username);
    }
}
