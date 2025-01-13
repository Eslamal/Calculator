package com.example.calculator;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Parameters;

import java.util.Iterator;

/**
 * A subclass of DoubleEvaluator that supports additional mathematical functions.
 */
public class ExtendedDoubleEvaluator extends DoubleEvaluator {
    /** Defines new functions. */
    private static final Function SQRT = new Function("sqrt", 1);
    private static final Function CBRT = new Function("cbrt", 1);
    private static final Function ASIN_DEG = new Function("asin_deg", 1);
    private static final Function ACOS_DEG = new Function("acos_deg", 1);
    private static final Function ATAN_DEG = new Function("atan_deg", 1);

    /** Parameters object for the evaluator. */
    private static final Parameters PARAMS;

    static {
        // Gets the default DoubleEvaluator's parameters
        PARAMS = DoubleEvaluator.getDefaultParameters();
        // Add the new functions to the parameters
        PARAMS.add(SQRT);
        PARAMS.add(CBRT);
        PARAMS.add(ASIN_DEG);
        PARAMS.add(ACOS_DEG);
        PARAMS.add(ATAN_DEG);
    }

    /**
     * Constructor for ExtendedDoubleEvaluator.
     */
    public ExtendedDoubleEvaluator() {
        super(PARAMS);
    }

    /**
     * Evaluates the custom functions or delegates to the parent evaluator.
     *
     * @param function           The function to evaluate.
     * @param arguments          Iterator over the function's arguments.
     * @param evaluationContext  Context for evaluation (can be null).
     * @return Result of the evaluation.
     */
    @Override
    public Double evaluate(Function function, Iterator<Double> arguments, Object evaluationContext) {
        if (function == SQRT) {
            double arg = arguments.next();
            if (arg < 0) {
                throw new IllegalArgumentException("Square root of a negative number is not allowed.");
            }
            return Math.sqrt(arg);
        } else if (function == CBRT) {
            return Math.cbrt(arguments.next());
        } else if (function == ASIN_DEG) {
            double arg = arguments.next();
            if (arg < -1 || arg > 1) {
                throw new IllegalArgumentException("asin_deg accepts values only in the range [-1, 1].");
            }
            return Math.toDegrees(Math.asin(arg));
        } else if (function == ACOS_DEG) {
            double arg = arguments.next();
            if (arg < -1 || arg > 1) {
                throw new IllegalArgumentException("acos_deg accepts values only in the range [-1, 1].");
            }
            return Math.toDegrees(Math.acos(arg));
        } else if (function == ATAN_DEG) {
            return Math.toDegrees(Math.atan(arguments.next()));
        } else {
            // If it's another function, pass it to DoubleEvaluator
            return super.evaluate(function, arguments, evaluationContext);
        }
    }
}
