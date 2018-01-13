package schweika.chatapplication.Models.API;

import java.util.ArrayList;
import java.util.Date;

public class Room
{
    public long id;
    public long idOwner;
    public String name;
    public String description;
    public Date utcCreationDate;

    public User owner;
    public ArrayList<User> users;
    public ArrayList<Message> messages;
}
