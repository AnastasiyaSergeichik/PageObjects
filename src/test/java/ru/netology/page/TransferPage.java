package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement amountInput = $("[data-test-id = amount] input");
    private final SelenideElement fromCardInput = $("[data-test-id = from] input");
    private final SelenideElement transferButton = $("[data-test-id = action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");


    public void transfer(DataHelper.TransferInfo transferInfo, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromCardInput.setValue(transferInfo.getCardNumber());
        transferButton.click();
        new DashboardPage();
    }

    public void transferCancel(DataHelper.TransferInfo transferInfo, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromCardInput.setValue(transferInfo.getCardNumber());
        cancelButton.click();
    }

    public SelenideElement showErrorMessage() {
        return $(withText("Недостаточно средств на карте")).shouldBe(Condition.visible);
    }
}