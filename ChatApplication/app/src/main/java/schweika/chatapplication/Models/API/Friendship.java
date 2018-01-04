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
    @SerializedName("utcServerReceived")
    public Date serverReceived;
    @SerializedName("utcAccepted")
    public Date accepted;

    //public User sender;
    //public User recipient;
}
