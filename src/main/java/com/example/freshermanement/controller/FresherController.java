package com.example.freshermanement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.freshermanement.entity.Fresher;
import com.example.freshermanement.service.FresherService;

import java.util.List;

@RestController
@RequestMapping("/freshers")
public class FresherController {

    @Autowired
    private FresherService fresherService;
    @GetMapping
    public ResponseEntity<List<Fresher>> getAllFreshers() {
        return ResponseEntity.ok(fresherService.getAllFreshers());
    }
    @PostMapping
    public ResponseEntity<Fresher> addFresher(@RequestBody Fresher fresher) {
        return ResponseEntity.ok(fresherService.addFresher(fresher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFresher(@PathVariable Long id) {
        fresherService.deleteFresher(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Fresher> updateFresher(@PathVariable Long id, @RequestBody Fresher fresher) {
        return ResponseEntity.ok(fresherService.updateFresher(id, fresher));
    }

    @GetMapping("/{fresherCode}")
    public ResponseEntity<Fresher> getFresherByFresherCode(@PathVariable String fresherCode) {
        return fresherService.getFresherByFresherCode(fresherCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
