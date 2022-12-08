package controller.database;
import entities.user.User;

public interface DatabaseGateway {

    void addUser(User user) throws RuntimeException;

    boolean lookupUsername(String username) throws RuntimeException;

    boolean checkCredentials(User user) throws RuntimeException;

    int lookupELO(String username) throws RuntimeException;

    void updateELO(String username, int elo) throws RuntimeException;

    void deleteUser(String username) throws RuntimeException;
}
