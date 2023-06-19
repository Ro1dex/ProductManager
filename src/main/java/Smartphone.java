public class Smartphone extends Product {


    private String producer;
    public Smartphone(int id, String name, int price, String producer) {
        super(id, name, price);
      this.producer = producer;
    }
   public String getProducer() {
       return producer;
   }

}
