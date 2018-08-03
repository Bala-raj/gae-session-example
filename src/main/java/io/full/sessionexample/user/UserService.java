package io.full.sessionexample.user;

import java.util.Optional;

public class UserService {

	public Optional<User> checkCredential(String login, String pwd) {
		switch (login+pwd) {
		case "bala@full.iobala@123":
			return Optional.of(new User(1, "Bala", "bala@full.io"));
		case "dunston@full.iotest":
			return Optional.of(new User(2, "Dunston	", "dunston@full.io	"));
		case "sabitha@full.iotest":
			return Optional.of(new User(3, "Sabitha", "sabitha@full.io"));
		default:
			return Optional.empty();
		}
	}
}