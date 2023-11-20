/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdaexpressioninterpreter;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Builders.LambdaTermNodeBuilder;
import anhkhoapham.lambdacalculus.LambdaExpressionTree.Nodes.LambdaTermUnfilledExpressionNode;
import anhkhoapham.lambdacalculus.LambdaExpressonTree.Parser.LambdaExpressionParser;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Khoapa
 */
public final class LambdaExpressionClassicInterpreter implements LambdaExpressionInterpreter {

    
    private final LambdaTermRoot identity;
    private final LambdaTermRoot two;
    private final LambdaTermRoot complex;
    private final LambdaTermNodeBuilder builder;
    
    private final LambdaTermUnfilledExpressionNode namedChildlessX;
    
    public LambdaExpressionClassicInterpreter(LambdaExpressionParser parser, LambdaTermNodeBuilder builder) {
        
        if (builder == null) throw new IllegalArgumentException("builder is null");
        
        identity = parser.parse("/id id");
        complex = parser.parse("/b /c ( /i i ) b c ( c b ( /_ c ) )");
        two = parser.parse("/f /x f ( f ( x ) )");
        this.builder = builder;
        
        namedChildlessX = builder.buildNamedNode("x", List.of());
    }
    
    @Override
    public Optional<Integer> interpretInt(LambdaTermRoot tree) {        
        
        AtomicInteger counter = new AtomicInteger();
        
        LambdaTermRoot f = new LambdaTermRoot()
        {   
            @Override
            public String displayName() {
                return identity.displayName();
            }

            @Override
            public LambdaTermExpressionNode topNode() {
                return identity.topNode();
            }

            @Override
            public LambdaTermRoot substitute(LambdaTermRoot visitingRoot, String substitutedName) {
                return identity.substitute(visitingRoot, substitutedName);
            }

            @Override
            public LambdaTermRoot invoke(LambdaTermRoot input) {
                
                counter.incrementAndGet();
                
                return input;
            }

            @Override
            public void print(StringBuilder buffer, String prefix, String childrenPrefix) {
                identity.print(buffer, prefix, childrenPrefix);
            }   
        };
                
        LambdaTermRoot result = tree.invokeAll(f, identity);
        
        if (result.equals(identity))
        {
            return Optional.of(counter.get());
        }
        
        else return Optional.empty();
    }

    @Override
    public Optional<Boolean> interpretBool(LambdaTermRoot tree) {
        
        var result = tree.invokeAll(two, complex);

        if (result.equals(two)) {
            return Optional.of(true);
        } else if (result.equals(complex)) {
            return Optional.of(false);
        } else
            return Optional.empty();
    }

    @Override
    public void interpretTuple(LambdaTermRoot tree, StringBuilder buffer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void interpretList(LambdaTermRoot tree, StringBuilder buffer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LambdaTermRoot translateInt(int n) {
        var topNode = buildIntBody(namedChildlessX, n);
        
        var rootX = builder.buildRoot("x", topNode);
        var nodeX = builder.buildOpenNode(rootX, List.of());
        return builder.buildRoot("f", nodeX);
    }
    
    private LambdaTermExpressionNode buildIntBody(LambdaTermExpressionNode currentNode, int nodesToBuild)
    {        
        if (nodesToBuild > 0) {
            var newNode = builder.buildNamedNode("f", List.of(currentNode));
            return buildIntBody(newNode, nodesToBuild - 1);
        } else {
            return currentNode;
        }
    }
}
