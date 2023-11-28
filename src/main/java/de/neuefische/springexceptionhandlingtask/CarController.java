package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) {
        if (!brand.equals("porsche")) {
            throw new IllegalArgumentException();
        }
        return brand;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorMessage("Only 'porsche' allowed").toString();
    }

    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }
}
