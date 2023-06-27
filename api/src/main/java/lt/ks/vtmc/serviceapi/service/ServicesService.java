package lt.ks.vtmc.serviceapi.service;

import io.jsonwebtoken.lang.Arrays;
import lombok.RequiredArgsConstructor;
import lt.ks.vtmc.serviceapi.exception.DuplicatedServiceInfo;
import lt.ks.vtmc.serviceapi.exception.ServiceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ServicesService {

    private final ServiceRepository serviceRepository;


    public List<AutoService> getServices() {
        var aa = serviceRepository.findAll();
        return aa;
    }


    public List<AutoService> getService(String title) {
        AutoService autoService = serviceRepository.findByTitle(title)
                .orElseThrow(() -> new ServiceNotFoundException("Service tile " + title + " not found!"));
        List<AutoService> services = new ArrayList<>();
        services.add(autoService);
        return services;
    }

    public AutoService createService(AutoService newService) {
        AutoService presentService = serviceRepository.findByTitle(newService.getTitle()).isPresent()
                ? serviceRepository.findByTitle(newService.getTitle()).get()
                : null;
        if (
                presentService != null
                && presentService.getAddressStreet().equalsIgnoreCase(newService.getAddressStreet())
                && presentService.getAddressCity().equalsIgnoreCase(newService.getAddressCity())){

       throw new DuplicatedServiceInfo("This service is in list already!");

        }

        return serviceRepository.save(newService);
    }

    public void deleteService(Long serviceId) {
        try {
            serviceRepository.deleteById(serviceId);
        }catch (Exception e){
            throw new ServiceNotFoundException(e.getMessage());
        }
    }
}
