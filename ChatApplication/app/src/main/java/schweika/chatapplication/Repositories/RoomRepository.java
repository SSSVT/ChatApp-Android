package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.DateDeserializer;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Repositories.Services.NetworkConfig;
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
