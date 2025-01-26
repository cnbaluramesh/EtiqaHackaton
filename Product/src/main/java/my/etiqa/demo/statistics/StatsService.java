package my.etiqa.demo.statistics;

import my.etiqa.demo.domain.ProductEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StatsService {

    private final Random random = new Random();

    @Cacheable(cacheNames = "stats", key = "#Id")
    public ProductEntity getUserStats(int id) {
        System.out.println("Calculating stats for userId=" + id);
        return new ProductEntity(id);
    }

    private int getRequestsCountFromDb(int id) {
        // heavy operation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return random.nextInt();
    }

}