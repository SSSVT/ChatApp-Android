package schweika.chatapplication.Repositories;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import schweika.chatapplication.Models.API.Message;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.Services.RXFriendshipService;
import schweika.chatapplication.Repositories.Services.RXMessageService;

public class RXMessageRepository extends RXSecuredRepository
{
    RXMessageService client = retrofit.create(RXMessageService.class);

    public RXMessageRepository(Token token)
    {
        super(token);
    }

    public Completable create(Message message)
    {
        return client.createMessage(getTokenHeader(),message);
    }

    public Observable<List<Message>> getByRoomID(long id, Date lastMessageTime)
    {
        return client.getMessagesByRoomID(getTokenHeader(),id,lastMessageTime);
    }
}
