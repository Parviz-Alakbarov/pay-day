package az.express.account.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private Long id;
    private BigDecimal price;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id.equals(stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

