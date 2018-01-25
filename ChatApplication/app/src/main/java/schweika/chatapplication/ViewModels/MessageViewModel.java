package schweika.chatapplication.ViewModels;

import schweika.chatapplication.Models.API.Message;
import schweika.chatapplication.Models.API.Participant;
import schweika.chatapplication.DataContext;
import schweika.chatapplication.Models.API.User;

public class MessageViewModel
{
    public Message message;
    //public Participant participant;
    //public User user;

    public MessageViewModel(Message message)
    {
        this.message = message;
        //this.participant = participant;
        //this.user = user;
    }

    public boolean isOwner()
    {
        return message.idUser == DataContext.getInstance().getUser().id;
    }
}
