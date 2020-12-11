package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");
    private ElementsCollection depositButton = $$("[data-test-id='action-deposit']");


    public TransferPage firstCardDepositClick() {
        depositButton.first().click();
        return new TransferPage();
    }

    public TransferPage secondCardDepositClick() {
        depositButton.last().click();
        return new TransferPage();
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        String cardBalance = text.substring(29, text.indexOf(" ", 29));
        return Integer.parseInt(cardBalance);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        String cardBalance = text.substring(29, text.indexOf(" ", 29));
        return Integer.parseInt(cardBalance);
    }

    public SelenideElement displayDashboardPageMessage() {
        return $("[data-test-id='dashboard']").shouldHave(Condition.exactText("Личный кабинет"));
    }
}