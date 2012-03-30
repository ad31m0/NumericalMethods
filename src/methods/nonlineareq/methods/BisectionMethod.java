package methods.nonlineareq.methods;

import java.util.Map;
import static java.lang.Math.*;

import function.Function;
import methods.nonlineareq.exceptions.ArgumentsException;
import methods.nonlineareq.exceptions.NoSolutionException;

public class BisectionMethod extends Method {

	private double start_point, end_point;

	@Override
	public Solution solve(Function f, Map<String, Object> arguments) throws NoSolutionException, ArgumentsException {
		init(f, arguments);
		// TODO : bound error
		double ya = f.getValue(start_point), yb = f.getValue(end_point), a = start_point, b = end_point, c, yc;
		if (ya * yb > 0)
			throw new NoSolutionException("Two bracketing points are on the same side of x-axis");

		max_iterations = (int) (1 + round((log(end_point - start_point) - log(tolerance)) / log(2)));

		for (iterations = 0; iterations < max_iterations; iterations++) {
			points.add(a);
			points.add(b);
			c = (a + b) / 2.0;
			yc = f.getValue(c);
			if (Math.abs(yc) < tolerance) {
				a = b = c;
			} else if (yb * yc > 0) {
				b = c;
				yb = yc;
			} else {
				a = c;
				ya = yc;
			}
			if (abs(a - b) <= tolerance) {
				stopping_reason |= TOLERANCE_REACHED;
				break;
			}
		}

		if (iterations == max_iterations)
			stopping_reason |= MAX_ITERATIONS_REACHED;

		c = (a + b) / 2.0;
		yc = f.getValue(c);
		error = abs(a - b);

		return new Solution(c);
	}

	@Override
	public void init(Function f, Map<String, Object> arguments) throws ArgumentsException {
		super.initSuperMethod(f, arguments);
		if (!arguments.containsKey(START_POINT))
			throw new ArgumentsException(String.format("%s Argument of Type Double is missing", start_point));

		if (!arguments.containsKey(END_POINT))
			throw new ArgumentsException(String.format("%s Argument of Type Double is missing", end_point));

		start_point = (Double) arguments.get(START_POINT);
		end_point = (Double) arguments.get(END_POINT);

	}
}
