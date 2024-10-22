package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.Performer;
import com.sparta.spring_prepare.Repository.PerformerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformerService {

    private final PerformerRepository performerRepository;

    public PerformerService(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }

    public Performer createperformer(Performer performer) {
        return performerRepository.save(performer);
    }

    public List<Performer> getAllperformer() {
        return performerRepository.findAll();
    }

    public Optional<Performer> getperformerById(Long id) {
        return performerRepository.findById(id);
    }

    public Performer updateperformer(Long id, Performer updatedperformer) {
        return performerRepository.findById(id)
                .map(performer -> {
                    performer.setTitle(updatedperformer.getTitle());
                    performer.setContent(updatedperformer.getContent());
                    performer.setPrice(updatedperformer.getPrice());
                    performer.setTradeTime(updatedperformer.getTradeTime());
                    performer.setTradeDate(updatedperformer.getTradeDate());
                    performer.setLocation(updatedperformer.getLocation());
                    performer.setImage(updatedperformer.getImage());
                    return performerRepository.save(performer);
                }).orElseThrow(() -> new IllegalArgumentException("Post not found with id " + id));
    }
}
