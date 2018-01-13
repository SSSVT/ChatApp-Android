package schweika.chatapplication.Repositories.Services;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.Participant;

public interface RXParticipantService
{
    @POST("Participants/PostParticipant")
    Completable addParticipant(@Header("Authorization") String token, @Body Participant participant);

    @GET("Participants/GetByRoomID")
    Observable<List<Participant>> getParticipantsByRoomID(@Header("Authorization") String token, @Query("id") long id);
}
