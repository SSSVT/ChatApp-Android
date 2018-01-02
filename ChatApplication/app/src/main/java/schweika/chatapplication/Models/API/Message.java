package schweika.chatapplication.Models.API;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.UUID;

public class Message
{
    public UUID id;
    public long idRoom;
    public long idUser;
    public Date send;
    public String content;

    public Room room;
    public User owner;
}
