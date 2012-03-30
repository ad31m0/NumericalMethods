package test;

import java.util.HashMap;
import java.util.Map;

import methods.nonlineareq.EquationsSolver;
import methods.nonlineareq.methods.Method;

import function.Function;

public class TestMethods {
	public static void main(String[] args) {
		// Suppose that the function was created using the parser.
		Function f = null;

		/*
		 * map to pass for the solve method to add arguments applies the Bundle
		 * design pattern to ensure encapsulation you should use the constants
		 * inside method class
		 */
		Map<String, Object> arguments_bundle = new HashMap<String, Object>();

		// Fixed Point;
		arguments_bundle.put(Method.MAX_ITERATIONS, 20); // 20 max iterations
		arguments_bundle.put(Method.TOLERANCE, 0.001); 	 // tolerance
		arguments_bundle.put(Method.START_POINT, 2.3); 	 // the value for p0

		try {
			EquationsSolver.solve(f, arguments_bundle, Method.FIXED_POINT);
		} catch (Exception e) {
			System.out.println("can't be solved");

		}

		// Bisection method;
		arguments_bundle.clear();
		arguments_bundle.put(Method.MAX_ITERATIONS, 20); // 20 max iterations
		arguments_bundle.put(Method.TOLERANCE, 0.001);   // tolerance
		arguments_bundle.put(Method.START_POINT, 2.3);   // the value for p0
		arguments_bundle.put(Method.END_POINT, 4.0);     // the value for p1

		try {
			EquationsSolver.solve(f, arguments_bundle, Method.BISECTION);
		} catch (Exception e) {
			System.out.println("can't be solved");

		}
	}
}
