package ashc.load;

import ashc.semantics.Member.Type;
import ashc.semantics.*;

/**
 * Ash
 *
 * @author samtebbs, 20:15:46 - 23 May 2015
 */
public class TypeImporter {

    public static ClassLoader loader = ClassLoader.getSystemClassLoader();

    public static boolean loadClass(final String path) {
	final String shortName = path.substring(path.lastIndexOf('.') + 1);
	// Check if it's already been imported
	if (Semantics.bindingExists(shortName)) return true;
	try {
	    final Class<?> cls = loader.loadClass(path);
	    new Type(cls, path);
	} catch (final ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return true;
    }

}
