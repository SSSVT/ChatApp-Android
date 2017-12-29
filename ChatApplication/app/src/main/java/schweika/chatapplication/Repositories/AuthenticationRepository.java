package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;
import schweika.chatapplication.Models.UserCredentials;
import schweika.chatapplication.Repositories.Services.TokenService;

public class AuthenticationRepository
{
    Gson gson = new GsonBuilder()
            //.setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TokenService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

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

    /*public void login(UserCredentials userCredentials, RetrofitCallback<Token> callback)
    {
        client.login(userCredentials).enqueue(new Callback<Token>()
        {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response)
            {
                if (response.isSuccessful())
                {
                    callback.onSuccess(response);
                }
                else
                {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t)
            {
                callback.onFailure();
            }
        });
    }

    public void isUsernameAvailable(String username, RetrofitCallback<Boolean> callback)
    {
        client.isUsernameAvailable(username).enqueue(new Callback<Boolean>()
        {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response)
            {
                if (response.isSuccessful())
                {
                    callback.onSuccess(response);
                }
                else
                {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t)
            {
                callback.onFailure();
            }
        });
    }*/
}
