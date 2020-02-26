import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TodoTests extends BaseTest{
    @Test
    public void createTodoTask() {
        String new_task_name = "Test ToDo";

        //Create a New To Do Task
        By ADD_TASK_BTN = By.id(APP_PACKAGE+":id/fab_add_task");
        By NEW_TASK_TITLE = By.id(APP_PACKAGE+":id/add_task_title");
        By NEW_TASK_DESC = By.id(APP_PACKAGE+":id/add_task_description");
        By DONE_BTN = By.id(APP_PACKAGE+":id/fab_edit_task_done");

        waitForElement(ADD_TASK_BTN).click();
        waitForElement(NEW_TASK_TITLE).sendKeys(new_task_name);
        waitForElement(NEW_TASK_DESC).sendKeys("Testing ToDo App");
        waitForElement(DONE_BTN).click();

        //Check after created To Do Task
        By TASK_TITLE = By.id(APP_PACKAGE+":id/title");
        List<WebElement> allTasks = waitForElements(TASK_TITLE);

        boolean taskDisplayed = false;
        for (WebElement element : allTasks) {
            if (element.getText().equals(new_task_name))
                taskDisplayed = true;
        }

        Assert.assertTrue("'" + new_task_name + "' task is cannot displayed.", taskDisplayed);
    }

    public MobileElement waitForElement(By by){
        return (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitForElements(By by){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
}
