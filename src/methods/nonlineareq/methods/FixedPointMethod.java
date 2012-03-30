package methods.nonlineareq.methods;

import java.util.Map;

import function.Function;
import methods.nonlineareq.exceptions.ArgumentsException;
import methods.nonlineareq.exceptions.NoSolutionException;

public class FixedPointMethod extends Method {

	private double start_point;

	@Override
	public Solution solve(Function f, Map<String, Object> arguments) throws ArgumentsException, NoSolutionException {

		init(f, arguments); // init arguments.

		// TODO : check if there is a solution .
		// TODO : bound error.

		points.add(start_point);
		for (iterations = 1; iterations < max_iterations; iterations++) {
			points.add(f.getValue(points.get(iterations - 1)));
			double absoluteError = Math.abs(points.get(iterations) - points.get(iterations - 1));
			error = absoluteError / (points.get(iterations) + epson);
			if (error <= tolerance || absoluteError <= tolerance) {
				stopping_reason |= TOLERANCE_REACHED;
				break;
			}
		}

		if (iterations == max_iterations)
			stopping_reason |= MAX_ITERATIONS_REACHED;

		if (points.isEmpty())
			throw new NoSolutionException("No Points Were Generated");

		return new Solution(points.get(points.size() - 1));
	}

	@Override
	public void init(Function f, Map<String, Object> arguments) throws ArgumentsException {
		super.initSuperMethod(f, arguments);
		if (!arguments.containsKey(MAX_ITERATIONS))
			throw new ArgumentsException(String.format("%s Argument of Type Integer is missing", MAX_ITERATIONS));
		if (!arguments.containsKey(START_POINT))
			throw new ArgumentsException(String.format("%s Argument of Type Double is missing", start_point));
		start_point = (Double) arguments.get(START_POINT);
		max_iterations = (Integer) arguments.get(MAX_ITERATIONS);
	}

}
