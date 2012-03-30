package methods.nonlineareq.methods;

import static java.lang.Math.*;
import java.util.Map;

import function.Function;
import methods.nonlineareq.exceptions.ArgumentsException;
import methods.nonlineareq.exceptions.NoSolutionException;

public class FalsePositionMethod extends Method {

	private double start_point, end_point;

	@Override
	public Solution solve(Function f, Map<String, Object> arguments) throws NoSolutionException, ArgumentsException {
		init(f, arguments);
		// TODO : bound error
		double ya = f.getValue(start_point), yb = f.getValue(end_point), a = start_point, b = end_point, c = 0, yc;
		if (ya * yb > 0)
			throw new NoSolutionException("Two bracketing points are on the same side of x-axis");

		for (iterations = 0; iterations < max_iterations; iterations++) {
			points.add(a);
			points.add(b);

			double dx = yb * (b - a) / (yb - ya);
			c = b - dx;
			double ac = c - a;
			yc = f.getValue(c);

			if (Math.abs(yc) < tolerance) {
				stopping_reason |= TOLERANCE_REACHED;
				break;
			} else if (yb * yc > 0) {
				b = c;
				yb = yc;
			} else {
				a = c;
				ya = yc;
			}

			dx = min(abs(dx), ac);
			if (abs(dx) < tolerance || abs(yc) < epson) {
				stopping_reason |= TOLERANCE_REACHED;
				break;
			}
		}

		if (iterations == max_iterations)
			stopping_reason |= MAX_ITERATIONS_REACHED;

		yc = f.getValue(c);
		error = abs(b - a) / 2.0;

		return new Solution(yc);
	}

	@Override
	public void init(Function f, Map<String, Object> arguments) throws ArgumentsException {
		super.initSuperMethod(f, arguments);
		if (!arguments.containsKey(START_POINT))
			throw new ArgumentsException(String.format("%s Argument of Type Double is missing", start_point));

		if (!arguments.containsKey(END_POINT))
			throw new ArgumentsException(String.format("%s Argument of Type Double is missing", end_point));
	
		if (!arguments.containsKey(MAX_ITERATIONS))
			throw new ArgumentsException(String.format("%s Argument of Type Integer is missing", MAX_ITERATIONS));
	
		start_point = (Double) arguments.get(START_POINT);
		end_point = (Double) arguments.get(END_POINT);
		max_iterations = (Integer) arguments.get(MAX_ITERATIONS);

	}

}
