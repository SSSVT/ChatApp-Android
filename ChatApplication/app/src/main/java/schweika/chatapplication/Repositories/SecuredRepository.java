package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.DateDeserializer;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Repositories.Services.NetworkConfig;

public abstract class SecuredRepository extends Repository
{
    protected Token token;

    public SecuredRepository(Token token)
    {
        this.token = token;
    }

    protected String getTokenHeader()
    {
        return token.type + " " + token.token;
    }
}
