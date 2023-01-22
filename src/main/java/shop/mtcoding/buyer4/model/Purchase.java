package shop.mtcoding.buyer4.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Purchase {
    private int id;
    private int userId;
    private int productId;
    private int count;
    private Timestamp createdAt;
}
