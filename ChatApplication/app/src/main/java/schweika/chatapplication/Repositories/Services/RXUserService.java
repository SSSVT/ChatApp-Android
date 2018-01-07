package schweika.chatapplication.Repositories.Services;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
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

    @GET("Users/SearchForUsersByUsername/{id}")
    Observable<List<User>> findUsersByUsername(@Header("Authorization") String token, @Path("id") String id);
}
