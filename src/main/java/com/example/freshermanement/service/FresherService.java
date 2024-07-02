
package com.example.freshermanement.service;

import com.example.freshermanement.entity.Fresher;
import com.example.freshermanement.entity.User;
import com.example.freshermanement.enums.Role;
import com.example.freshermanement.repository.FresherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private UserService userService;

    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll();
    }

    public Fresher addFresher(Fresher fresher) {
        String username = fresher.getEmail(); 
        String email = fresher.getEmail();
        String password = "123"; 
        User user = userService.createUserWithRole(username, email, password, Role.FRESHER);
        fresher.setUser(user);
        fresher.calculateAndUpdateFinalAverageScore(); 
        return fresherRepository.save(fresher);
    }

    public void deleteFresher(Long id) {
        fresherRepository.deleteById(id);
    }

    public Fresher updateFresher(Long id, Fresher fresherDetails) {
        Fresher fresher = fresherRepository.findById(id).orElseThrow(() -> new RuntimeException("Fresher not found"));
        fresher.setName(fresherDetails.getName());
        fresher.setProgrammingLanguage(fresherDetails.getProgrammingLanguage());
        fresher.setEmail(fresherDetails.getEmail());
        fresher.setProjectScores(fresherDetails.getProjectScores());
        fresher.setUser(fresherDetails.getUser());
        fresher.setCenter(fresherDetails.getCenter());
        fresher.calculateAndUpdateFinalAverageScore(); 
        return fresherRepository.save(fresher);
    }

    public Optional<Fresher> getFresherByFresherCode(String fresherCode) {
        return Optional.ofNullable(fresherRepository.findByFresherCode(fresherCode));
    }
}