package guru.springframework.msscbreweryclient.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    private UUID id;
    private Long version;

    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private String beerName;
    private String beerStyle;
    private Long upc;
    private BigDecimal price;

    private Integer minOnHand;
    private Integer quantityToBrew;
}
