package management;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categorys {
	private int season_category;
	private int color_category;
	private int s_category;
	private int usage_category1;
	private int usage_category2;
	private int usage_category3;
}
