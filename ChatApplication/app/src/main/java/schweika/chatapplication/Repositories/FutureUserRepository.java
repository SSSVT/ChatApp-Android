package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.DateDeserializer;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;
import schweika.chatapplication.Repositories.Services.TokenService;
import schweika.chatapplication.Repositories.Services.UserService;

/**
 * Created by patri on 28.12.2017.
 */

public class FutureUserRepository
{
    private Token token;

    private ExecutorService executor = Executors.newCachedThreadPool();

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(UserService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    UserService client = retrofit.create(UserService.class);

    public FutureUserRepository(Token token)
    {
        this.token = token;
    }

    public Token getToken()
    {
        return token;
    }

    public void setToken(Token token)
    {
        this.token = token;
    }

    public Response<User> getCurrentUser()
    {
        try
        {
            return client.getCurrentUser(getTokenHeader()).execute();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    public Future<Response<User>> getCurrentUserAsync()
    {
        return executor.submit(() ->
        {
            return client.getCurrentUser(getTokenHeader()).execute();
        });
    }

    private String getTokenHeader()
    {
        return token.type + " " + token.token;
    }
}
