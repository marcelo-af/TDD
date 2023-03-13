package com.example.tdd;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.tdd.model.BookingModel;
import com.example.tdd.repository.BookingRepository;
import com.example.tdd.service.BookingService;

@RunWith(SpringRunner.class)
public class BookingServiceTest {
	
	
	//Cria uma classe de configuração para retornar o metodo bookingService
	@TestConfiguration
	static class BookingServiceTestConfiguration{
		
		/*Como foi instanciada o bookingService
		  toda vez que for chamada tera o valort dessa instancia */	
		@Bean
		public BookingService bookingService() {
			return new BookingService();
		}
	}
		
	@Autowired
	BookingService bookingService;
	
	@MockBean
	BookingRepository bookingRepository;
	
	//O @Test informa que o metodo é um teste
	@Test
	public void bookingTestServiceDaysCalculator() {
	  
		String name = "Marcelo";
		int days = bookingService.daysCalculatorWithDB(name);
		
		//assertEquals informa a variavel esperada(days) e o resultado esperado(10) 
		Assertions.assertEquals(days, 10);			
	}

	@Before
	public void setup() {
		LocalDate checkIn = LocalDate.parse("2020-11-10");
		LocalDate checkOut = LocalDate.parse("2020-11-20");
		
		BookingModel bookingModel = new BookingModel("1", "Marcelo", checkIn, checkOut, 5);
		
		//Utiliza a instancia do bookingRepository no Mockito
		Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName()))
		        .thenReturn(java.util.Optional.of(bookingModel)); 
	}
}
