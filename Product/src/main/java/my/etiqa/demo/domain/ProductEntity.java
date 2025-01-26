package my.etiqa.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Objects;

@Table("product_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity implements Serializable {

    @Id
    @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("id")
    private Long id;

    @Column("book_title")
    @Schema(name = "bookTitle", example = "The Great Gatsby", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("bookTitle")
    private String bookTitle;

    @Column("book_price")
    @Schema(name = "bookPrice", example = "19.99", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("bookPrice")
    private Float bookPrice;

    @Column("book_quantity")
    @Schema(name = "bookQuantity", example = "100", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("bookQuantity")
    private Integer bookQuantity;

    @Column("category")
    @Schema(name = "category", example = "Fiction", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("category")
    private String category;

    @Column("description")
    @Schema(name = "description", example = "A novel written by F. Scott Fitzgerald.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("description")
    private String description;

    public ProductEntity(long id) {
        this.id = id;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bookTitle, that.bookTitle) &&
                Objects.equals(bookPrice, that.bookPrice) &&
                Objects.equals(bookQuantity, that.bookQuantity) &&
                Objects.equals(category, that.category) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookTitle, bookPrice, bookQuantity, category, description);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookQuantity=" + bookQuantity +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
