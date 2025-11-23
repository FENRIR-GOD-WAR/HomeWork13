import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {
    Product p = new Product(1, "Mouse", 800);
    Product p1 = new Product(1, "Book", 300);
    Product p2 = new Product(2, "Phone", 2000);

    @Test
    public void shouldRemoveExistringProduct() {
        ShopRepository repo = new ShopRepository();

        repo.add(p1);
        repo.add(p2);

        repo.removeById(1);

        Product[] expected = {p2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }



    @Test
    public void shouldAddProductsAndAll() {
        ShopRepository repo = new ShopRepository();

        repo.add(p1);
        repo.add(p2);

        Product[] expected = {p1, p2};
        Assertions.assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void shouldFindByIdExists() {
        ShopRepository repo = new ShopRepository();
        repo.add(p);

        Assertions.assertEquals(p, repo.findById(1));
    }

    @Test
    public void shouldReturnNullNotFound() {
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(1,"Mouse", 800));

        assertNull(repo.findById(999));
    }



    @Test
        public void shouldAddProducts() {
            ShopRepository repo = new ShopRepository();
            Product p1 = new Product(1, "Phone", 1000);
            Product p2 = new Product(2, "TV", 2000);

            repo.add(p1);
            repo.add(p2);

            Product[] expected = {p1, p2};
            Product[] actual = repo.findAll();

            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldFindByIdIfExists() {
            ShopRepository repo = new ShopRepository();
            Product p1 = new Product(1, "Phone", 1000);
            Product p2 = new Product(2, "TV", 2000);

            repo.add(p1);
            repo.add(p2);

            Product actual = repo.findById(2);

            Assertions.assertEquals(p2, actual);
        }

        @Test
        public void shouldReturnNullIfNotFound() {
            ShopRepository repo = new ShopRepository();
            Product p1 = new Product(1, "Phone", 1000);

            repo.add(p1);

            Product actual = repo.findById(999); // нет такого товара

            Assertions.assertNull(actual);
        }

        @Test
        public void shouldRemoveExistingProduct() {
            ShopRepository repo = new ShopRepository();
            Product p1 = new Product(1, "Phone", 1000);
            Product p2 = new Product(2, "TV", 2000);
            Product p3 = new Product(3, "Lamp", 500);

            repo.add(p1);
            repo.add(p2);
            repo.add(p3);

            repo.removeById(2);

            Product[] expected = {p1, p3};
            Product[] actual = repo.findAll();

            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        public void shouldThrowNotFoundExceptionWhenRemoveMissing() {
            ShopRepository repo = new ShopRepository();
            Product p1 = new Product(1, "Phone", 1000);
            repo.add(p1);

            Assertions.assertThrows(NotFoundException.class, () -> repo.removeById(100));
        }

        @Test
        public void shouldStoreMessage() {
            NotFoundException e = new NotFoundException("Test message");
            Assertions.assertEquals("Test message", e.getMessage());
        }

        @Test
        public void shouldThrowNotFoundException() {
            ShopRepository repo = new ShopRepository();
            Product p1 = new Product(1, "Phone", 1000);
            Product p2 = new Product(2, "TV", 2000);

            repo.add(p1);
            repo.add(p2);

            Assertions.assertThrows(NotFoundException.class, () -> {
                repo.removeById(999);
            });
        }
}
