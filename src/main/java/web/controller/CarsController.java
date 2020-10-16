package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarsController {
    @Autowired
    public CarService service;

    @PostConstruct
    void fillDataBase (){
        Car car1 = new Car ("BMW",6,666);
        Car car2 = new Car ("Mersedes",600,777);
        Car car3 = new Car ("Hammer",100,1111);
        Car car4 = new Car ("LADA",300,4444);
        Car car5 = new Car ("UAZ",400,5555);

        service.add(car1);
        service.add(car2);
        service.add(car3);
        service.add(car4);
        service.add(car5);
    }

    @GetMapping(value = "/cars")
    public String listAll(@RequestParam (value = "locale", required = false) String locale,
                          @RequestParam (value = "count", required = false) int count, Model model) {

        List<Car> cars = service.getCars(count);
        Map<String,String> titles = new HashMap<>();
        titles.put("ru","List of машин:");
        titles.put("en","List of cars:");
        model.addAttribute("listOfCars", cars);
        if (locale == null) {
            locale = "en";
        }
        model.addAttribute("title", titles.get(locale));
        return "cars";
    }
}




