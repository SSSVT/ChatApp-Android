package schweika.chatapplication.ViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Repositories.RXFriendshipRepository;
import schweika.chatapplication.Repositories.RXRoomRepository;
import schweika.chatapplication.Repositories.RXUserRepository;
import schweika.chatapplication.DataContext;

public class HomeViewModel extends ViewModel
{

    public MutableLiveData<ArrayList<Room>> rooms = new MutableLiveData<>();
    //public MutableLiveData<HashMap<Friendship,User>> friends = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Friendship>> friendships = new MutableLiveData<>();

    private Token token = DataContext.getInstance().getToken();
    public User currentUser = DataContext.getInstance().getUser();

    private RXFriendshipRepository rxFriendshipRepository = new RXFriendshipRepository(token);
    private RXUserRepository rxUserRepository = new RXUserRepository(token);
    private RXRoomRepository rxRoomRepository = new RXRoomRepository(token);

    public HomeViewModel()
    {
        rooms.setValue(new ArrayList<>());
        friendships.setValue(new ArrayList<>());

        updateRooms();
        updateFriends();
    }

    public String getUsername()
    {
        return (currentUser.username.substring(0, 1).toUpperCase() + currentUser.username.substring(1));
    }

    public void updateRooms()
    {
        rxRoomRepository.findByUserID(currentUser.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rooms1 ->
                {
                    rooms.setValue(new ArrayList<>(rooms1));
                });
    }

    private long getOtherUserId(Friendship friendship)
    {
        if (friendship.idSender != currentUser.id)
        {
            return friendship.idSender;
        }
        else
        {
            return friendship.idRecipient;
        }
    }

    public void updateFriends()
    {
        rxFriendshipRepository.findByUserID(currentUser.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(friendships1 ->
                {
                    this.friendships.setValue(new ArrayList<>(friendships1));
                });

        /*HashMap<Friendship, User> friendshipUserHashMap = new HashMap<>();

        rxFriendshipRepository.findByUserID(currentUser.id)
                .flatMapIterable(friendships -> friendships)
                .flatMap(friendship -> rxUserRepository.findById(getOtherUserId(friendship)).toObservable(),(friendship,user) -> new Pair<Friendship,User>(friendship,user))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pairs -> {

                    for (Pair<Friendship,User> pair : pairs)
                    {
                        friendshipUserHashMap.put(pair.first,pair.second);
                    }

                    friends.setValue(friendshipUserHashMap);
                });*/
    }
}
