package SmartVehicleCRUD.smartVehicle.VehicleTracking.Services;

import SmartVehicleCRUD.smartVehicle.VehicleTracking.Entity.Vehicle;
import SmartVehicleCRUD.smartVehicle.VehicleTracking.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Create a new vehicle
    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Read and retreive data
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // Update the vehicle information
    @Override
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        // Check if the vehicle with the given id exists
        Optional<Vehicle> existingVehicleOptional = vehicleRepository.findById(id);
        if (existingVehicleOptional.isPresent()) {
            updatedVehicle.setId(id);
            return vehicleRepository.save(updatedVehicle);
        } else {
            // Vehicle with the given id not found
            return null; // Or throw an exception
        }
    }

    // Delete operation
    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return null;
    }

}