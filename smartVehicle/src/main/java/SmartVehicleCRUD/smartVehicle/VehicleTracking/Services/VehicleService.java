package SmartVehicleCRUD.smartVehicle.VehicleTracking.Services;

import SmartVehicleCRUD.smartVehicle.VehicleTracking.Entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    // Create operation
    Vehicle createVehicle(Vehicle vehicle);

    Optional<Vehicle> getVehicleById(Long id);

    // Update operation
    Vehicle updateVehicle(Long id, Vehicle updatedVehicle);

    // Delete operation
    void deleteVehicle(Long id);

    Vehicle updateVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();
}
