package by.htp.onlinestore.controller;

/**
 * ENUM class contains instances of commands classes
 * and exists in a single copy
 * @author Iryna Siandzetskaya
 *
 */
public enum NameCommands {
	
	HOME {
		{
			this.command = new CommandHome();
		}
	},CREATEBASKET {
		{
			this.command = new CommandCreateBasket();
		}
	},
	EDITBUYER {
		{
			this.command = new CommandEditBuyer();
		}
	},
	CREATEGOOD {
		{
			this.command = new CommandCreateGood();
		}
	},
	LOGIN {
		{
			this.command = new CommandLogin();
		}
	},
	LOGOUT {
		{
			this.command = new CommandLogout();
		}
	},
	ERROR {
		{
			this.command = new CommandError();
		}
	},
	INDEX {
		{
			this.command = new CommandIndex();
		}
	},
	SIGNUP {
		{
			this.command = new CommandSignup();
		}
	},
	PROFILE {
		{
			this.command = new CommandProfile();
		}
	},
	CONFIRMORDER {
		{
			this.command = new CommandConfirmOrder();
		}
	},
	CHANGELOCALE {
		{
			this.command = new CommandChangeLocale();
		}
	};

	/**
	 * declares a command
	 */
	public Command command;
}
