package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.UserCredentials;
import schweika.chatapplication.Repositories.Services.NetworkConfig;
import schweika.chatapplication.Repositories.Services.TokenService;

public class AuthenticationRepository extends Repository
{
    TokenService client = retrofit.create(TokenService.class);


    public void register(User user, Callback<Void> callback)
    {
        client.register(user).enqueue(callback);
    }

    public void login(UserCredentials userCredentials, Callback<Token> callback)
    {
        client.login(userCredentials).enqueue(callback);
    }

    public void isUsernameAvailable(String username, Callback<Boolean> callback)
    {
        client.isUsernameAvailable(username).enqueue(callback);
    }
}
