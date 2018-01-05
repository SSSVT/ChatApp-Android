package schweika.chatapplication.Repositories;

import retrofit2.Callback;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Repositories.Services.UserService;

public class UserRepository extends SecuredRepository
{
    UserService client = retrofit.create(UserService.class);

    public UserRepository(Token token)
    {
        super(token);
    }

    public void getCurrentUser(Callback<User> callback)
    {
        client.getCurrentUser(getTokenHeader()).enqueue(callback);
    }

    public void findByUsername(String username, Callback<User> callback)
    {
        client.findByUsername(getTokenHeader(),username).enqueue(callback);
    }

    public void findByID(long id, Callback<User> callback)
    {
        client.findByID(getTokenHeader(),id).enqueue(callback);
    }
}
