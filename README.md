## My first unit testing with Java

Dưới đây là một ví dụ về kiểm thử đơn vị (unit testing) trong Java sử dụng **JUnit**, một thư viện phổ biến cho việc kiểm thử.

### Mô tả bài toán
Giả sử bạn có một lớp `Calculator` với các phương thức cộng (`add`) và nhân (`multiply`). Bạn muốn viết kiểm thử đơn vị để đảm bảo rằng các phương thức này hoạt động chính xác.

---

### 1. Lớp chính `Calculator`

```java
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

### 2. Lớp kiểm thử `CalculatorTest`

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void testAddWithZero() {
        assertEquals(5, calculator.add(5, 0), "Addition with zero failed");
        assertEquals(0, calculator.add(0, 0), "Addition with both zeros failed");
    }

    @Test
    void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(5, 0), "Multiplication with zero failed");
        assertEquals(0, calculator.multiply(0, 5), "Multiplication with zero failed");
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, 8",
            "-5, -3, -8",
            "-5, 3, -2",
            "1000000, 2000000, 3000000"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b), "Addition test failed");
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, 15",      // Nhân hai số dương
            "-5, -3, 15",    // Nhân hai số âm
            "-5, 3, -15",    // Nhân một số âm và một số dương
            "0, 5, 0",       // Nhân với 0
            "5, 0, 0"        // Nhân với 0 ở phía khác
    })
    void testMultiply(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b), "Multiplication test failed");
    }

    @Test
    void testMultiplyWithLargeNumbers() {
        assertEquals(2000000, calculator.multiply(1000, 2000), "Large number multiplication failed");
    }

    @Test
    void testMultiplyWithEdgeCases() {
        // Kiểm tra biên
        assertEquals(Integer.MIN_VALUE, calculator.multiply(Integer.MIN_VALUE, 1), 
            "Multiplication with MIN_VALUE failed");
        assertEquals(Integer.MAX_VALUE, calculator.multiply(Integer.MAX_VALUE, 1), 
            "Multiplication with MAX_VALUE failed");

        // Kiểm tra tràn số
        assertThrows(ArithmeticException.class, () -> {
            int result = Math.multiplyExact(Integer.MAX_VALUE, 2);
        }, "Expected overflow did not occur");
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
### 5. Kết quả thực tế
![image](https://github.com/user-attachments/assets/d1132d45-60c3-4412-bd5b-3635dd935d92)


---
### 6. Tham khảo
[Tại đây](https://chatgpt.com/share/677b5670-821c-8000-a050-c010f045b93b)
