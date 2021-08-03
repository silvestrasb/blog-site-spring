package lt.codeacademy.blog_site.exception;

public class UserNotFoundException extends RuntimeException{

    private final String username;
    public UserNotFoundException(String username) {
        this.username = username;
    }

}
