package ashc.main;

import java.io.*;
import java.util.*;

import ashc.codegen.*;
import ashc.codegen.GenNode.GenNodeType;
import ashc.grammar.*;
import ashc.grammar.Node.NodeFile;

/**
 * Ash
 *
 * @author samtebbs, 15:14:41 - 23 May 2015
 */
public class AshCompiler {

    private final Parser parser;
    private final Lexer lexer;
    private NodeFile fileNode;
    public String relFilePath;

    public static Stack<AshCompiler> compilers = new Stack<AshCompiler>();

    public AshCompiler(final String relFilePath) throws FileNotFoundException, IOException {
	this.relFilePath = relFilePath;
	lexer = new Lexer(new BufferedReader(new FileReader(new File(relFilePath))));
	parser = new Parser(lexer);
	compilers.push(this);
    }

    public void parse() {
	fileNode = parser.start();
    }

    public void preAnalyse() {
	if (fileNode != null) fileNode.preAnalyse();
    }

    public void analyse() {
	if (fileNode != null) fileNode.analyse();
    }

    public void generate() {
	if (fileNode != null) {
	    fileNode.generate();
	    for (final GenNodeType type : GenNode.types)
		type.generate(null);
	}
    }

    public static AshCompiler get() {
	return compilers.peek();
    }

}
