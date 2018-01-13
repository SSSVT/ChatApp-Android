package schweika.chatapplication.Repositories;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.Room;

public class ApiRequestManager
{
    public ArrayList<Friendship> friendships;
    public ArrayList<Room> rooms;

    private static final ApiRequestManager ourInstance = new ApiRequestManager();

    public static ApiRequestManager getInstance()
    {
        return ourInstance;
    }

    private ApiRequestManager()
    {
    }
}
