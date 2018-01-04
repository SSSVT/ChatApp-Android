package schweika.chatapplication.Repositories;

import io.reactivex.Observable;
import io.reactivex.Single;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Repositories.Services.RXFriendshipService;
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
}
