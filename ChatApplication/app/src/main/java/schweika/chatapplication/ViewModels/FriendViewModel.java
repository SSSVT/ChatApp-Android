package schweika.chatapplication.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.BR;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.GenericRecyclerViewAdapter;
import schweika.chatapplication.Repositories.RXFriendshipRepository;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.ViewModels.Interfaces.GenericViewModelListener;

public class FriendViewModel extends BaseObservable
{
    public Friendship friendship;
    public User user;
    private RXFriendshipRepository rxFriendshipRepository = new RXFriendshipRepository(TokenSingleton.getInstance().getToken());
    private GenericViewModelListener<FriendViewModel> listener;

    public FriendViewModel(Friendship friendship, User user, GenericViewModelListener<FriendViewModel> listener)
    {
        this.friendship = friendship;
        this.user = user;
        this.listener = listener;
    }

    public void add()
    {
        rxFriendshipRepository.acceptFriendship(friendship.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->
                {
                    this.friendship.accepted = new Date();
                    notifyPropertyChanged(BR.actionMessage);
                    notifyPropertyChanged(BR.displayAdd);
                });
    }

    public void remove()
    {
        rxFriendshipRepository.remove(friendship.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->
                {
                    listener.onActionSuccess(this);
                });
    }

    public String getUsername()
    {
        return (user.username.substring(0, 1).toUpperCase() + user.username.substring(1));
    }

    private boolean isFriend()
    {
        if (friendship.accepted == null)
            return false;
        else
            return true;
    }

    private boolean isSender()
    {
        if (friendship.idSender == TokenSingleton.getInstance().getUser().id)
        {
            return true;
        }
        else
            return false;
    }

    @Bindable
    public boolean getDisplayAdd()
    {
        if (!isFriend() && !isSender())
            return true;
        else
            return false;
    }

    @Bindable
    public boolean getDisplayPending()
    {
        if (!isFriend() && isSender())
            return true;
        else
            return false;
    }

    @Bindable
    public String getActionMessage()
    {
        if (isSender() && !isFriend())
        {
            return "Pending friend request.";
        }
        else if (!isFriend())
        {
            return "Wants to add you.";
        }
        else
            return null;
    }
}
