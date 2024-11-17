package lab6.server.jsonInteractors;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * Class that is used as a serializer-deserializer for parsing data in LocalDate format.
 */
public class LocalDateInteractor implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate>
{
    @Override
    public JsonElement serialize(LocalDate src, Type type, JsonSerializationContext context)
    {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        return LocalDate.parse(json.getAsString());
    }
}