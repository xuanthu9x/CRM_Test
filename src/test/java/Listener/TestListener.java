package Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext result) {
        System.out.println("Setup môi trường onStart: " + result.getStartDate());

    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("Kết thúc bộ test: " + result.getEndDate());

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Bắt đầu chạy test case: " + result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test case " + result.getName() + " is passed.");
        System.out.println("==> Status: " + result.getStatus());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case " + result.getName() + " is failed.");
        System.out.println("==> Status: " + result.getStatus());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test case " + result.getName() + " is skipped.");
        System.out.println("==> Status: " + result.getStatus());

    }
}