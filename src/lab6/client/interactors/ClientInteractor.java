package lab6.client.interactors;

import lab6.client.Configuration;
import lab6.main.classes.Worker;
import lab6.main.commands.AbstractCommand;
import lab6.main.consoles.BaseConsole;
import lab6.main.consoles.Console;
import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.exceptions.IncorrectIdInputException;
import lab6.main.exceptions.InputCompleteException;
import lab6.main.exceptions.WorkerCompleteInputException;
import lab6.main.requests.AllCommandsRequest;
import lab6.main.requests.CommandRequest;
import lab6.main.requests.UpdateCollectionHistory;
import lab6.main.requests.ValidationRequest;
import lab6.main.responses.AllCommandsResponse;
import lab6.main.responses.CommandResponse;
import lab6.main.responses.ValidationResponse;

import java.io.IOException;

/**
 * Class is used to supply client side.
 * Receives command from server: sends commands to server and processes the results.
 */
public class ClientInteractor
{
    private static final Console console = new BaseConsole();
    Client client;

    public ClientInteractor()
    {
        client = new Client(Configuration.getHost(), Configuration.getPort());
    }

    public AbstractCommand[] getAllCommands() throws IOException, ClassNotFoundException
    {
        client.start();
        writeGetAllCommandsRequest();
        AllCommandsResponse response = (AllCommandsResponse) client.getObject();
        client.close();
        return response.getCommands();
    }

    public void writeGetAllCommandsRequest() throws IOException
    {
        client.writeObject(new AllCommandsRequest());
    }

    public void writeValidationRequest(AbstractCommand command) throws IOException
    {
        client.writeObject(new ValidationRequest(command));
    }

    public void writeCommandRequest(AbstractCommand command) throws IOException
    {
        client.writeObject(new CommandRequest(command));
    }

    public void writeUpdateCollectionRequest() throws IOException
    {
        client.start();
        client.writeObject(new UpdateCollectionHistory());
        client.close();
    }

    public void commandHandler(InputInteractor inputInteractor, AbstractCommand command) throws IOException, ClassNotFoundException, ClassCastException
    {
        client.start();
        writeValidationRequest(command);
        ValidationResponse response = (ValidationResponse) client.getObject();
        client.close();

        client.start();
        if (!response.getStatus())
        {
            console.print(response.getErrorMessage());
        }
        else
        {
            try
            {
                if (command.isWithWorker())
                {
                    Worker worker = inputInteractor.getWorker();
                    command.setWorker(worker);
                }
            }
            catch (WorkerCompleteInputException | InputCompleteException e)
            {
                client.close();
                return;
            }
            catch (IncorrectCmdArgsException e)
            {
                client.close();
            }
            writeCommandRequest(command);
            CommandResponse cmdResponse = (CommandResponse) client.getObject();
            if (!cmdResponse.getStatus())
            {
                console.print(cmdResponse.getErrorMessage());
            }
            else
            {
                console.print(cmdResponse.getResult());
            }
        }
        client.close();
    }
}