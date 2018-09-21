package by.htp.onlinestore.controller;

public enum NameCommands {
	// в одном экземпляре сущ enum
	CREATEBASKET {
		{
			this.command = new CommandCreateBasket();
		}
	},
	EDITBUYER {
		{
			this.command = new CommandEditBuyer();
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

	public Command command;
}
