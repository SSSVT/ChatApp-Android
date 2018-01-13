package schweika.chatapplication.Models.API;

import com.google.gson.annotations.Expose;

import java.util.UUID;

public class Participant
{
    public UUID id;
    public long idRoom;
    public long idUser;

    public User user;
    //public Room room;
}
