package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.Models.User;

public class UserRepository
{
    Gson gson = new GsonBuilder()
            //.setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(UserService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    UserService client = retrofit.create(UserService.class);

    public void add(User user)
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

    public void add(User user, RetrofitCallback callback)
    {
        client.register(user).enqueue(new Callback<RequestBody>()
        {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response)
            {
                callback.onSuccess();
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t)
            {
                callback.onFailure();
            }
        });

    }
}
