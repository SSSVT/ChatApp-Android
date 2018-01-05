package schweika.chatapplication.Repositories;

import java.util.List;

import retrofit2.Callback;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.Services.FriendshipService;

public class FriendshipRepository extends SecuredRepository
{
    FriendshipService client = retrofit.create(FriendshipService.class);

    public FriendshipRepository(Token token)
    {
        super(token);
    }

    public void sendFriendship(Friendship friendship, Callback<Friendship> callback)
    {
        client.createFriendship(getTokenHeader(),friendship).enqueue(callback);
    }

    public void findByUserID(long id, Callback<List<Friendship>> callback)
    {
        client.findByUserID(getTokenHeader(),id).enqueue(callback);
    }
}
