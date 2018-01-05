package schweika.chatapplication.Repositories;

import java.util.List;

import retrofit2.Callback;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.Services.RoomService;

public class RoomRepository extends SecuredRepository
{

    RoomService client = retrofit.create(RoomService.class);

    public RoomRepository(Token token)
    {
        super(token);
    }

    public void findByUserID(long id, Callback<List<Room>> callback)
    {
        client.findByUserID(getTokenHeader(),id).enqueue(callback);
    }

    public void findByUser(User user, Callback<List<Room>> callback)
    {
        client.findByUserID(getTokenHeader(),user.id).enqueue(callback);
    }

    public void create(Room room, Callback<Room> callback)
    {
        client.create(getTokenHeader(),room).enqueue(callback);
    }

    public void findAll(Callback<List<Room>> callback)
    {
        client.findAll(getTokenHeader()).enqueue(callback);
    }
}
