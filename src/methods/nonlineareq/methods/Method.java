package methods.nonlineareq.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import methods.nonlineareq.EquationsSolver;
import methods.nonlineareq.exceptions.ArgumentsException;
import methods.nonlineareq.exceptions.NoSolutionException;
import function.Function;

public abstract class Method {

	protected static final double epson = 0.0000000001;
	protected int iterations;
	protected double error;
	protected List<Double> points;

	protected double tolerance;
	protected int max_iterations;

	protected int stopping_reason;

	public abstract Solution solve(Function f, Map<String, Object> arguments)
			throws ArgumentsException, NoSolutionException;

	public abstract void init(Function f, Map<String, Object> arguments)
			throws ArgumentsException;

	protected void initSuperMethod(Function f, Map<String, Object> arguments)
			throws ArgumentsException {
		if (!arguments.containsKey(TOLERANCE))
			throw new ArgumentsException(String.format(
					"%s Argument of Type Double is missing", TOLERANCE));

		this.tolerance = (Double) arguments.get(TOLERANCE);
		this.points = new ArrayList<Double>();
	}

	/**
	 * TODO do doc
	 * 
	 * @author Belbesy
	 * 
	 */
	public class Solution {
		public final int iterations, stopping_reason;
		public final double error, solution;
		public final List<Double> points;

		public Solution(double solution) {
			this.solution = solution;
			this.stopping_reason = Method.this.stopping_reason;
			this.iterations = Method.this.iterations;
			this.error = Method.this.error;
			this.points = Method.this.points;
		}
	}

	/**
	 * Solves the equations with the fixed point method
	 * 
	 * @see {@link EquationsSolver#solve(Function, int)}
	 * @see <a
	 *      href="http://en.wikipedia.org/wiki/Fixed-point_iteration">Wikipedia
	 *      : Fixed Point</a>
	 * @see {@link FixedPointMethod}
	 * @author Belbesy
	 */
	public static final int FIXED_POINT_METHOD = 0;
	/**
	 * Solves the equations with the bisection method
	 * 
	 * @see {@link EquationsSolver#solve(Function, int)}
	 * @see <a href="http://en.wikipedia.org/wiki/Bisection_method">Wikipedia :
	 *      Bisection Method</a>
	 * @see {@link BisectionMethod}
	 * @author Belbesy
	 */
	public static final int BISECTION_METHOD = 1;
	/**
	 * Solves the equations with the false position method
	 * 
	 * @see {@link EquationsSolver#solve(Function, int)}
	 * @see <a
	 *      href="http://en.wikipedia.org/wiki/False_position_method">Wikipedia
	 *      : False Position</a>
	 * @see {@link FalsePositionMethod}
	 * @author Belbesy
	 */
	public static final int FALSE_POSITION_METHOD = 2;

	public static final int SECANT_METHOD = 3;
	public static final int NEWTON_METHOD = 4;

	public static final String TOLERANCE = "TOLERANCE";
	public static final String MAX_ITERATIONS = "MAX_ITERATIONS";

	public static final String START_POINT = "START_POINT";
	public static final String END_POINT = "END_POINT";

	public static final int MAX_ITERATIONS_REACHED = Integer.parseInt("10", 2);
	public static final int TOLERANCE_REACHED = Integer.parseInt("01", 2);

}
