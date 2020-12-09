package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

import static ru.netology.data.DataHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerification(verificationCode);

    }

    @Test
    @Order(1)
    void shouldTransferMoneyFromSecondToFirstCard() {
        val dashboardPage = new DashboardPage();
        val amount = 1500;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val transferInfo = getSecondCardNumber();
        val transferPage = dashboardPage.firstCardDepositClick();
        transferPage.transfer(transferInfo, amount);
        val FirstCardBalanceAfterSend = getCardBalanceAfterGetMoney(firstCardBalance, amount);
        val SecondCardBalanceAfterSend = getCardBalanceAfterTransferMoney(secondCardBalance, amount);
        val finalBalanceOfFirstCard = dashboardPage.getFirstCardBalance();
        val finalBalanceOfSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(FirstCardBalanceAfterSend, finalBalanceOfFirstCard);
        assertEquals(SecondCardBalanceAfterSend, finalBalanceOfSecondCard);
    }

    @Test
    @Order(2)
    void shouldTransferMoneyFromFirstToSecondCard() {
        val dashboardPage = new DashboardPage();
        val amount = 2000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val transferInfo = getFirstCardNumber();
        val transferPage = dashboardPage.secondCardDepositClick();
        transferPage.transfer(transferInfo, amount);
        val FirstCardBalanceAfterSend = getCardBalanceAfterTransferMoney(firstCardBalance, amount);
        val SecondCardBalanceAfterSend = getCardBalanceAfterGetMoney(secondCardBalance, amount);
        val finalBalanceOfFirstCard = dashboardPage.getFirstCardBalance();
        val finalBalanceOfSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(FirstCardBalanceAfterSend, finalBalanceOfFirstCard);
        assertEquals(SecondCardBalanceAfterSend, finalBalanceOfSecondCard);
    }

    @Test
    @Order(3)
    void shouldTransferAllMoney() {
        val dashboardPage = new DashboardPage();
        val amount = dashboardPage.getSecondCardBalance();
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val transferInfo = getSecondCardNumber();
        val transferPage = dashboardPage.firstCardDepositClick();
        transferPage.transfer(transferInfo, amount);
        val FirstCardBalanceAfterSend = getCardBalanceAfterGetMoney(firstCardBalance, amount);
        val SecondCardBalanceAfterSend = getCardBalanceAfterTransferMoney(secondCardBalance, amount);
        val finalBalanceOfFirstCard = dashboardPage.getFirstCardBalance();
        val finalBalanceOfSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(FirstCardBalanceAfterSend, finalBalanceOfFirstCard);
        assertEquals(SecondCardBalanceAfterSend, finalBalanceOfSecondCard);
    }

    @Test
    @Order(4)
    void shouldErrorMessageWhenNotEnoughMoneyForTransfer() {
        val dashboardPage = new DashboardPage();
        val amount = dashboardPage.getFirstCardBalance() + 50000;
        val transferPage = dashboardPage.secondCardDepositClick();
        val transferInfo = getFirstCardNumber();
        transferPage.transfer(transferInfo, amount);
        transferPage.showErrorMessage();
    }

    @Test
    @Order(5)
    void shouldReturnToDashboardPageIfClickCancel() {
        val dashboardPage = new DashboardPage();
        int amount = 2000;
        val transferPage = dashboardPage.firstCardDepositClick();
        val transferInfo = getFirstCardNumber();
        transferPage.transferCancel(transferInfo, amount);
        dashboardPage.displayDashboardPageMessage();
    }
}



