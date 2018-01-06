package schweika.chatapplication.ViewModels;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Repositories.RXFriendshipRepository;
import schweika.chatapplication.Repositories.RXUserRepository;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.ViewModels.Interfaces.ViewModelListener;

public class AddFriendViewModel
{
    private String username;
    private ViewModelListener listener;

    private RXUserRepository rxUserRepository = new RXUserRepository(TokenSingleton.getInstance().getToken());
    private RXFriendshipRepository rxFriendshipRepository = new RXFriendshipRepository(TokenSingleton.getInstance().getToken());

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
        if (username != TokenSingleton.getInstance().getUser().username)
        {

            rxUserRepository.findByUsername(this.username)
                    .flatMap(user ->
                    {
                        Friendship friendship = new Friendship();

                        friendship.idSender = TokenSingleton.getInstance().getUser().id;
                        friendship.idRecipient = user.id;

                        return rxFriendshipRepository.create(friendship);
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( friendship ->
                    {
                        listener.onActionSuccess();
                    });

        }
    }
}
