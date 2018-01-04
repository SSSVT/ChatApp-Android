package schweika.chatapplication.Repositories.Services;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.User;

public interface RXUserService
{
    @GET("Users/GetCurrentUser")
    Single<User> getCurrentUser(@Header("Authorization") String token);

    @GET("Users/FindByUsername")
    Single<User> findByUsername(@Header("Authorization") String token, @Query("id") String username);

    @GET("Users/Detail")
    Single<User> findByID(@Header("Authorization") String token,@Query("id") long id);
}
