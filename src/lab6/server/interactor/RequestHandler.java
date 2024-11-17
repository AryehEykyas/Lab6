package lab6.server.interactor;

import lab6.main.consoles.StringConsole;
import lab6.main.exceptions.IncorrectCmdArgsException;
import lab6.main.exceptions.IncorrectIdInputException;
import lab6.main.requests.CommandRequest;
import lab6.main.requests.Request;
import lab6.main.requests.UpdateCollectionHistory;
import lab6.main.requests.ValidationRequest;
import lab6.main.responses.*;
import lab6.server.commands.ServerCommand;

/**
 * Class that is used for processing requests from server.
 */
public class RequestHandler
{
    private final CommandInteractor commandInteractor;

    public RequestHandler(CommandInteractor commandInteractor)
    {
        this.commandInteractor = commandInteractor;
    }

    public Response requestHandler(Request request)
    {
        if (request instanceof CommandRequest)
        {
            return new AllCommandsResponse(CommandInteractor.getAbstractCommands());
        }
        else if (request instanceof UpdateCollectionHistory)
        {
            UpdateCollectionHistoryResponses response = new UpdateCollectionHistoryResponses();
            commandInteractor.addStateCollection();
            return response;
        }
        else if (request instanceof ValidationRequest)
        {
            ServerCommand command = CommandInteractor.getServerCommandFromAbstractCommand(((ValidationRequest) request).getCommand());
            ValidationResponse response = new ValidationResponse();

            try
            {
                commandInteractor.serverValidateCommand(command);
                response.setStatus(true);
            }
            catch (IncorrectIdInputException | IncorrectCmdArgsException e)
            {
                response.setStatus(false);
                response.setErrorMessage(e.toString());
            }
            return response;
        }

        else
        {
            ServerCommand command = CommandInteractor.getServerCommandFromAbstractCommand(((CommandRequest) request).getCommand());
            CommandResponse response = new CommandResponse();

            try
            {
                commandInteractor.serverValidateCommand((ServerCommand) command);
                StringConsole strConsole = new StringConsole();

                commandInteractor.getCollectionInteractor().sortByName();
                commandInteractor.getCollectionInteractor().setConsole(strConsole);

                commandInteractor.executeCommand((ServerCommand) command, strConsole);
                response.setStatus(true);
                response.setErrorMessage(strConsole.getText());
            }
            catch (IncorrectIdInputException | IncorrectCmdArgsException e)
            {
                response.setStatus(false);
                response.setErrorMessage(e.toString());
            }
            return response;
        }
    }
}