package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/customers")
    public ResponseEntity<Collection<Customer>> getAllCustomers() throws MongoConnectionException {
        ResponseEntity<Collection<Customer>> customers;
        try {
            customers = new ResponseEntity<Collection<Customer>>(repository.findAll(),HttpStatus.OK);
        }
        catch (DataAccessResourceFailureException exception) {
            throw new MongoConnectionException(exception.getMessage(), exception);
        }
        return customers;
    }
}
