package schweika.chatapplication.RecyclerView.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import schweika.chatapplication.Models.API.User;

/**
 * Created by patri on 30.12.2017.
 */

public class UserViewModel extends BaseObservable
{
    private User user;

    public UserViewModel(User user)
    {
        this.user = user;
    }

    public void setUser(User item)
    {
        user = item;
    }

    @Bindable
    public String getUsername()
    {
        return user.getUsername();
    }

    @Bindable
    public String getEmail()
    {
        return user.getEmail();
    }
}
