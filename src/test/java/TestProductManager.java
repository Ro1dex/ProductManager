import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestProductManager {

    Product product1 = new Product(0, "футболка", 500);
    Product product2 = new Product(1, "мяч", 1500);
    Product product3 = new Book(2, "книга", 500,"А. С. Пушкин");
    Product product4 = new Smartphone(3, "смартфон", 50000, "Samsung");

    @Test
    public void shouldAddProductInManager(){
        ProductRepository repository = new ProductRepository();
        ProductManager manager = new ProductManager(repository);
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        Product[] exp = {product1, product2, product3, product4};
        Product[] act = repository.findAll();
        Assertions.assertArrayEquals(exp,act);
    }
    @Test
    public void shouldDelProduct(){
        ProductRepository repository = new ProductRepository();
        repository.save(product2);
        repository.save(product4);
        repository.removeById(1);
        Product[] exp = {product4};
        Product[] act = repository.findAll();
        Assertions.assertArrayEquals(exp,act);

    }
    @Test
    public void shouldSearchProduct(){
        ProductRepository repository = new ProductRepository();
        ProductManager manager = new ProductManager(repository);
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        Product[] act = manager.searchBy("мяч");
        Product[] exp = {product2};
        Assertions.assertArrayEquals(exp, act);
        Product[] act1 = manager.searchBy("книга");
        Product[] exp1 = {product3};
        Assertions.assertArrayEquals(act1, exp1);
        Product[] act2 = manager.searchBy("смартфон");
        Product[] exp2 = {product4};
        Assertions.assertArrayEquals(act2,exp2);
    }
    @Test
    public void shouldNotFindIdWhenDelProduct(){
        ProductRepository repository = new ProductRepository();
        ProductManager manager = new ProductManager(repository);
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        Assertions.assertThrows(NotFoundException.class,() -> {repository.removeById(10);} );
    }
}
