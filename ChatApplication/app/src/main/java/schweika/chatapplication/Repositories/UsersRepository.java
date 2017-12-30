package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.DateDeserializer;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Repositories.Services.NetworkConfig;
import schweika.chatapplication.Repositories.Services.UserService;

public class UsersRepository
{
    Token token;

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NetworkConfig.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    UserService client = retrofit.create(UserService.class);

    public UsersRepository(Token token)
    {
        this.token = token;
    }

    public void getCurrentUser(Callback<User> callback)
    {
        client.getCurrentUser(getTokenHeader()).enqueue(callback);
    }

    private String getTokenHeader()
    {
        return token.type + " " + token.token;
    }
}
