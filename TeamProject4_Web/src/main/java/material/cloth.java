package material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class cloth {
	 private int cloth_num;
	 private String cloth_name;
	 private String cloth_brand;
	 private int cloth_price;
	 private int cloth_image;
	 private int cloth_season_category;
	 private int cloth_color_category;
	 private int cloth_b_category;
	 private int cloth_s_category1;
	 private int cloth_s_category2;
}