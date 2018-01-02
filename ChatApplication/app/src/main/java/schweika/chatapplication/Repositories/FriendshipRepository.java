package schweika.chatapplication.Repositories;

import retrofit2.Callback;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Repositories.Services.FriendshipService;

/**
 * Created by patri on 02.01.2018.
 */

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
}