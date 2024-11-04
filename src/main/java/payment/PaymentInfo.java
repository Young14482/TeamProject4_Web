package payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentInfo {
    private int totalPayPrice;
    private int paymentCount; // 여기 이름을 일치시켜줍니다.
}

