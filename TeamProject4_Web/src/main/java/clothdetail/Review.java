package clothdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Review {
	private String user_ID;
	private int cloth_num;
	private String reviewDetail;
	private String good_or_bad;
	private String review_date;
}
