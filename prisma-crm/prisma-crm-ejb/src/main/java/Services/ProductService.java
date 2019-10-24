package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import Entities.Product;
import Entities.Tariff;
import Interfaces.IProductServiceLocal;
import Interfaces.IProductServiceRemote;



@Stateless
public class ProductService implements IProductServiceLocal, IProductServiceRemote{
	@PersistenceContext(unitName = "prisma-crm-ejb")
	EntityManager em;

	
	public ProductService() {
		super();
	}

	@Override
	public Product addProduct(Product p) {
		em.persist(p);
		return p;
	}

	@Override
	public void removeProduct(int id) {
		em.remove(em.find(Product.class, id));		
	}

	@Override
	public Product updateProduct(Product newProduct) {
		Product p = em.find(Product.class, newProduct.getId());
		p.setName(newProduct.getName());
		p.setReference(newProduct.getReference());
		p.setDescription(newProduct.getDescription());
		p.setType(newProduct.getType());
		p.setGuarantee(newProduct.getGuarantee());
		p.setPrice(newProduct.getPrice());
		
		return p;
		
	}

	@Override
	public Product findProductById(int id) {
		Product p = em.find(Product.class, id);
		return p;
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = em.createQuery("from Product", Product.class).getResultList();
		return products;
	}
	
	
	@Override
	public Tariff addTarif(Tariff t) {
		em.persist(t);
		return t;
	}

	@Override
	public void removeTarif(int id) {
		em.remove(em.find(Tariff.class, id));		
		
	}

	@Override
	public Tariff updateTarif(Tariff newTarif) {
		Tariff t = em.find(Tariff.class, newTarif.getId());
		t.setCnxSpeed(newTarif.getCnxSpeed());
		t.setPriceT(newTarif.getPriceT());
		return t;
	}

	@Override
	public Tariff findTarifById(int id) {
		Tariff t = em.find(Tariff.class, id);
		return t;
	}

	@Override
	public List<Tariff> findAllTarifs() {
		List<Tariff> tarifs = em.createQuery("from Tariff", Tariff.class).getResultList();
		return tarifs;
	}
	
	

	
}