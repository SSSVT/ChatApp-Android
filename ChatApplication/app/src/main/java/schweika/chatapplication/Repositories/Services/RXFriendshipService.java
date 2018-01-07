package schweika.chatapplication.Repositories.Services;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import schweika.chatapplication.Models.API.Friendship;

/**
 * Created by patri on 03.01.2018.
 */

public interface RXFriendshipService
{
    @POST("Friendships/PostFriendship")
    Single<Friendship> createFriendship(@Header("Authorization") String token, @Body Friendship friendship);

    @PUT("Friendships/PutFriendship/{id}")
    Completable editFriendship(@Header("Authorization") String token, @Path("id") UUID uuid, @Body Friendship friendship);

    @DELETE("Friendships/DeleteFriendship/{id}")
    Completable deleteFriendship(@Header("Authorization") String token, @Path("id") UUID uuid);

    @GET("Friendships/GetByUserID/{id}")
    Observable<List<Friendship>> findByUserID(@Header("Authorization") String token, @Path("id") long id);

    @PUT("Friendships/AcceptFriendship/{id}")
    Completable acceptFriendship(@Header("Authorization") String token, @Path("id") UUID id);
}
