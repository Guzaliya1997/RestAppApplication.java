package ru.spring.RestApp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.spring.RestApp.controller.Controller;
import ru.spring.RestApp.util.SymbolNotAllowedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestRestControllerTests {
		@InjectMocks
		private Controller restController;

		@Test
		public void testGetFrequencySymbol() {
			String input = "aaannnnjjjkkkkk";
			String expected = "\"k\":5, \"n\":4, \"a\":3, \"j\":3";

			String result = restController.getFrequencySymbol(input);

			assertEquals(expected, result);
		}

	@Test
	public void testGetFrequencySymbol_InvalidInput() {
		String input = "aaannnjjj123";
		String expectedErrorMessage = "Error.Only english letters are allowed to input";

		Exception exception = assertThrows(SymbolNotAllowedException.class, () -> {
			restController.getFrequencySymbol(input);
		});
		assertEquals(expectedErrorMessage, exception.getMessage());
	}
	}





