/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package anhkhoapham.lambdaexpressioninterpreter;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;
import java.util.Optional;

/**
 *
 * @author Khoapa
 */
public interface LambdaExpressionInterpreter {
    
    Optional<Integer> interpretInt(LambdaTermRoot tree);
    
    Optional<Boolean> interpretBool(LambdaTermRoot tree);
    
    default Optional<Character> interpretChar(LambdaTermRoot tree)
    {
        var result = interpretInt(tree);
        
        if (result == null || result.isEmpty())
        {
            return Optional.empty();
        }
        else
        {
            return Optional.of((char)(result.get().intValue()));
        }
    }
    
    LambdaTermRoot translateInt(int n);
    
    void interpretTuple(LambdaTermRoot tree, StringBuilder buffer);
    
    void interpretList(LambdaTermRoot tree, StringBuilder buffer);
}
