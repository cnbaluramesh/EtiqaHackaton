package my.etiqa.demo.dto;

public record Product(
        Long id,
        String bookTitle,
        Float bookPrice,
        String category,
        Integer bookQuantity,
        String description) {
}
