package schweika.chatapplication.Models.API;

import java.util.Date;

public class Message
{
    public long id;
    public Date sendDateTime;
    public String Author; //USER??
    public String body;
    public Room room;
}
