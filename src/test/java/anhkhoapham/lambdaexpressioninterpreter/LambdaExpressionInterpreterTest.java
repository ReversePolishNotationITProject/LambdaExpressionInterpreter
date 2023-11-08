/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package anhkhoapham.lambdaexpressioninterpreter;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Builders.LambdaTermNodeBuilder;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Builders.LambdaTermNodeBuiltInBuilder;
import anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser.LambdaExpressionExternalTreePorterParser;
import anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser.LambdaExpressionParser;
import static org.junit.Assert.*;

/**
 *
 * @author Khoapa
 */
public class LambdaExpressionInterpreterTest {
    
    private final LambdaExpressionInterpreter interpreter;
    private final LambdaExpressionParser parser;
    private final LambdaTermNodeBuilder builder;
    
    
    public LambdaExpressionInterpreterTest() {

        parser = new LambdaExpressionExternalTreePorterParser();
        builder = LambdaTermNodeBuiltInBuilder.get();
        
        interpreter = new LambdaExpressionClassicInterpreter(parser, builder);
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    /**
     * Test of interpretInt method, of class LambdaExpressionInterpreter.
     */
    @org.junit.Test
    public void testInterpretInt() {
        System.out.println("interpretInt");

        var treeInt3 = parser.parse("/f /x f ( f ( f ( x ) ) )");
        var treeTrue = parser.parse("/a /_ a");
        
        var result1 = interpreter.interpretInt(treeInt3);
        var result2 = interpreter.interpretInt(treeTrue).isEmpty();
        
        assertEquals(3, (int)result1.get());
        assertTrue(result2);
    }

    /**
     * Test of interpretBool method, of class LambdaExpressionInterpreter.
     */
    @org.junit.Test
    public void testInterpretBool() {
        System.out.println("interpretBool");

        var treeTrue = parser.parse("/a /_ a");
        var treeFalse = parser.parse("/_ /b b");
        var treeInt3 = parser.parse("/f /x f ( f ( f ( x ) ) )");
        
        assertEquals(true, interpreter.interpretBool(treeTrue).get());
        assertEquals(false, interpreter.interpretBool(treeFalse).get());       
        assertTrue(interpreter.interpretBool(treeInt3).isEmpty());
    }

    /**
     * Test of interpretChar method, of class LambdaExpressionInterpreter.
     */
    @org.junit.Test
    public void testInterpretChar() {
        System.out.println("interpretChar");

        var treeInt65 = interpreter.translateInt(65);
        
        char result = interpreter.interpretChar(treeInt65).get();
        
        assertEquals('A', result);
    }

    /**
     * Test of translateInt method, of class LambdaExpressionInterpreter.
     */
    @org.junit.Test
    public void testTranslateInt() {
        System.out.println("translateInt");

        var treeInt3 = parser.parse("/f /x f ( f ( f ( x ) ) )");
        
        var translatedTree = interpreter.translateInt(3);
        
        assertEquals(treeInt3, translatedTree);
    }

    /**
     * Test of interpretTuple method, of class LambdaExpressionInterpreter.
     */
    @org.junit.Test
    public void testInterpretTuple() {
        System.out.println("interpretTuple");

        // TODO: Implement test.
    }

    /**
     * Test of interpretList method, of class LambdaExpressionInterpreter.
     */
    @org.junit.Test
    public void testInterpretList() {
        System.out.println("interpretList");

        // TODO: Implement test.
    }    
}
