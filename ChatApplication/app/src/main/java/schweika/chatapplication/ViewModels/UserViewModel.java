package schweika.chatapplication.ViewModels;

import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.ViewModels.Abstract.SelectableViewModel;

public class UserViewModel extends SelectableViewModel
{
    public User user;

    public UserViewModel(User user)
    {
        this.user = user;
    }
}
