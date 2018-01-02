package schweika.chatapplication.Models.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class Friendship
{
    public UUID id;
    public long idSender;
    public long idRecipient;
    public Date send;

    //public User sender;
    //public User recipient;
}
