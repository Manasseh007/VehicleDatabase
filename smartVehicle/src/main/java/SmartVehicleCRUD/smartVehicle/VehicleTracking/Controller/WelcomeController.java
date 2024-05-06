package SmartVehicleCRUD.smartVehicle.VehicleTracking.Controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WelcomeController {

    @GetMapping("/vehicles/api")
    public String login() {
        return "login";
    }
}
