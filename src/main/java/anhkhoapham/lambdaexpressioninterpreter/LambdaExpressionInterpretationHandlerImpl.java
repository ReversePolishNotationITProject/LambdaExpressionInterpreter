/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anhkhoapham.lambdaexpressioninterpreter;

import anhkhoapham.lambdacalculus.LambdaExpressionTree.Root.LambdaTermRoot;

/**
 *
 * @author Khoapa
 */
public final class LambdaExpressionInterpretationHandlerImpl implements LambdaExpressionInterpretationHandler {

    public LambdaExpressionInterpretationHandlerImpl(LambdaExpressionInterpreter interpreter) {
        if (interpreter == null) throw new IllegalArgumentException("intepreter is null.");
        
        this.interpreter = interpreter;
    }

    private final LambdaExpressionInterpreter interpreter;
    
    @Override
    public String interpret(String targetType, LambdaTermRoot tree) {
               
        if (null != targetType) {
            switch (targetType) {
                case "int" ->  {
                    var interpretation = interpreter.interpretInt(tree);
                    if (interpretation.isPresent()) {
                        return interpretation.get().toString();
                    }
                    else
                        return "Not an instance of " + targetType + ".";
                }
                case "bool" ->  {
                    var interpretation = interpreter.interpretBool(tree);
                    if (interpretation.isPresent()) {
                        return interpretation.get().toString();
                    }
                    else
                        return "Not an instance of " + targetType + ".";
                }
                case "char" ->  {
                    var interpretation = interpreter.interpretChar(tree);
                    if (interpretation.isPresent()) {
                        return interpretation.get().toString();
                    }
                    else
                        return "Not an instance of " + targetType + ".";
                }
            }
        }

        return targetType + " is not supported.";
    }
}
