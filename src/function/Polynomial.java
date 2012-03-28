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

	public Polynomial(int pow, List<Product> prod) {
		this();
		power = pow;
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
		// TODO Auto-generated method stub
		return 0;
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
		Polynomial clone = new Polynomial();

		return clone;
	}

}
