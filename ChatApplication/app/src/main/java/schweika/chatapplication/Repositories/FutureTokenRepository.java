package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.UserCredentials;
import schweika.chatapplication.Repositories.Services.NetworkConfig;
import schweika.chatapplication.Repositories.Services.TokenService;

/**
 * Created by patri on 28.12.2017.
 */

public class FutureTokenRepository
{
    Gson gson = new GsonBuilder()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NetworkConfig.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    TokenService client = retrofit.create(TokenService.class);

    private ExecutorService executor = Executors.newCachedThreadPool();

    public Future<Response<Token>> loginAsync(UserCredentials userCredentials)
    {
        return executor.submit(() ->
        {
            return client.login(userCredentials).execute();
        });
    }

    /*public CompletableFuture<Response<Token>> loginAsync(UserCredentials userCredentials)
    {
        return executor.submit(() ->
        {
            return client.login(userCredentials).execute();
        });
    }*/

    public Future<Response<Void>> registerAsync(User user)
    {
        return executor.submit(() ->
        {
            return client.register(user).execute();
        });
    }

    public Response<Void> register(User user)
    {
        try
        {
            return client.register(user).execute();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    public Future<Response<Boolean>> isUsernameAvailableAsync(String username)
    {
        return executor.submit(() ->
        {
            return client.isUsernameAvailable(username).execute();
        });
    }

    public Response<Boolean> isUsernameAvailable(String username)
    {
        try
        {
            return client.isUsernameAvailable(username).execute();
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
