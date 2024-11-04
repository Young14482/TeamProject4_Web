package payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PayCloth {
	private String user_ID;
	private int cloth_num;
	private String cloth_name;
	private int cloth_size;
	private int payment_count;
	private String payment_date;
	private int write_review;
	private int cloth_price;
	private int delivery_status;
}
