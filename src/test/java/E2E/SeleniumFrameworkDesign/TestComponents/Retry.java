package E2E.SeleniumFrameworkDesign.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int count = 0;
	int maxRetry = 1; // maximum rerun

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub

		if (count < maxRetry) {
			count++;
			return true;
		}

		return false;
	}

}
