package schweika.chatapplication.ViewModels;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Repositories.RXFriendshipRepository;
import schweika.chatapplication.Repositories.RXUserRepository;
import schweika.chatapplication.DataContext;
import schweika.chatapplication.ViewModels.Interfaces.ViewModelListener;

public class AddFriendViewModel
{
    private String username;
    private ViewModelListener listener;

    private RXUserRepository rxUserRepository = new RXUserRepository(DataContext.getInstance().getToken());
    private RXFriendshipRepository rxFriendshipRepository = new RXFriendshipRepository(DataContext.getInstance().getToken());

    public AddFriendViewModel(ViewModelListener listener)
    {
        this.listener = listener;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void add()
    {
        if (!username.equals(DataContext.getInstance().getUser().username))
        {

            rxUserRepository.findByUsername(this.username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((user)->
                    {
                        if (user != null)
                        {
                            rxFriendshipRepository.isUserFriend(user.id)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(aBoolean ->
                                    {
                                        if (aBoolean == false)
                                        {
                                            Friendship friendship = new Friendship();

                                            friendship.idSender = DataContext.getInstance().getUser().id;
                                            friendship.idRecipient = user.id;

                                            rxFriendshipRepository.create(friendship)
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe(friendship1 ->
                                                    {
                                                        listener.onActionSuccess();
                                                    }, throwable ->
                                                    {

                                                    });

                                        }
                                    }, throwable ->
                                    {

                                    });
                        }
                    }, throwable ->
                    {

                    });
        }
    }
}
