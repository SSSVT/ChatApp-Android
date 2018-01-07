package schweika.chatapplication.ViewModels.Interfaces;

import android.view.View;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.User;

public interface CreateRoomListener extends ViewModelListener
{
    public ArrayList<User> getSelectedUsers();
}
