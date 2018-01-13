package schweika.chatapplication.ViewModels;

import schweika.chatapplication.Models.API.Message;
import schweika.chatapplication.Models.API.Participant;

public class MessageViewModel
{
    public Message message;
    public Participant participant;

    public MessageViewModel(Message message, Participant participant)
    {
        this.message = message;
        this.participant = participant;
    }
}
