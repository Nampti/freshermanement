package com.example.freshermanement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.freshermanement.dto.response.ApiResponse;
import com.example.freshermanement.entity.Fresher;
import com.example.freshermanement.service.FresherService;

import java.util.List;

@RestController
@RequestMapping("/freshers")
public class FresherController {

    @Autowired
    private FresherService fresherService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Fresher>>> getAllFreshers() {
        List<Fresher> freshers = fresherService.getAllFreshers();
        return new ResponseEntity<>(ApiResponse.<List<Fresher>>builder().code(HttpStatus.OK.value()).message("Success").result(freshers).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Fresher>> addFresher(@RequestBody Fresher fresher) {
        Fresher createdFresher = fresherService.addFresher(fresher);
        return new ResponseEntity<>(ApiResponse.<Fresher>builder().code(HttpStatus.CREATED.value()).message("Fresher added successfully").result(createdFresher).build(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFresher(@PathVariable Long id) {
        fresherService.deleteFresher(id);
        return new ResponseEntity<>(ApiResponse.<Void>builder().code(HttpStatus.OK.value()).message("Fresher deleted successfully").build(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Fresher>> updateFresher(@PathVariable Long id, @RequestBody Fresher fresher) {
        Fresher updatedFresher = fresherService.updateFresher(id, fresher);
        return new ResponseEntity<>(ApiResponse.<Fresher>builder().code(HttpStatus.OK.value()).message("Fresher updated successfully").result(updatedFresher).build(), HttpStatus.OK);
    }

    @GetMapping("/{fresherCode}")
    public ResponseEntity<ApiResponse<Fresher>> getFresherByFresherCode(@PathVariable String fresherCode) {
        return fresherService.getFresherByFresherCode(fresherCode)
                .map(fresher -> new ResponseEntity<>(ApiResponse.<Fresher>builder().code(HttpStatus.OK.value()).message("Success").result(fresher).build(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(ApiResponse.<Fresher>builder().code(HttpStatus.NOT_FOUND.value()).message("Fresher not found").build(), HttpStatus.NOT_FOUND));
    }
}