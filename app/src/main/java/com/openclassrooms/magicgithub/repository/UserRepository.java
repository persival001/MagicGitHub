package com.openclassrooms.magicgithub.repository;

import com.openclassrooms.magicgithub.api.ApiService;
import com.openclassrooms.magicgithub.model.User;

import java.util.List;

public class UserRepository {

    // Utilise la classe ApiService
    private final ApiService apiService;

    public UserRepository(ApiService apiService) {

        this.apiService = apiService;
    }

    // Retourne une liste d'utilisateurs
    public List<User> getUsers() {

        return apiService.getUsers();
    }

    // Genere un utilisateur au hasard
    public void generateRandomUser() {

        apiService.generateRandomUser();
    }

    // Supprime un utilisateur
    public void deleteUser(User user) {

        apiService.deleteUser(user);
    }
}
