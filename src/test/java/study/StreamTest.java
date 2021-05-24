package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stream.OnlineClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("java8의 stream api 테스트")
public class StreamTest {

	private List<String> names;

	@BeforeEach
	void init() {
		names = new ArrayList<>();
		names.add("hoonhee");
		names.add("hhjeong");
		names.add("jhh992000");
		names.add("erin");
		names.add("sunmi");
	}

	@Test
	void test_stream1() {
		List<String> list = names.stream()
				.map(s -> {
					System.out.println(s);
					return s.toUpperCase();
				}).collect(Collectors.toList());

		list.forEach(System.out::println);

		System.out.println("~~~~~~~~~~~~~~~");

		names.forEach(System.out::println);
	}


	@Test
	void test_stream2() {
		List<String> collect = names.parallelStream()
				.map(s -> {
					System.out.println(s + " " + Thread.currentThread().getName());
					return s.toUpperCase();
				})
				.collect(Collectors.toList());
		collect.forEach(System.out::println);
	}

	@Test
	void test_stream3() {
		List<OnlineClass> springClasses = new ArrayList<>();
		springClasses.add(new OnlineClass(1, "spring boot", true));
		springClasses.add(new OnlineClass(2, "spring data jpa", true));
		springClasses.add(new OnlineClass(3, "spring mvc", false));
		springClasses.add(new OnlineClass(4, "spring core", false));
		springClasses.add(new OnlineClass(5, "rest api development", false));

		List<OnlineClass> javaClasses = new ArrayList<>();
		javaClasses.add(new OnlineClass(6, "The Java, Test", true));
		javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
		javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

		List<List<OnlineClass>> hoonheeEvents = new ArrayList<>();
		hoonheeEvents.add(springClasses);
		hoonheeEvents.add(javaClasses);


		System.out.println("spring 으로 시작하는 수업");
		springClasses.stream()
				.filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
				.forEach(System.out::println);

		System.out.println("------------------------------------------------");
		System.out.println("close되지 않은 수업");
		springClasses.stream()
				.filter(Predicate.not(OnlineClass::isClosed))
				.forEach(System.out::println);

		System.out.println("------------------------------------------------");
		System.out.println("수업 이름만 모아서 스트림 만들기");
		springClasses.stream()
				.map(OnlineClass::getTitle)
				.forEach(System.out::println);

		System.out.println("------------------------------------------------");
		System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
		hoonheeEvents.stream().flatMap(Collection::stream)
				.forEach(onlineClass -> System.out.println(onlineClass.getId()));

		System.out.println("------------------------------------------------");
		System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
		Stream.iterate(10, integer -> integer +1)
				.skip(10)
				.limit(10)
			.forEach(System.out::println);

		System.out.println("------------------------------------------------");
		System.out.println("자바 수업중에 Test가 들어있는 수업이 있는지 확인");
		boolean test = javaClasses.stream().anyMatch(onlineClass -> onlineClass.getTitle().contains("Test"));
		System.out.println(test);

		System.out.println("------------------------------------------------");
		System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
		List<String> spring = springClasses.stream()
				.map(OnlineClass::getTitle)
				.filter(title -> title.startsWith("spring"))
				.collect(Collectors.toList());
		spring.forEach(System.out::println);

	}

}
