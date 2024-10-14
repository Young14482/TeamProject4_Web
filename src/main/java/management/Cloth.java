package management;

import com.google.protobuf.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cloth {
	private int no;
	
	private int cloth_num; // 상품 번호(pk)
	private String cloth_name; // 상품 이름
	private String cloth_brand; // 상품 브랜드
	private int cloth_price; // 상품 가격
	private int cloth_sold; // 상품 판매 수량
	private int cloth_min_size; // 각 상품 별 최소 사이즈 (fk, size_category 참조)
	private int cloth_max_size; // 각 상품 별 최대 사이즈 (fk, size_category 참조)
	private String cloth_gender; // 상품 착용 권장 성별
	private String cloth_explanation;
	
	private int cloth_size_s; // 각 상품 별 S사이즈 재고 수량
	private int cloth_size_m; // 각 상품 별 M사이즈 재고 수량
	private int cloth_size_l; // 각 상품 별 L사이즈 재고 수량
	private int cloth_size_xl; // 각 상품 별 XL사이즈 재고 수량
	private int cloth_size_xxl; // 각 상품 별 XXL사이즈 재고 수량
}