package function;

//ok
public class SinFun extends Function {
	Polynomial angle;

	public SinFun(Polynomial a) {
		angle = a;
		power = 1;
	}

	public SinFun(Polynomial a, double power) {
		angle = a;
		this.power = power;
	}

	@Override
	public double getValue(double x) {
		return Math.sin(angle.getValue(x));
	}

	@Override
	public Function differentiateFunction() {
		if (power == 0) {
			return new ZeroFunction();
		}
		Product res = new Product();
		res.multiply(new CosFun(angle));
		res.multiply(angle.getDerivative());
		return res;
	}

	@Override
	public String toString() {
		return "sin^" + power + "(" + angle.toString() + ")";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new SinFun(angle, power);
	}
}
