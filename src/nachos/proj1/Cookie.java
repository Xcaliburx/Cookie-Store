package nachos.proj1;

public class Cookie implements Runnable {

	private String name;
	private String size;
	private Integer price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Cookie(String name, String size, Integer price) {
		super();
		this.name = name;
		this.size = size;
		this.price = price;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		try {
			System.out.println(" - " + getName());
			System.out.println("===================");
			System.out.println("Size  : " + getSize());
			System.out.println("Price : " + getPrice());
			System.out.println("");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
