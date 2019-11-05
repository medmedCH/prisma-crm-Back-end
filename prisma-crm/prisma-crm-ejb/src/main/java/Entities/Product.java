package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import Enums.ProductType;

@Entity
@Table(name = "Product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String reference;

	private String name;

	private String description;

	@Enumerated(EnumType.STRING)
	private ProductType type;

	private int guarantee;

	private double price;
	@Column(name = "new_price")
	private double new_price;

	private String brand;

	private String memory;

	private String resolution;

	private String camera;

	private String imageUrl;


	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdAt = new Date(System.currentTimeMillis());


	@ManyToOne(cascade = CascadeType.ALL)
	Agent agent;

	@JsonIgnore
	@ManyToOne
	Store store; 
	
	@OneToMany(mappedBy="product")
	private Set<CartProductRow> cartRows;
		
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "tarifProduct",
			joinColumns = {@JoinColumn(name = "product_id")},
			inverseJoinColumns = {@JoinColumn(name = "tarif_id")}
			)
	private Set<Tariff> tarifs ;
	
	@Column(name="stock")
	private int stock;

	@JsonIgnore
	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
	private List<Pack> packs;
	

	@ManyToOne
	private Promotion promotion;

	public Product(int id, String reference, String name, String description, ProductType type, int guarantee,
			double price/* List<TarifProduct> tafifProductList */, Promotion promotion, double new_price) {
		super();
		this.id = id;
		this.reference = reference;
		this.name = name;
		this.description = description;
		this.type = type;
		this.guarantee = guarantee;
		this.price = price;
		// this.stockList = stockList;
		// this.tafifProductList = tafifProductList;
		this.promotion = promotion;
		this.new_price = new_price;
	}

	public Product() {
		super();
	}

	public Product(int id, String reference, String name, String description, ProductType type, int guarantee,
			double price, List<Pack> packs, Promotion promotion, double new_price) {
		super();
		this.id = id;
		this.reference = reference;
		this.name = name;
		this.description = description;
		this.type = type;
		this.guarantee = guarantee;
		this.price = price;
		this.packs = packs;
		this.promotion = promotion;
		this.new_price = new_price;
	}

	public Product(int id, String reference, String name, String description, ProductType type, int guarantee,
			double price) {
		super();
		this.id = id;
		this.reference = reference;
		this.name = name;
		this.description = description;
		this.type = type;
		this.guarantee = guarantee;
		this.price = price;
	}

	public Product(String reference, String name, String description, ProductType type, int guarantee, double price) {
		super();
		this.reference = reference;
		this.name = name;
		this.description = description;
		this.type = type;
		this.guarantee = guarantee;
		this.price = price;

	}

	// @OneToMany(mappedBy = "tariff" ,cascade =
	// {CascadeType.ALL},fetch=FetchType.EAGER)
	// private List<TarifProduct> tafifProductList = new ArrayList<>();


	public List<Pack> getPacks() {
		return packs;
	}

	public void setPacks(List<Pack> packs) {
		this.packs = packs;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public double getNew_price() {
		return new_price;
	}

	public void setNew_price(double new_price) {
		this.new_price = new_price;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public int getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(int guarantee) {
		this.guarantee = guarantee;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}	

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Set<Tariff> getTarifs() {
		return tarifs;
	}

	public void setTarifs(Set<Tariff> tarifs) {
		this.tarifs = tarifs;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<CartProductRow> getCartRows() {
		return cartRows;
	}

	public void setCartRows(Set<CartProductRow> cartRows) {
		this.cartRows = cartRows;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
//	public List<TarifProduct> getTarifProductList() {
//		return tafifProductList;
//	}

	/*
	 * public void setTarifProductList(List<TarifProduct> tafifProductList) {
	 * this.tafifProductList = tafifProductList; }
	 * 
	 * public List<Stock> getStockList() { return stockList; }
	 */

	/*
	 * public void setStockList(List<Stock> stockList) { this.stockList = stockList;
	 * }
	 * 
	 * public List<TarifProduct> getTafifProductList() { return tafifProductList; }
	 * 
	 * public void setTafifProductList(List<TarifProduct> tafifProductList) {
	 * this.tafifProductList = tafifProductList; }
	 */

	/*
	 * @Override public String toString() { return "Product [id=" + id +
	 * ", reference=" + reference + ", name=" + name + ", description=" +
	 * description + ", type=" + type + ", guarantee=" + guarantee + ", price=" +
	 * price + ", stockList=" + stockList + ", tafifProductList=" + tafifProductList
	 * + "]"; }
	 */
}

