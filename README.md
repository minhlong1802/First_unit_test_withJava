Dưới đây là một ví dụ về kiểm thử đơn vị (Unit Testing) trong Java sử dụng JUnit, một thư viện phổ biến cho việc kiểm thử.
### Mô tả bài toán
Giả sử bạn có một lớp `Calculator` với các phương thức cộng (`add`) và nhân (`multiply`). Bạn muốn viết kiểm thử đơn vị để đảm bảo rằng các phương thức này hoạt động chính xác.

---

**1. Lớp chính `Calculator`**
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}
```

---

 **2. Lớp kiểm thử `CalculatorTest`**
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result, "The addition result should be 5");
    }

    @Test
    public void testMultiply() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(4, 5);
        assertEquals(20, result, "The multiplication result should be 20");
    }
}
```

---

### 3. Cách thiết lập môi trường để chạy kiểm thử

#### a. Thêm JUnit vào dự án:
Nếu bạn sử dụng **Maven**, thêm đoạn sau vào file `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version> <!-- Phiên bản mới nhất -->
        <scope>test</scope>
    </dependency>
</dependencies>
```

Nếu bạn dùng **Gradle**, thêm đoạn sau vào file `build.gradle`:
```groovy
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
}
```

#### b. Chạy kiểm thử:
- Nếu dùng IntelliJ IDEA hoặc Eclipse, click chuột phải vào file `CalculatorTest` và chọn **Run 'CalculatorTest'**.
- Nếu dùng Maven, chạy lệnh:
  ```bash
  mvn test
  ```
- Nếu dùng Gradle, chạy lệnh:
  ```bash
  gradle test
  ```

---

### 4. Kết quả kỳ vọng
- Khi chạy kiểm thử, bạn sẽ thấy thông báo rằng cả hai test đều **passed** (thành công).
- Nếu có lỗi trong logic (ví dụ: phép cộng bị sai), test sẽ **failed**, giúp bạn nhanh chóng xác định vấn đề.

---

Ví dụ này minh họa cách viết và chạy kiểm thử đơn vị đơn giản trong Java. Nếu bạn cần thêm ví dụ phức tạp hơn, tôi rất sẵn lòng hỗ trợ! 😊
