package function;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Polynomial extends Function implements Iterable<Product> {
	private List<Product> products;

	public Polynomial() {
		// initialize power to 0 or 1 ?
		power = 1;
		products = new LinkedList<Product>();
	}

	public Polynomial(double power, List<Product> prod) {
		this();
		this.power = power;
		products.addAll(prod);
	}

	public Polynomial(LinkedList<Product> prod) {
		power = 1;
		products = prod;
	}

	public Polynomial(Product... products) {
		this();
		for (Product product : products)
			this.products.add(product);
	}

	public List<Product> getProducts() {
		return products;
	}

	@Override
	public double getValue(double x) {
		double sum = 0;
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			sum += it.next().getValue(x);
		}
		return sum;
	}

	@Override
	public Function differentiateFunction() {
		Polynomial result = new Polynomial();
		for (Product product : products)
			result.add(product.differentiateFunction());
		return result;
	}

	public void add(Function function) {
		if (function instanceof Polynomial)
			for (Product product : (Polynomial) function)
				this.products.add(product);

		else if (function instanceof Product)
			this.products.add((Product) function);

		else
			this.products.add(new Product(function));
	}

	@Override
	public Iterator<Product> iterator() {
		return products.iterator();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		LinkedList<Product> cl = new LinkedList<Product>();
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			cl.add(it.next());
		}
		return new Polynomial(power, cl);
	}

	@Override
	public String toString() {
		String s = "(";
		Iterator<Product> it = products.iterator();
		if (it.hasNext())
			s += it.next();
		while (it.hasNext()) {
			s += " + " + it.next().toString();
		}
		s += ")";
		return s;
	}

}
