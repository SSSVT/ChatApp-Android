package schweika.chatapplication.Repositories;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.Services.RXRoomService;

public class RXRoomRepository extends RXSecuredRepository
{
    RXRoomService client = retrofit.create(RXRoomService.class);

    public RXRoomRepository(Token token)
    {
        super(token);
    }

    public Observable<List<Room>> findByUserID(long id)
    {
        return client.findByUserID(getTokenHeader(),id);
    }

    public Single<Room> create(Room room)
    {
        return client.create(getTokenHeader(),room);
    }

    public Completable update(Room room)
    {
        return client.update(getTokenHeader(),room.id,room);
    }

    public Completable delete(long id)
    {
        return client.delete(getTokenHeader(),id);
    }

    public Completable leaveRoom(long id)
    {
        return client.leaveRoom(getTokenHeader(),id);
    }
}
