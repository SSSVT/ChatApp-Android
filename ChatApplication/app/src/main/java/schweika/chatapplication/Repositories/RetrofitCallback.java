package schweika.chatapplication.Repositories;

import retrofit2.Response;

/**
 * Created by patri on 23.12.2017.
 */

public interface RetrofitCallback<T>
{
    void onSuccess(Response<T> response);

    void onFailure();
}
