package function;

public class ZeroFunction extends Function{

	@Override
	public double getValue(double x) {
		return 0;
	}

	@Override
	public Function differentiateFunction() {
		return new ZeroFunction();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new ZeroFunction();
	}

}
