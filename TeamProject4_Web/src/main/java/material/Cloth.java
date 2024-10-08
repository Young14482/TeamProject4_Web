package material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cloth {
	private int cloth_num;
	private String cloth_name;
	private String cloth_brand;
	private int cloth_price;
	private int cloth_image;
	private int cloth_sold;
	private int cloth_min_size;
	private int cloth_max_size;
	private String cloth_explanation;
	private String cloth_gender;
	private int season_category;
	private int color_category;
	private int s_category;
	private int usage_category1;
	private int usage_category2;
	private int usage_category3;

	private String list_image;
	private String main_image1;
	private String main_image2;
	private String main_image3;
	private String explanation_image1;
	private String explanation_image2;
	private String explanation_image3;
	private String explanation_image4;
	private String explanation_image5;

	private int cloth_good;
	private int cloth_bad;
}