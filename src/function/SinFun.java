package function;
//ok
public class SinFun extends Function{
	Polynomial angle;
	
	public SinFun(Polynomial a){
		angle = a;
		power = 1;
	}
	
	public SinFun(Polynomial a, int p){
		angle = a;
		power = p;
	}
	
	@Override
	public double getValue(double x) {
		return Math.sin(angle.getValue(x));
	}

	@Override
	public Function differentiateFunction() {
		Product result = new Product();
		result.multiply(new CosFun(angle));
		result.multiply(angle.differentiateFunction());
		return result;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
}
