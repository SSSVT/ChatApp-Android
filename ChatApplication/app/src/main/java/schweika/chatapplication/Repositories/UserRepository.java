package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.DateDeserializer;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Repositories.Services.NetworkConfig;
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
}
