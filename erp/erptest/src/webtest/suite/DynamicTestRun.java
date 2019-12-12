package webtest.suite;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@RunWith(DynamicSuite.class)
public class DynamicTestRun {
	public static Class<?>[] suite(String url) throws IOException {
		List<Class<?>> classList = listMatchingClasses(url);
		return (Class<?>[]) classList.toArray(new Class[classList.size()]);
	}

	public static List<Class<?>> listMatchingClasses(String matchPattern) throws IOException {
		List<Class<?>> classes = new LinkedList<Class<?>>();
		PathMatchingResourcePatternResolver scanner = new PathMatchingResourcePatternResolver();
		Resource[] resources = scanner.getResources(matchPattern);

		for (Resource resource : resources) {
			Class<?> clazz = getClassFromResource(resource);
			classes.add(clazz);
		}
		return classes;
	}

	public static Class<?> getClassFromResource(Resource resource) {
		try {
			String resourceUri = resource.getURI().toString();
			System.out.println(resourceUri);
			if (resourceUri.indexOf("webtest") != -1)
				resourceUri = resourceUri.substring(resourceUri.indexOf("webtest")).replace(".class", "")
						.replace("/", ".");
			return Class.forName(resourceUri);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
