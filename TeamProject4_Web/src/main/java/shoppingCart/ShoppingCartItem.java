package shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCartItem {
    private String user_Id;
    private int cloth_num;
    private int cloth_price;
    private int shoppingcart_count;
    private String cloth_name;
    private String list_Image;
}
