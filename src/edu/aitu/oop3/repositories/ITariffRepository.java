package edu.aitu.oop3.repositories;
import edu.aitu.oop3.models.Tariff;
import java.util.List;

public interface ITariffRepository {
    Tariff getById(int id);
    List<Tariff> getAllTariffs();
}

//void create(Tariff tariff);
//Tariff findByName(String name);
//Tariff getById(int id);
//Tariff getByCost(int cost);