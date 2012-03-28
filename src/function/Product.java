package function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Product extends Function implements Iterable<Function> {

	private List<Function> functions;

	public Product() {
		functions = new LinkedList<Function>();
	}

	public Product(LinkedList<Function> funs, double pow) {
		functions = funs;
		power = pow;
	}

	public Product(List<Function> functions) {
		this();
		functions.addAll(functions);

	}

	public Product(Function function) {
		this();
		functions.add(function);
	}

	public Product(Function... functions) {
		this();
		for (Function f : functions)
			this.functions.add(f);
	}

	public List<Function> getFunctions() {
		return functions;
	}

	// multiplies product by 1 function
	public Product multiply(Function f) {
		// add function to the list
		functions.add(f);
		return this;
	}

	// multiplies product by 1 product
	public Product multiply(Product p) {
		// adds all the functions in the input product to the list of functions
		// of the product that called the method
		Iterator<Function> it = p.getFunctions().iterator();
		while (it.hasNext()) {
			functions.add(it.next());
		}
		return this;
	}

	// multiplies product by 1 polynomial
	public Polynomial multiply(Polynomial p1) {
		// multiply each product in p1 by the calling product then return p1
		Iterator<Product> it = p1.getProducts().iterator();
		while (it.hasNext()) {
			it.next().multiply(this);
		}
		return p1;
	}

	@Override
	public double getValue(double x) {
		return 0;
	}

	@Override
	public Function differentiateFunction() {

		/*
		 * now it's needed to find the derivative of the functions it is
		 * represented by 2 products the product is represented as f0*f1, where
		 * f0 is the 1st function, f1 = product/f0 prod1 = f0 * f1' prod2 = f0'*
		 * f1
		 */
		if (functions.size() == 0) {
			return new ZeroFunction();
		} else if (functions.size() == 1) {
			return functions.get(0).differentiateFunction();
		} else { // functions.size() > 1
			List<Product> splited = split();
			// prepare 1st term in the polynomial, which is f0 * f1'
			// initially toBeF0 = 1
			Product toBeF0 = splited.get(0);
			// toBeF1 = f0*f1
			Product toBeF1 = splited.get(1);
			
			Product term1 = toBeF0.multiply(toBeF1.differentiateFunction());
			Product term2 = toBeF1.multiply(toBeF0.differentiateFunction());

			return new Polynomial(term1, term2);
		}
	}

	private List<Product> split() {
		assert (functions.size() > 1);
		List<Function> clone = new ArrayList<Function>(functions);
		List<Product> splited = new ArrayList<Product>();
		splited.add(new Product(clone.get(0)));
		clone.remove(0);
		splited.add(new Product(clone));
		return splited;
	}

	@Override
	public Iterator<Function> iterator() {
		return functions.iterator();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
}
