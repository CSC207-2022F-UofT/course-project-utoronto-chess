package user.loginregistration;

public interface UserRegistrationModel {

    public User registerRequest(String username, String password1, String password2) throws IllegalArgumentException;

}
