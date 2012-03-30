package function;

public class XFun extends Function{
	double coefficient;
	
	public XFun(double coef, double pow){
		coefficient = coef;
		power = pow;
	}
	
	@Override
	public double getValue(double x) {
		return coefficient * (Math.pow(x, power));
	}
	
	@Override
	public Function differentiateFunction() {
		if(power == 1)
			return new NumberFun(coefficient);
		Polynomial result = new Polynomial();
		Product res = new Product();
		res.multiply(new XFun(coefficient*power, power-1));
		result.add(res);
		return result;
	}

	@Override
	public String toString(){
		return coefficient + " (x^" + power + ")";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new XFun(coefficient, power);
	}
}
