package microservices.book.multiplication.service;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices.book.multiplication.domain.Multiplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RandomGeneratorServiceImplTest {
	
	private  MultiplicationServiceImpl multiplicationServiceImpl;
	
	private AutoCloseable autoCloseable;
	
	@Mock
	private RandomGeneratorService randomGeneratorService;
	
	@BeforeEach	
	public void setup() {
		
		autoCloseable = MockitoAnnotations.openMocks(this);
		
		randomGeneratorService = new RandomGeneratorServiceImpl();
		multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
		
	}
	
	@AfterEach
	public void closeService() throws Exception {
		
		autoCloseable.close();
		
	}
	
	@Test
	public void createRandomMultiplicationTest() {
		
		//given
		given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
		
		//when
		Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();
		
		//then
		
		assertThat(multiplication.getFactorA()).isEqualTo(30);
		assertThat(multiplication.getFactorB()).isEqualTo(50);
		assertThat(multiplication.getResult()).isEqualTo(1500);	
		
	}
	
	
	@Ignore("not ready")
	public void generateRandomFactorIsBetweenExpectedLimits() throws Exception{
		
		List<Integer> randomFactors = IntStream.range(0, 1000)
											   .map(i -> 
											   randomGeneratorService.generateRandomFactor()
											   )
											   .boxed()
											   .collect(Collectors.toList());
		List <Integer> limits = IntStream.range(11, 100)
								  .boxed()
								  .collect(Collectors.toList());
		
		
		assertThat(randomFactors).isSubsetOf(limits);
		
	}

}
