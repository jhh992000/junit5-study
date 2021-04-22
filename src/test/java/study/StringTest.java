package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayName("String 테스트")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class StringTest {

	@Test
	@DisplayName("요구사항 1-1 : 구분자가 있을 경우 split 테스트")
	void splitContains() {
		String str = "1,2";
		String[] result = str.split(",");

		assertThat(result).contains("1", "2");
	}

	@Test
	@DisplayName("요구사항 1-2 : 구분자가 없을 경우 split 테스트")
	void splitContainsExactly() {
		String str = "1";
		String[] result = str.split(",");

		assertThat(result).containsExactly("1");
	}

	@Test
	@DisplayName("요구사항 2-1 : () 문자열 제거후 split 테스트")
	void splitAfterRemoveBracket() {
		String str = "(1,2)";
		if (str.contains("(")) {
			str = str.substring(str.indexOf("(") + 1);
		}
		if (str.contains(")")) {
			str = str.substring(0, str.indexOf(")"));
		}
		String[] result = str.split(",");

		assertThat(result).containsExactly("1", "2");
	}

	@Test
	@DisplayName("요구사항 3-1 : charAt 테스트")
	void charAt() {
		String str = "abc";
		char searchChar = str.charAt(0);

		assertThat(searchChar).isEqualTo('a');
	}

	@Test
	@DisplayName("요구사항 3-2 : charAt 예외발생 테스트1")
	void charAtException1() {
		String str = "abc";

		assertThatThrownBy(() -> {

			char searchChar = str.charAt(3);
			assertThat(searchChar).isEqualTo('e');

		}).isInstanceOf(IndexOutOfBoundsException.class)
			.hasMessageContaining("String index out of range: 3");
	}

	@Test
	@DisplayName("요구사항 3-2 : charAt 예외발생 테스트2")
	void charAtException2() {
		String str = "abc";

		assertThatExceptionOfType(IndexOutOfBoundsException.class)
			.isThrownBy(() -> {

				char searchChar = str.charAt(4);
				assertThat(searchChar).isEqualTo('e');

			}).withMessageMatching("String index out of range: \\d+");
	}

	@Test
	@DisplayName("요구사항 3-2 : nullPointer 예외발생 테스트")
	void nullPointerException() {
		String str = null;

		assertThatNullPointerException().isThrownBy(() -> {

			String result = str.substring(0, 1);
			assertThat(result).isEqualTo('a');

		}).withNoCause();
	}
}
