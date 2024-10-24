package com.sparta.spring_prepare.api;

import com.sparta.spring_prepare.Entity.Performer;
import com.sparta.spring_prepare.Service.PerformerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/performer")
@CrossOrigin(origins = "http://localhost:3000")
public class performerController {


    private final PerformerService performerService;

    public performerController(PerformerService performerService) {
        this.performerService = performerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Performer> createperformer(@RequestBody Performer performer) {
        Performer createdperformer = performerService.createperformer(performer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdperformer);
    }

    @GetMapping("/list")
    public List<Performer> getAllperformers() {
        return performerService.getAllperformers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Performer> getperformerById(@PathVariable Long id) {
        Optional<Performer> performer = performerService.getperformerById(id);
        return performer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Performer> updateperformer(@PathVariable Long id, @RequestBody Performer updatedperformer) {
        Performer performer = performerService.updateperformer(id, updatedperformer);
        return ResponseEntity.ok(performer);
    }
}

