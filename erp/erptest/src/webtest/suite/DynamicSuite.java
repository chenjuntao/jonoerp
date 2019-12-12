package webtest.suite;

import java.io.IOException;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

public class DynamicSuite extends Suite {
	public DynamicSuite(Class<?> setupClass, String url) throws InitializationError, IOException {
		super(setupClass, DynamicTestRun.suite(url));
	}
}
