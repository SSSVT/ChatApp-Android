package schweika.chatapplication.Models.API;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.UUID;

public class Message
{
    public UUID id;
    public long idRoom;
    public long idUser;
    public Date utcSend;
    public Date utcServerReceived;
    public String content;
    public String sender;

    //public Room room;
    //public User owner;
}
