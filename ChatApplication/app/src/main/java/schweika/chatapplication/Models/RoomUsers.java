package schweika.chatapplication.Models;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;

/**
 * Created by patri on 07.01.2018.
 */

public class RoomUsers
{
    public Room room;
    public ArrayList<User> users;

    public RoomUsers(Room room, ArrayList<User> users)
    {
        this.room = room;
        this.users = users;
    }
}
