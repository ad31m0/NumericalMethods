package function;

public class CosFun extends Function {

	private Polynomial angle;

	public CosFun(Polynomial a) {
		angle = a;
		power = 1;
	}

	public CosFun(Polynomial a, double p) {
		angle =a;
		power = p;
	}

	@Override
	public double getValue(double x) {
		return Math.cos(angle.getValue(x));
	}

	@Override
	public Function differentiateFunction() {
	if (power == 0) {
			return new ZeroFunction();
		}
		Product result = new Product();
		result.multiply(new NumberFun(-1));
		result.multiply(new SinFun(angle));
		result.multiply(angle.differentiateFunction());
		return result;
	}
public String toString() {
		return "cos(" + angle.toString() + ")";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new CosFun(angle, power);
	}
}
