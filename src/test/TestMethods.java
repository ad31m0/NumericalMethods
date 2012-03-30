package test;

import java.util.HashMap;
import java.util.Map;

import methods.nonlineareq.EquationsSolver;
import methods.nonlineareq.methods.Method;
import methods.nonlineareq.methods.Method.Solution;

import function.CosFun;
import function.Function;
import function.Polynomial;
import function.SinFun;
import function.XFun;

public class TestMethods {
	public static void main(String[] args) {
		// Suppose that the function was created using the parser.
		Polynomial p = new Polynomial();
		p.add(new XFun(1.0, 3));
		Function f = new CosFun(p);

		/*
		 * map to pass for the solve method to add arguments applies the Bundle
		 * design pattern to ensure encapsulation you should use the constants
		 * inside method class
		 */
		Map<String, Object> arguments_bundle = new HashMap<String, Object>();

		// Fixed Point;
		arguments_bundle.put(Method.MAX_ITERATIONS, 20); // 20 max iterations
		arguments_bundle.put(Method.TOLERANCE, 0.001); // tolerance
		arguments_bundle.put(Method.START_POINT, 1.1); // the value for p0

		try {
			Solution ss = EquationsSolver.solve(f, arguments_bundle, Method.FIXED_POINT_METHOD);
			System.out.print("Fixed Point : ");
			System.out.println(ss.solution + " ~= 0.0");
			System.out.println(ss.points);
		} catch (Exception e) {
			System.out.println("can't be solved");

		}

		// Bisection method;
		arguments_bundle.clear();
		arguments_bundle.put(Method.MAX_ITERATIONS, 20); // 20 max iterations
		arguments_bundle.put(Method.TOLERANCE, 0.001); // tolerance
		arguments_bundle.put(Method.START_POINT, 0.0); // the value for p0
		arguments_bundle.put(Method.END_POINT, 3.0); // the value for p1

		try {
			Solution ss = EquationsSolver.solve(f, arguments_bundle, Method.BISECTION_METHOD);

			System.out.print("Bisection : ");
			System.out.println(ss.solution + " ~= 0.0");
			System.out.println(ss.points);
		} catch (Exception e) {
			System.out.println("can't be solved" + e.getMessage());
			e.printStackTrace();

		}

		// False Position Method;
		arguments_bundle.clear();
		arguments_bundle.put(Method.MAX_ITERATIONS, 20); // 20 max iterations
		arguments_bundle.put(Method.TOLERANCE, 0.001); // tolerance
		arguments_bundle.put(Method.START_POINT, 1.1); // the value for p0
		arguments_bundle.put(Method.END_POINT, 1.5); // the value for p1

		try {
			Solution ss = EquationsSolver.solve(f, arguments_bundle, Method.NEWTON_METHOD);

			System.out.print("False Position : ");
			System.out.println(ss.solution + " ~= 0.0");
			System.out.println(ss.points);
		} catch (Exception e) {
			System.out.println("can't be solved" + e.getMessage());
			e.printStackTrace();

		}

		// Secant Method;
		arguments_bundle.clear();
		arguments_bundle.put(Method.MAX_ITERATIONS, 20); // 20 max iterations
		arguments_bundle.put(Method.TOLERANCE, 0.001); // tolerance
		arguments_bundle.put(Method.START_POINT, 0.0); // the value for p0
		arguments_bundle.put(Method.END_POINT, 1.5); // the value for p1

		try {
			Solution ss = EquationsSolver.solve(f, arguments_bundle, Method.SECANT_METHOD);

			System.out.print("Secant : ");
			System.out.println(ss.solution + " ~= 0.0");
			System.out.println(ss.points);
		} catch (Exception e) {
			System.out.println("can't be solved" + e.getMessage());
			e.printStackTrace();

		}

		// Newton Method;
		arguments_bundle.clear();
		arguments_bundle.put(Method.MAX_ITERATIONS, 20); // 20 max iterations
		arguments_bundle.put(Method.TOLERANCE, 0.001); // tolerance
		arguments_bundle.put(Method.START_POINT, 1.2); // the value for p0
		

		try {
			Solution ss = EquationsSolver.solve(f, arguments_bundle, Method.NEWTON_METHOD);

			System.out.print("Newton : ");
			System.out.println(ss.solution + " ~= 0.0");
			System.out.println(ss.points);
		} catch (Exception e) {
			System.out.println("can't be solved" + e.getMessage());
			e.printStackTrace();

		}
	}
}
