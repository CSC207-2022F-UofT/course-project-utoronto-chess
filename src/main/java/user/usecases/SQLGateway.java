package user.usecases;
import user.entities.User;

import java.sql.SQLException;

public interface SQLGateway {

    boolean addUser(User user) throws SQLException;

    boolean lookupUsername(String username) throws SQLException;

    boolean checkCredentials(User user) throws SQLException;

    int lookupELO(String username) throws SQLException;

}
