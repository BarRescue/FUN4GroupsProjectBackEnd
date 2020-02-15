package App.service;

import App.entity.Drink;
import App.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<Drink> findAll() {
        return this.drinkRepository.findAll();
    }

    public Optional<Drink> findById(UUID id) {
        return this.drinkRepository.findById(id);
    }

    public Optional<Drink> findByName(String name) {
        return this.drinkRepository.findByName(name);
    }

    public Drink createOrUpdate(Drink drink) {
        return this.drinkRepository.save(drink);
    }

    public void delete(Drink drink) {
        this.drinkRepository.delete(drink);
    }
}
