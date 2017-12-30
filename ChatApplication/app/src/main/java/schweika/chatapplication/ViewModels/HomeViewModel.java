package schweika.chatapplication.ViewModels;

import android.databinding.BaseObservable;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.FriendRequest;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;

public class HomeViewModel extends BaseObservable
{
    public User currentUser;
    public ArrayList<Room> rooms;
    public ArrayList<User> friends;
    public ArrayList<FriendRequest> friendRequests;
}
