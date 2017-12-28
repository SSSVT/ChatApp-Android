package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.RequestBody;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;
import schweika.chatapplication.Models.UserCredentials;

/**
 * Created by patri on 28.12.2017.
 */

public class FutureTokenRepository
{
    Gson gson = new GsonBuilder()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TokenService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    TokenService client = retrofit.create(TokenService.class);

    private ExecutorService executor = Executors.newSingleThreadExecutor();

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

    public Future<Response<RequestBody>> registerAsync(User user)
    {
        return executor.submit(() ->
        {
            return client.register(user).execute();
        });
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
        catch (Exception e)
        {
            return null;
        }
    }
}
