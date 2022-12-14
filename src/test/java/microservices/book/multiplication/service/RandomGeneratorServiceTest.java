package microservices.book.multiplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomGeneratorServiceTest {
	
	@Autowired
	private RandomGeneratorService randomGeneratorService;
	
	@Test
	public void generateRandomFactorIsBetweenExpectedLimits() throws Exception{
		
		List<Integer> randomFactors = IntStream.range(0, 1000)
											   .map(i -> randomGeneratorService.generateRandomFactor())
											   .boxed()
											   .collect(Collectors.toList());
		
		List<Integer> limitFactors = IntStream.range(11, 100)
										     .boxed()
										     .collect(Collectors.toList());
		
		assertThat(randomFactors).containsExactlyElementsOf(limitFactors);
		
	}

}
