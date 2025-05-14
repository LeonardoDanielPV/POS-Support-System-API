package mx.ipn.pos_support_system_api.entitys.administrator_control.product_change;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductChangeNotFoundAdvice {
  @ExceptionHandler(ProductChangeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String productChageNotFoundHandler(ProductChangeNotFoundException ex) {
    return ex.getMessage();
  }
}
