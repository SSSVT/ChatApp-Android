package schweika.chatapplication.Repositories;


import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Observable;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.Services.RXFriendshipService;

public class RXFriendshipRepository extends RXSecuredRepository
{
    RXFriendshipService client = retrofit.create(RXFriendshipService.class);

    public RXFriendshipRepository(Token token)
    {
        super(token);
    }

    public Observable<List<Friendship>> findByUserID(long id)
    {
        return client.findByUserID(getTokenHeader(),id);
    }

    public Completable remove(UUID id)
    {
        return client.deleteFriendship(getTokenHeader(),id);
    }

    public Completable edit(Friendship friendship)
    {
        return client.editFriendship(getTokenHeader(),friendship.id,friendship);
    }
}
