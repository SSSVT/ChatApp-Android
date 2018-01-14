package schweika.chatapplication.ViewModels;

import schweika.chatapplication.Models.API.Message;
import schweika.chatapplication.Models.API.Participant;
import schweika.chatapplication.DataContext;

public class MessageViewModel
{
    public Message message;
    public Participant participant;

    public MessageViewModel(Message message, Participant participant)
    {
        this.message = message;
        this.participant = participant;
    }

    public boolean isOwner()
    {
        if (message.idUser == DataContext.getInstance().getUser().id)
            return true;
        else
            return false;
    }
}
