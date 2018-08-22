package by.htp.onlinestore.controller;

public enum Actions {
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
	RESETDB {
		{
			this.command = new CommandResetDB();
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
	};

	public Action command;
}
