package chava.bday.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RestApi {
    
    @GetMapping(path = "/hello_world")
    public String HelloWorld(){
        return "Hello everyone, play SMT3 Nocturne, is great!!!";
    }

    @GetMapping(path = "/bday")
    public ResponseEntity<Map<String, Object>> getBdayInfo(@RequestParam (required = false) LocalDate bday){
        try {
            LocalDate today = LocalDate.now();
            int currentYear = today.getYear();
            if(today.getMonthValue() >= bday.getMonthValue() && today.getDayOfMonth() > bday.getDayOfMonth()){
                currentYear++;
            }
            long difference = ChronoUnit.DAYS.between(bday, today);
            long untilBday = ChronoUnit.DAYS.between(today, LocalDate.of(currentYear, bday.getMonth(), bday.getDayOfMonth()));

            System.out.println("Bday: " + bday + "\nDate: " + today + "\nNext Bday: " + (LocalDate.of(today.getYear(), bday.getMonth(), bday.getDayOfMonth())));

            Map<String,Object> response = new HashMap<>();
            response.put("diff", difference);
            response.put("untilBday", untilBday);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(501).body(null);
        }
    }
}
