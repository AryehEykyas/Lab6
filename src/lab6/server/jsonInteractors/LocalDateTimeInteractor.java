package lab6.server.jsonInteractors;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class LocalDateTimeInteractor implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime>
{
    @Override
    public JsonElement serialize(LocalDateTime src, Type type, JsonSerializationContext context)
    {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        return LocalDateTime.parse(json.getAsString());
    }
}