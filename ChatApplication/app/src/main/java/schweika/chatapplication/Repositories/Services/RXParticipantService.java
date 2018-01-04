package schweika.chatapplication.Repositories.Services;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import schweika.chatapplication.Models.API.Participant;

public interface RXParticipantService
{
    @POST("Participants/PostParticipant")
    Observable<Void> addParticipant(@Header("Authorization") String token, @Body Participant participant);
}
