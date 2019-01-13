package com.example.user.freedge;

import java.util.ArrayList;

public class DataHandler {

    // ***********************
    // *** Product getters ***
    // ***********************

    public ArrayList getAvailableProducts() { // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: Масса, [3]: Дата добавления, [4]: ID Категории]
        ArrayList availableProducts = new ArrayList();
        return availableProducts;
    }

    public ArrayList getAllProducts() { // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: ID Категории]
        ArrayList allProducts = new ArrayList();
        return allProducts;
    }

    public ArrayList getProductByID(int ID) { // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: ID Категории]
        ArrayList productInformation = new ArrayList();
        return productInformation;
    }

    // **********************
    // *** Recipe getters ***
    // **********************

    public ArrayList getAvailableRecipes() { // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: ID Необходимых продуктов, [3]: Категория]
        ArrayList availableRecipes = new ArrayList();
        return availableRecipes;
    }


    public ArrayList getAllRecipes() { // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: ID Необходимых продуктов, [3]: Категория]
        ArrayList allRecipes = new ArrayList();
        return allRecipes;
    }

    // *******************************
    // *** Product adders/removers ***
    // *******************************

    public void addProduct(int id, int weight){

    }

    public void removeProduct(int id){

    }


}
