package ashc.main;

import java.io.*;

import ashc.error.*;
import ashc.load.*;

/**
 * Grammar
 *
 * @author samtebbs, 19:52:26 - 20 May 2015
 */
public class AshMain {

    public static void main(final String[] args) throws FileNotFoundException, IOException {
	TypeImporter.loadClass("java.lang.String");
	TypeImporter.loadClass("java.lang.Object");
	TypeImporter.loadClass("java.lang.System");
	final AshCompiler compiler = new AshCompiler(args[0]);
	compiler.parse();
	compiler.preAnalyse();
	compiler.analyse();
	if (AshError.numErrors == 0) compiler.generate();
	System.exit(AshError.numErrors == 0 ? 0 : 1);
    }

}
