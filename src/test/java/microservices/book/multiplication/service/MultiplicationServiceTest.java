package microservices.book.multiplication.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import microservices.book.multiplication.domain.Multiplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {
	
	@MockBean
	private RandomGeneratorService randomGeneratorService;
	
	@Autowired	
	private MultiplicationService multiplicationService;
	
	
	@Test
	public void createRandomMultiplicationTest() {
	
		//given
		given(randomGeneratorService.generateRandomFactor()).willReturn(30,50);
		
		//when
		Multiplication multiplication = multiplicationService.createRandomMultiplication();
		
		//then
		
		assertThat(multiplication.getFactorA()).isEqualTo(30);
		assertThat(multiplication.getFactorB()).isEqualTo(50);
		assertThat(multiplication.getResult()).isEqualTo(1500);		
		
	}

}
