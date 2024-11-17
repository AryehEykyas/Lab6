package lab6.server.interactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lab6.main.classes.Worker;
import lab6.main.consoles.BaseConsole;
import lab6.main.consoles.Console;
import lab6.server.jsonInteractors.LocalDateInteractor;
import lab6.server.jsonInteractors.LocalDateTimeInteractor;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Class that is used for processing json-files. Read from and write data in them.
 */
public class JsonInteractor
{
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateInteractor()).registerTypeAdapter(
            LocalDateTime.class, new LocalDateInteractor()).setPrettyPrinting().create();

    private static final Console console = new BaseConsole();

    /**
     * Receiving json string from linkedList.
     */
    public static String getStrJsonFromLinkedListWorker(LinkedList<Worker> workers)
    {
        try
        {
            return gson.toJson(workers);
        }
        catch (Exception e)
        {
            console.print(e.toString());
            return "Ошибка парсинга";
        }
    }

    /**
     * Receiving linkedList of workers from json string.
     */
    public static LinkedList<Worker> getLinkedListWorkerFromStrJson(String json)
    {
        try
        {
            LinkedList<Worker> workers = new LinkedList<>();
            if (!json.isEmpty()
            ) {

                Type collectionType = new TypeToken<LinkedList<Worker>>()
                {}.getType();
                workers = gson.fromJson(json, collectionType);
            }
            return workers;
        }
        catch (Exception e)
        {
            console.print("Json-файл поврежден, данные из него не были взяты.");
            return new LinkedList<>();
        }
    }

    /**
     * Receiving json-string from linkedList of workers.
     */
    public static String getStrJsonFromStepCollection(TreeMap<String, LinkedList<Worker>> stepCollection)
    {
        try
        {
            return gson.toJson(stepCollection);
        }
        catch (Exception e)
        {
            console.print(e.toString());
            return "Ошибка парсинга";
        }
    }

    /**
     * Receiving linkedList of workers from Json-string.
     */
    public static TreeMap<String, LinkedList<Worker>> getStepCollectionFromStrJson(String json) {
        try {
            TreeMap<String, LinkedList<Worker>> stepCollection = new TreeMap<>();
            if (!json.isEmpty()) {
                Type collectionType = new TypeToken<TreeMap<String, LinkedList<Worker>>>() {
                }.getType();
                stepCollection = gson.fromJson(json, collectionType);
            }
            return stepCollection;
        } catch (Exception e) {
            console.print(e.toString());
            console.print("Json-файл для состояний коллекции повреждён, данные из него не были взяты.");
            return new TreeMap<String, LinkedList<Worker>>();
        }
    }
}