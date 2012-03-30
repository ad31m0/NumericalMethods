package methods.nonlineareq.methods;

import java.util.Map;

import methods.nonlineareq.exceptions.ArgumentsException;
import methods.nonlineareq.exceptions.NoSolutionException;
import function.Function;
import static java.lang.Math.*;

public class SecantMethod extends Method {
	private double start_point, end_point;

	@Override
	public Solution solve(Function f, Map<String, Object> arguments) throws ArgumentsException, NoSolutionException {
		init(f, arguments);
		double p0 = start_point, p1 = end_point, p2 = 0,y=0;
		for (iterations = 0; iterations < max_iterations; iterations++) {
			points.add(p0);
			points.add(p1);
			p2 = p1 - (f.getValue(p1) * (p1 - p0)) / (f.getValue(p1) - f.getValue(p0));
			double absolute_error = abs(p2-p1);
			error = 2*absolute_error/(abs(p2)-tolerance);
			
			p0=p1;
			p1=p2;
			y=f.getValue(p1);
			if(error<tolerance | absolute_error<tolerance | abs(y) <epson){
				stopping_reason |= TOLERANCE_REACHED;
				break;
			}
			
		}

		if(iterations==max_iterations)
			stopping_reason |= MAX_ITERATIONS_REACHED;
		
		return new Solution(p0);
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
