package function;

public class NumberFun extends Function {

	private double number;

	public NumberFun(double number) {
		this.number = number;
	}

	@Override
	public double getValue(double x) {
		return number;
	}

	@Override
	public Function differentiateFunction() {
		return new ZeroFunction();
	}

		@Override
	protected Object clone() throws CloneNotSupportedException {
		NumberFun clone = new NumberFun(number);
		clone.power = this.power;
		return clone;		
	}
	
	@Override
	public String toString(){
		return ""+number;
	}


}
