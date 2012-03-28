package function;

public class ExpFun extends Function{

	private Polynomial pow;
	
	public ExpFun(Polynomial p){
		pow = p;
		power =1;
	}
	@Override
	public double getValue(double x) {
		return  Math.pow(Math.E, pow.getValue(x));
	}

	@Override
	public Function differentiateFunction() {
		// TODO Auto-generated method stub
		Product res = new Product();
		res.multiply(new ExpFun(pow));
		return res.multiply(pow.differentiateFunction()); 
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
