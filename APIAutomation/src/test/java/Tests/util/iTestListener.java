package Tests.util;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;


public class iTestListener  implements ITestListener
{

    @Override
    public void onStart(ITestContext Results)
    {
        System.out.println("Started FAB  API Automation");

    }

    @Override
    public void onFinish(ITestContext Results)
    {

        System.out.println("Ended FAB  API Automation");
    }

    public void onTestFailure(ITestResult Result)
    {

        System.out.println("The name of the testcase failed is :"+Result.getName());


        //   AppiumDriver<MobileElement> driver=Base_class.
    }

    @Override
    public void onTestSkipped(ITestResult Result)
    {
        System.out.println("The name of the testcase Skipped is :"+Result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Success PErceNTage");
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result)
    {

        System.out.println("                                                  * * * *  Starting test * * * *");
    }
    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult Result)
    {
        System.out.println("                                                     * * * *  End test * * * *");
    }


}