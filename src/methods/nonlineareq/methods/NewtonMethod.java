package methods.nonlineareq.methods;

import static java.lang.Math.abs;

import java.util.Map;

import methods.nonlineareq.exceptions.ArgumentsException;
import methods.nonlineareq.exceptions.NoSolutionException;
import function.Function;

public class NewtonMethod extends Method{
	private double start_point;

	@Override
	public Solution solve(Function f, Map<String, Object> arguments) throws ArgumentsException, NoSolutionException {

		double p0 =start_point, p1 = 0,y=0;
		for (iterations = 0; iterations < max_iterations; iterations++) {
			p1=p0-(f.getValue(p0)/f.getDerivative().getValue(p0));
			points.add(p0);
			points.add(p1);
			double absolute_error = abs(p1-p0);
			error= 2*absolute_error/(abs(p1+tolerance));
			p0=p1;
			
			y=f.getValue(p1);
			if(error<tolerance | absolute_error<tolerance | abs(y) <epson){
				stopping_reason |= TOLERANCE_REACHED;
				break;
			}
		}

		if(iterations==max_iterations)
			stopping_reason |= MAX_ITERATIONS_REACHED;
		
		return new Solution(y);
	}

	@Override
	public void init(Function f, Map<String, Object> arguments) throws ArgumentsException {
		super.initSuperMethod(f, arguments);
		if (!arguments.containsKey(START_POINT))
			throw new ArgumentsException(String.format("%s Argument of Type Double is missing", start_point));

		if (!arguments.containsKey(MAX_ITERATIONS))
			throw new ArgumentsException(String.format("%s Argument of Type Integer is missing", MAX_ITERATIONS));

		start_point = (Double) arguments.get(START_POINT);
		max_iterations = (Integer) arguments.get(MAX_ITERATIONS);

	}
}
