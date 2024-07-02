
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

   
    private final FresherRepository fresherRepository;
    private final UserService userService;
    private static final String DEFAULT_PASSWORD = "123";

    @Autowired
    public FresherService(FresherRepository fresherRepository, UserService userService) {
        this.fresherRepository = fresherRepository;
        this.userService = userService;
    }

    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll();
    }

    public Fresher addFresher(Fresher fresher) {
        User user = createUserForFresher(fresher);
        fresher.setUser(user);
        fresher.calculateAndUpdateFinalAverageScore();
        return fresherRepository.save(fresher);
    }

    private User createUserForFresher(Fresher fresher) {
        return userService.createUserWithRole(fresher.getEmail(), fresher.getEmail(), DEFAULT_PASSWORD, Role.FRESHER);
    }

    public void deleteFresher(Long id) {
        fresherRepository.deleteById(id);
    }

    public Fresher updateFresher(Long id, Fresher fresherDetails) {
        Fresher fresher = fresherRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Fresher not found for id: " + id));
        updateFresherDetails(fresher, fresherDetails);
        return fresherRepository.save(fresher);
    }

    private void updateFresherDetails(Fresher fresher, Fresher fresherDetails) {
        fresher.setName(fresherDetails.getName());
        fresher.setProgrammingLanguage(fresherDetails.getProgrammingLanguage());
        fresher.setEmail(fresherDetails.getEmail());
        fresher.setProjectScores(fresherDetails.getProjectScores());
        fresher.setUser(fresherDetails.getUser());
        fresher.setCenter(fresherDetails.getCenter());
        fresher.calculateAndUpdateFinalAverageScore();
    }

    public Optional<Fresher> getFresherByFresherCode(String fresherCode) {
        return Optional.ofNullable(fresherRepository.findByFresherCode(fresherCode));
    }
}