package schweika.chatapplication.Repositories;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.Services.RXUserService;

public class RXUserRepository extends RXSecuredRepository
{
    RXUserService client = retrofit.create(RXUserService.class);

    public RXUserRepository(Token token)
    {
        super(token);
    }

    public Single<User> findById(long id)
    {
        return client.findByID(getTokenHeader(),id);
    }
    public Single<User> getCurrentUser()
    {
        return client.getCurrentUser(getTokenHeader());
    }
    public Single<User> findByUsername(String username)
    {
        return client.findByUsername(getTokenHeader(), username);
    }

    public Observable<List<User>> findUsersByUsername(String username)
    {
        return client.findUsersByUsername(getTokenHeader(),username);
    }
}
