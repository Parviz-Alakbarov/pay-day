package az.express.order.model.entity;

import az.express.order.model.OrderState;
import az.express.order.model.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "order_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    private Long id;

    @Column
    private Long stockId;

    private Long userId;

    private String accountNumber;

    private Integer quantity;

    @Enumerated(EnumType.ORDINAL)
    private OrderType orderType;

    @Enumerated(EnumType.ORDINAL)
    private OrderState orderState;

    private BigDecimal targetPrice;

}
