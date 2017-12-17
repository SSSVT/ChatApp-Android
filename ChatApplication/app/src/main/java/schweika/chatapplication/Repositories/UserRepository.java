package schweika.chatapplication.Repositories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.Models.User;

public class UserRepository
{
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(UserService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    UserService client = retrofit.create(UserService.class);

    public void add(User user)
    {
        client.add(user).enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {

            }
        });

    }
}
