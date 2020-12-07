import lombok.AllArgsConstructor;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }


    @Value
    public static class AuthInfo {
        String login;
        String password;
    }


    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }


    @Value
    public static class VerificationCode {
        String code;
    }


    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

}


