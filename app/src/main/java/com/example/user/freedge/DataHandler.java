package com.example.user.freedge;

import java.util.ArrayList;
import java.util.Arrays;

public class DataHandler {

    /**
     * Product getters
     */

    public static ArrayList getAvailableProducts() {
        // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: Масса, [3]: Дата добавления, [4]: ID Категории]

        // TODO: Тут должен быть запрос из ДБ
        String[][] availableProductsMass = {
                {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
                {"Куриное мясо", "Брокколи", "Говядина", "Сыр", "Колбаса", "Арбуз", "Молоко", "Морковь", "Лук", "Яблоки"},
                {"500 г", "700 г", "1200 г", "350 г", "200 г", "5400 г", "1000 г", "2000 г", "1500 г", "1200 г"},
                {"10.11.2005", "10.11.2005", "10.11.2005", "10.11.2005", "10.11.2005", "10.11.2005", "10.11.2005", "10.11.2005", "10.11.2005", "10.11.2005"},
                {"0", "1", "0", "2", "3", "4", "1", "2", "3", "0"}
        };

        ArrayList availableProducts = new ArrayList<ArrayList<String>>();
        for (String[] i : availableProductsMass) {
            ArrayList inside = new ArrayList<String>();
            for (String j: i) {
                inside.add(j);
            }
            availableProducts.add(inside);
        }
        return availableProducts;
    }

    public ArrayList getAllProducts() {
        // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: ID Категории]
        ArrayList allProducts = new ArrayList();
        return allProducts;
    }

    public ArrayList getProductByID(int ID) {
        // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: ID Категории]
        ArrayList productInformation = new ArrayList();
        return productInformation;
    }

    /**
     * Recipe getters
     */

    public ArrayList getAvailableRecipes() {
        // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: ID Необходимых продуктов, [3]: Категория]
        ArrayList availableRecipes = new ArrayList();
        return availableRecipes;
    }


    public ArrayList getAllRecipes() {
        // TODO: Возвращение ArrayList со структурой [[0]: ID, [1]: Название, [2]: ID Необходимых продуктов, [3]: Категория]
        ArrayList allRecipes = new ArrayList();
        return allRecipes;
    }

    /**
     * Category getters
     */

    public static ArrayList getCategoryColorsById(ArrayList<String> idList) {
        // TODO: Метод принимает на вход ArrayList с id категорий и отдаёт ArrayList с String`ами цветов
        ArrayList colorList = new ArrayList<String>();
        String[] categories = {"#EF5350", "#ffa726", "#66BB6A", "#26A69A", "#8D6E63", "#EC407A"};
        for (int i = 0; i < idList.size(); ++i) {
            colorList.add(categories[Integer.valueOf(idList.get(i))]);
        }
        return colorList;
    }

    public ArrayList getCategoryIconsById(ArrayList idList) {
        // TODO: Метод принимает на вход ArrayList с id категорий и отдаёт ArrayList с String`ами ссылок на иконки
        ArrayList iconList = new ArrayList();
        String[] iconURLs = {"@drawable", "#ffa726", "#66BB6A", "#26A69A", "#8D6E63", "#EC407A"};
        return iconList;
    }

    /**
     * Product adders/removers
     */

    public void addProduct(int id, int weight){
        // TODO: Метод принимает на вход id продукта и вес, после чего добавляет этот продукт в DB
    }

    public void removeProduct(int id){
        // TODO: Метод принимает на вход id продукта, после чего удаляет его из DB
    }


}
