package lab6.server.interactor;

import lab6.main.classes.Worker;
import lab6.main.interactors.FileInteractor;
import lab6.server.Configuration;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Class that is used for working with different stated of a collection.
 */
public class CollectionHistory
{
    private static String dataFileName = Configuration.getStartFileName();

    private static final String fileName = Configuration.getHistoryFileName();

    public TreeMap<String, LinkedList<Worker>> getStepCollection()
    {
        return JsonInteractor.getStepCollectionFromStrJson(lab6.main.interactors.FileInteractor.readTextFromFile(CollectionHistory.getFileName()));
    }

    public static String getFileName()
    {
        return fileName;
    }

    public static void setDataFileName(String newDataFileName)
    {
        dataFileName = newDataFileName;
    }

    /**
     * Taking from file collection's state.
     */
    public LinkedList<Worker> getCurState()
    {
        TreeMap<String, LinkedList<Worker>> stepCollection = getStepCollection();
        return stepCollection.get(String.valueOf(stepCollection.size() - 1));
    }

    /**
     * Adding current state of collection.
     *
     * @param linkedList - current state.
     */
    public void addStateCollection(LinkedList<Worker> linkedList)
    {
        TreeMap<String, LinkedList<Worker>> stepCollection = getStepCollection();
        stepCollection.put(String.valueOf(stepCollection.size()), linkedList);
        save(stepCollection);
    }

    /**
     * Setting starting state.
     *
     * @param linkedList - starting collection's state
     */
    public void setStart(LinkedList<Worker> linkedList)
    {
        rollback(1);
        addStateCollection(linkedList);
    }

    /**
     * Cancelling actions of the last n commands.
     *
     * @param n number of commands that need to be cancelled
     */
    public void rollback(int n)
    {
        TreeMap<String, LinkedList<Worker>> stepCollection = getStepCollection();
        int total = stepCollection.size();
        n = Math.max(0, Math.min(n, total - 1));

        for (int i = total - n; i < total; i++)
        {
            stepCollection.remove(String.valueOf(i));
        }
        save(stepCollection);
    }

    /**
     * Saving words of the current states in file.
     * Saving another current state in file of the current state.
     *
     * @param stepCollection dictionary of current states
     */
    public void save(TreeMap<String, LinkedList<Worker>> stepCollection)
    {
        FileInteractor.writeTextInFile(fileName, JsonInteractor.getStrJsonFromStepCollection(stepCollection));
        if (getStepCollection().size() > 0)
        {
            FileInteractor.writeTextInFile(dataFileName, JsonInteractor.getStrJsonFromLinkedListWorker(getCurState()));
        }
    }
}