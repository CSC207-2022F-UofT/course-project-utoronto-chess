package useCases.database;
import entities.user.User;

import java.sql.SQLException;

public interface SQLGateway {

    void addUser(User user) throws SQLException;

    boolean lookupUsername(String username) throws SQLException;

    boolean checkCredentials(User user) throws SQLException;

    int lookupELO(String username) throws SQLException;

    void updateELO(String username, int elo) throws SQLException;

}
