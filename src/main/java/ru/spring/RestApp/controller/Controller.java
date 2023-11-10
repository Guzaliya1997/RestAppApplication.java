package ru.spring.RestApp.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.RestApp.util.StringErrorResponse;
import ru.spring.RestApp.util.SymbolNotAllowedException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Api(description = "Контроллер для демонстрации вычисления частоты встречи символов английского алфавита  по заданной строке")

@RestController
@RequestMapping("/api")
public class Controller {
    @ApiOperation("Проверка строки на соответствование требованиям. В случае если строка соответствует требованиям," +
            "каждый символ кладется в HashMap в качестве ключей, в качестве значений кладутся значение частоты повторений ключа" +
            "затем параметру output  присваивается новая строка отсортированная по значению HashMap в обратном порядке " +
            "через методы stream  ;" +
            "в случае если входная строка не соответствует требованиям, выбрасывается текст  ошибки SymbolNotAllowedException ")
    @GetMapping("/frequencySymbol/{string}")
    public String getFrequencySymbol(@PathVariable  String string) {
        if (!isValid(string)) {
            throw new SymbolNotAllowedException();
        }
            Map<Character, Integer> map = new HashMap<>();
            String output = "";
            for (int i = 0; i < string.length(); i++) {
                char k = string.charAt(i);
                char key = Character.toLowerCase(k);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            output = map.entrySet().stream()
                    .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                    .map(entry -> "\"" + entry.getKey() + "\":" + entry.getValue())
                    .collect(Collectors.joining(", "));
            return output;

    }
    @ApiOperation("Метод для определения соответсвия строки требованиям")
    private boolean isValid(String string) {
        if(string.matches("^[a-zA-Z]+$")) { return true; }
        else{ return false;}
    }


    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler
        private ResponseEntity<StringErrorResponse> handleException(SymbolNotAllowedException e) {
            StringErrorResponse response = new StringErrorResponse(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
