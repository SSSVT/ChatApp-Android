package schweika.chatapplication.Models.API;

import com.google.gson.annotations.Expose;

import java.util.UUID;

/**
 * Created by patri on 31.12.2017.
 */

public class Participant
{
    public UUID id;
    public long idRoom;
    public long idUser;

    //public User user;
    //public Room room;
}
