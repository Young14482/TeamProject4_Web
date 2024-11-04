package management;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinUser {
	// 표의 행의 개수 나타내기 위한 필드 
	// (sql문에서의 row_number() over() as no)
	private int no; 
	
	private String user_id; // 회원 아이디
    private String user_name; // 회원 이름
    private String user_gender; // 회원 성별
    private String user_birth; // 회원 생년월일
    private String user_phone; // 회원 전화번호
    private String user_address; // 회원 주소
    private String user_grade; // 회원 등급
    private int user_useMoney; // 회원별 총 구매 금액 합산 
    private int user_block; // 회원 차단 여부
    private int user_leave; // 회원 탈퇴 여부
    
    private String deliveryaddress; // 배송지(회원 주소와 다를 수 있음)
    
    private String cloth_name; // 상품명
    private String cloth_brand; // 상품 브랜드
    private int cloth_price; // 상품 가격
    
    private int payment_count; // 판매 수량
    private Date payment_date; // 결제 일시 
}