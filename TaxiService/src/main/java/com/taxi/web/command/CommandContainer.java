package com.taxi.web.command;



import java.util.Map;
import java.util.TreeMap;



public class CommandContainer {
	
	//private static final Logger log = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();//TREE??
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		
		commands.put("register", new RegisterCommand());
		commands.put("lang", new LangCommand());
		
		//	commands.put("noCommand", new NoCommand());
		// client commands
		commands.put("account", new AccountCommand());
		commands.put("ride", new RideCommand());
		commands.put("rideDetails", new RideDetailsCommand());
		commands.put("rideConfirmed", new RideConfirmedCommand());
		commands.put("adminPage", new AdminPageCommand());
		commands.put("showRides", new ShowRidesCommand());
		
		// admin commands
		//commands.put("listOrders", new ListOrdersCommand());
		
		//log.debug("Command container was successfully initialized");
		//log.trace("Number of commands --> " + commands.size());
	}

	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			//log.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}
		
		return commands.get(commandName);
	}
	
}