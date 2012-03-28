package methods.nonlineareq;

import java.util.Map;

import methods.nonlineareq.exceptions.ArgumentsException;
import methods.nonlineareq.exceptions.MethodException;
import methods.nonlineareq.exceptions.MethodNotSupportedException;
import methods.nonlineareq.methods.BisectionMethod;
import methods.nonlineareq.methods.FalsePositionMethod;
import methods.nonlineareq.methods.FixedPointMethod;
import methods.nonlineareq.methods.Method;
import methods.nonlineareq.methods.Method.Solution;
import function.Function;

public class EquationsSolver {

	/**
	 * array of methods to use in {@link #solve(Function, int)}
	 * 
	 * @author Belbesy
	 */
	public static final Method[] methods = { new FixedPointMethod(), new BisectionMethod(), new FalsePositionMethod() };

	
	/**
	 * 
	 * @param f
	 * <br/>
	 *            Function to solve
	 * @param method
	 * <br/>
	 *            can be Either :
	 *            <ul>
	 *            <li>{@link Method#FIXED_POINT}</li>
	 *            <li>{@link Method#BISECTION}</li>
	 *            <li>{@link Method#FALSE_POSITION}</li>
	 *            </ul>
	 * @return returns a {@link Solution} Object
	 * @throws {@link MethodException}, {@link MethodNotSupportedException}, {@link ArgumentsException}
	 * @author Belbesy
	 *  
	 */
	
	public static Solution solve(Function f, Map<String, Object> args, int method) throws MethodException, MethodNotSupportedException, ArgumentsException {
		
		// check if given method is a valid one
		if (method < 0 || method >= methods.length)
			throw new MethodNotSupportedException();
		// use the statically initialized methods
		return methods[method].solve(f,args);
	}

}
