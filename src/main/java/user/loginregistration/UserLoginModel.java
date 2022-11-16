package user.loginregistration;

public interface UserLoginModel {

    public User loginRequest(String username, String password) throws IllegalArgumentException;

    public boolean logoutRequest(String username);

}
