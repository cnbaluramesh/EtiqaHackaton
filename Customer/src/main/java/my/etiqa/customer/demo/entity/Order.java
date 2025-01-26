package my.etiqa.customer.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Exclude from input but include in output
    @Schema(description = "Auto-generated ID", accessMode = Schema.AccessMode.READ_ONLY) // Mark ID as read-only
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @ElementCollection
    @CollectionTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @Column(name = "product_id")
    private List<Long> products;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Version
    //@Column(name = "version", nullable = false)
    private Integer version;
}
