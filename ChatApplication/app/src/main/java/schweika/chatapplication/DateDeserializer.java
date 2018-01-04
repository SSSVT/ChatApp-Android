package schweika.chatapplication;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateDeserializer implements JsonDeserializer<Date>
{
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        String stringDate = json.getAsString();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        try
        {
            return format.parse(stringDate);
        }
        catch (ParseException e)
        {
            Log.e("PARSE ERROR","COULDNT PARSE");
            return null;
        }
    }
}
