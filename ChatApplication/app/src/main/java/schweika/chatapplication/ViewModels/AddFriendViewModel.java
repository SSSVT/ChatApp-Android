package schweika.chatapplication.ViewModels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schweika.chatapplication.Models.API.Friendship;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Repositories.FriendshipRepository;
import schweika.chatapplication.Repositories.UserRepository;
import schweika.chatapplication.TokenSingleton;
import schweika.chatapplication.Views.Home.Fragments.FriendsFragment;

public class AddFriendViewModel
{
    private String username;
    private ViewModelListener listener;

    private UserRepository userRepository = new UserRepository(TokenSingleton.getInstance().getToken());
    private FriendshipRepository friendshipRepository = new FriendshipRepository(TokenSingleton.getInstance().getToken());

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
            userRepository.findByUsername(this.username, new Callback<User>()
            {
                @Override
                public void onResponse(Call<User> call, Response<User> response)
                {
                    if (response.isSuccessful())
                    {
                        Friendship friendship = new Friendship();

                        friendship.idSender = TokenSingleton.getInstance().getUser().id;
                        friendship.idRecipient = response.body().id;

                        friendshipRepository.sendFriendship(friendship, new Callback<Friendship>()
                        {
                            @Override
                            public void onResponse(Call<Friendship> call, Response<Friendship> response)
                            {
                                if (response.isSuccessful())
                                {
                                    listener.onActionSuccess();
                                }
                            }

                            @Override
                            public void onFailure(Call<Friendship> call, Throwable t)
                            {

                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t)
                {

                }
            });
        }
    }
}
