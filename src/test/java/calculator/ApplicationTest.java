package calculator;
import otherpackage+.Calculator;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    // Calculator 객체 선언
    private final Calculator calculator = new Calculator();  // 여기서 Calculator 클래스 인스턴스 생성

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(calculator.add("1")).contains("결과 : 1");  // calculator 인스턴스를 사용하여 add 메서드 호출
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
