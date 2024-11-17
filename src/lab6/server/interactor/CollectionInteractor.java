package lab6.server.interactor;

import lab6.main.classes.Worker;
import lab6.main.consoles.BaseConsole;
import lab6.main.consoles.Console;
import lab6.main.exceptions.DuplicateIdException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Class that is used to work with a collection.
 */
public class CollectionInteractor
{
    private Console console = new BaseConsole();
    private LinkedList<Worker> linkedList;
    private final TreeMap<Integer, Worker> idWorkerFromCollection = new TreeMap<>();
    private final LocalDateTime creationDate;

    public CollectionInteractor()
    {
        creationDate = LocalDateTime.now();
        linkedList = new LinkedList<>();
    }

    public CollectionInteractor(LinkedList<Worker> workers)
    {
        this();
        setWorkers(workers);
    }

    public void setConsole(Console console)
    {
        this.console = console;
    }

    public void setWorkers(LinkedList<Worker> workers)
    {
        clear();
        int maxId = 0;
        for (Worker worker : workers)
        {
            if (worker != null && worker.validate())
            {
                try
                {
                    add(worker);
                    maxId = Math.max(maxId, worker.getId());
                }
                catch (DuplicateIdException e)
                {

                }
            }
        }

        Worker.moveNextId(maxId + 1);
    }

    /**
     * Checks if the correctness of a collection.
     * All workers are correct and all ids are different.
     */
    public boolean getValidation()
    {
        for (Worker worker: linkedList)
        {
            if (!worker.validate() || idWorkerFromCollection.get(worker.getId()) != worker)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the collection empty.
     */
    public boolean isEmpty()
    {
        return linkedList.size() == 0;
    }

    /**
     * Prints out information about the collection.
     */
    public void printInfo()
    {
        console.print("Data type: " + linkedList.getClass().getName());
        console.print("Data of initialization: " + creationDate);
        console.print("Number of elements: " + linkedList.size());
    }

    /**
     * Prints out all elements of a collection.
     */
    public void printAllElements()
    {
        if (linkedList.isEmpty())
        {
            console.print("The are no elements in the collection");
            return;
        }

        console.print("Elements of collection: " + linkedList.size());
        linkedList.forEach(worker -> console.print(worker.toString()));
    }

    /**
     * Prints elements out from the collection in ascending order.
     */
    public void printAscending()
    {
        linkedList.stream().sorted(Comparator.naturalOrder()).forEach(worker -> console.print(worker.toString()));
    }

    /**
     * Prints positions of workers and workers are sorted in ascending order.
     */
    public void printFieldAscendingPosition()
    {
        linkedList.stream().sorted(Comparator.naturalOrder()).forEach(worker -> console.print(worker.getSalary().toString()));
    }

    /**
     * Adding worker to collection.
     *
     * @param worker to the collection.
     */
    public void add(Worker worker) throws DuplicateIdException
    {
        if (worker.getId() == 0)
        {
            worker.setId();
        }

        if (existsId(worker.getId()))
        {
            throw new DuplicateIdException();
        }

        idWorkerFromCollection.put(worker.getId(), worker);
        linkedList.add(worker);
    }

    public boolean existsId(long id)
    {
        return idWorkerFromCollection.containsKey(id);
    }

    public void update(long id, Worker worker)
    {
        if (!idWorkerFromCollection.containsKey(id))
        {
            console.print("Нет пользователя с таким id");
            return;
        }

        idWorkerFromCollection.get(id).update(worker);
    }

    public void remove(long id)
    {
        if (!idWorkerFromCollection.containsKey(id))
        {
            console.print("Нет пользователя с таким id");
            return;
        }
        linkedList.remove(idWorkerFromCollection.get(id));
        idWorkerFromCollection.remove(id);
    }

    public void removeGreater(Worker worker)
    {
        for (Worker other : new LinkedList<>(linkedList))
        {
            if (other.compareTo(worker) > 0)
            {
                idWorkerFromCollection.remove(other.getId());
                linkedList.remove(other);
            }
        }
    }

    public void removeLower(Worker worker)
    {
        for (Worker other : new LinkedList<>(linkedList))
        {
            if (other.compareTo(worker) < 0)
            {
                idWorkerFromCollection.remove(other.getId());
                linkedList.remove(other);
            }
        }
    }

    public void clear()
    {
        idWorkerFromCollection.clear();
        linkedList.clear();
    }

    public Worker getHead()
    {
        return linkedList.getFirst();
    }

    public Worker getMinByCreationDate()
    {
        LinkedList<Worker> aWorkers = new LinkedList<>(linkedList);
        Worker min = aWorkers.getFirst();
        for (int i = 0; i < aWorkers.size(); i++)
        {
            if (min.getCreationDate().compareTo(aWorkers.get(i).getCreationDate()) > 0)
            {
                min = aWorkers.get(i);
            }
        }

        return min;
    }

    public LinkedList<Worker> getLinkedList()
    {
        return linkedList;
    }

    public LinkedList<Worker> getCopy()
    {
        LinkedList<Worker> copy = new LinkedList<>(linkedList);
        for (Worker worker : linkedList)
        {
            copy.add(worker);
        }

        return copy;
    }

    public LinkedList<Worker> getFilteredBySubName(String name)
    {
        LinkedList<Worker> filtered = new LinkedList<>();
        for (Worker worker : linkedList)
        {
            if (worker.getName().contains(name))
            {
                filtered.add(worker);
            }
        }

        return filtered;
    }

    public void printFieldDescendingPosition() {
        linkedList.stream().sorted(Comparator.reverseOrder()).forEach(worker ->
                console.print(worker.getPosition() == null ? "null" : worker.getPosition().toString()));
    }

    public LinkedList<Worker> getFilterBySalary(Float salary) {
        return linkedList.stream().filter(worker ->
                (worker.getSalary() == null && salary == null)
                        || (worker.getSalary() != null && worker.getSalary().equals(salary))
        ).collect(Collectors.toCollection(LinkedList::new));
    }

    public void sortByName()
    {
        linkedList = linkedList.stream().sorted(Comparator.comparing(Worker::getName))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}