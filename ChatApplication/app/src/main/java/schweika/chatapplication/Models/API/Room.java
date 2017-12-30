package schweika.chatapplication.Models.API;

import java.util.ArrayList;

public class Room
{
    public long id;
    public String name;
    public ArrayList<User> users;
    public ArrayList<Message> messages;
}
