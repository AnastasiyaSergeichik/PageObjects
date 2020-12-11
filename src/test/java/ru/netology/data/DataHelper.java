package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.val;
import ru.netology.page.DashboardPage;

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
        private String verificationCode;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {

        return new VerificationCode("12345");
    }

    @Value
    @AllArgsConstructor
    public static class TransferInfo {
        private String cardNumber;
        private String cardBalance;
    }

    public static TransferInfo getFirstCardNumber() {
        return new TransferInfo("5559 0000 0000 0001", "10000");
    }

    public static TransferInfo getSecondCardNumber() {
        return new TransferInfo("5559 0000 0000 0002", "10000");
    }

    public static int getCardBalanceAfterGetMoney(int balance, int amount) {
        int totalBalance = balance + amount;
        return totalBalance;
    }

    public static int getCardBalanceAfterTransferMoney(int balance, int amount) {
        int totalBalance = balance - amount;
        return totalBalance;
    }
}


