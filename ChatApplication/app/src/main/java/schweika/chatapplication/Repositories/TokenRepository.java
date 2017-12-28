package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Future;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;
import schweika.chatapplication.Models.UserCredentials;

public class TokenRepository
{
    Gson gson = new GsonBuilder()
            //.setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TokenService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    TokenService client = retrofit.create(TokenService.class);

    public void register(User user)
    {
        client.register(user).enqueue(new Callback<RequestBody>()
        {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response)
            {

            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t)
            {

            }
        });
    }

    public void register(User user, RetrofitCallback<RequestBody> callback)
    {
        client.register(user).enqueue(new Callback<RequestBody>()
        {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response)
            {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t)
            {
                callback.onFailure();
            }
        });
    }

    public void login(UserCredentials userCredentials)
    {
        client.login(userCredentials).enqueue(new Callback<Token>()
        {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response)
            {

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t)
            {

            }
        });
    }

    public void login(UserCredentials userCredentials, RetrofitCallback<Token> callback)
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
    }
}
