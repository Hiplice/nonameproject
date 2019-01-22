package com.example.user.freedge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class DataHandler {

    String availableProducts;
    boolean onAvailableProductsLoaded = false;

    /**
     * Метод делает запрос в локальную ДБ и возвращает двумерный массив  [productID, productName, productWeight, categoryID, addDate]
     **/
    public static String[][] getAvailableProducts(final Context context) {
        class DatabaseThread extends AsyncTask<Void, Void, String[][]> {
            @Override
            protected String[][] doInBackground(Void... voids) {
                DataBaseHelper dbHelper = new DataBaseHelper(context);
                SQLiteDatabase db;
                try {
                    db = dbHelper.getWritableDatabase();
                }
                catch (SQLiteException ex){
                    db = dbHelper.getReadableDatabase();
                }
                Cursor cursor = db.query("localproductlist", null, null, null, null, null, null);

                ArrayList<ArrayList<String>> requestList = new ArrayList<>();

                int counter = 0;
                while (cursor.moveToNext()) {
                    requestList.add(new ArrayList<String>());
                    for (int i = 0; i < 5; ++i) {
                        requestList.get(counter).add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.columnNames[i])));
                    } counter++;
                }
                String[][] returnRequest;
                if (requestList.size() == 0) {
                    returnRequest = new String[][] {};
                } else {
                    returnRequest = new String[requestList.size()][requestList.get(0).size()];
                }
                for (int i = 0; i < requestList.size(); ++i) {
                    for (int j = 0; j < requestList.get(0).size(); ++j) {
                        returnRequest[i][j] = requestList.get(i).get(j);
                    }
                }
                cursor.close();
                db.close();
                return returnRequest;
            }
        }
        DatabaseThread databaseThread = new DatabaseThread();
        databaseThread.execute();

        String[][] returnElement = {{}, {}, {}, {}, {}};
        try {
            returnElement = databaseThread.get();
        } catch (Exception e) {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        return returnElement;
    }

    public static void addProduct(final int id, final String name, final String weight, final int catID, final String date, final Context context){
        // TODO: Метод принимает на вход id продукта и вес, после чего добавляет этот продукт в DB
        class DatabaseThread extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                DataBaseHelper dbHelper = new DataBaseHelper(context);
                SQLiteDatabase db;
                try {
                    db = dbHelper.getWritableDatabase();
                }
                catch (SQLiteException ex){
                    db = dbHelper.getReadableDatabase();
                }

                ContentValues insertData = new ContentValues();
                insertData.put("productID", id);
                insertData.put("productName", name);
                insertData.put("productWeight", weight);
                insertData.put("categoryID", catID);
                insertData.put("addDate", date);
                db.insert(DataBaseHelper.TABLE_NAME, null, insertData);
                db.close();
                return null;
            }
        }
        DatabaseThread databaseThread = new DatabaseThread();
        databaseThread.execute();
    }

    public static void removeProduct(final int id, final Context context){
        // TODO: Метод принимает на вход id продукта, после чего удаляет его из DB
        class DatabaseThread extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                DataBaseHelper dbHelper = new DataBaseHelper(context);
                SQLiteDatabase db;
                try {
                    db = dbHelper.getWritableDatabase();
                }
                catch (SQLiteException ex){
                    db = dbHelper.getReadableDatabase();
                }

                db.delete(DataBaseHelper.TABLE_NAME, "productID = ?", new String[] {String.valueOf(id)});
                db.close();
                return null;
            }
        }
        DatabaseThread databaseThread = new DatabaseThread();
        databaseThread.execute();
    }

    /**
     * Геттеры цветов и иконок для RecyclerView
     */

    public static int getCategoryColorsById(String id) {
        int[] categories = {R.color.categoryMeat, R.color.categoryFruit, R.color.categoryGrass, R.color.categoryVegetable, R.color.categorySpice, R.color.categorySweet};
        return categories[Integer.valueOf(id)];
    }

    public static int getCategoryIconsById(String id) {
        int[] iconURLs = {R.drawable.category_meat, R.drawable.category_fruit, R.drawable.category_grass, R.drawable.category_vegetable, R.drawable.category_spice, R.drawable.category_sweet};
        return iconURLs[Integer.valueOf(id)];
    }

    public static String[][] searchFirstNRecipes(int n, String str, Context context) {

        String[] recipes = new String[] {"Лазанья с сыром", "Томатный суп с креветками"};
        NetworkRequests request = new NetworkRequests(context);
        request.execute("http://95.163.181.200/fridge/?query=search&count=" + String.valueOf(n) + "&str=" + String.valueOf(str));

        JSONArray jsonArray;
        String[][] returnRecipes = {};
        try {
            String response = request.get();
            jsonArray = new JSONArray(response);
            returnRecipes = new String[2][jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                returnRecipes[0][i] =  jsonArray.getJSONObject(i).getString("id");
                returnRecipes[1][i] =  jsonArray.getJSONObject(i).getString("name");
            }
        } catch (Exception e) {
            Toast.makeText(context, "Что-то пошло не так :c", Toast.LENGTH_LONG);
        }

        return returnRecipes;
    }

    public static String[] getRecipeById(int id, Context context) {

        NetworkRequests request = new NetworkRequests(context);
        request.execute("http://95.163.181.200/fridge/?query=get_recipe&i=" + String.valueOf(id));

        JSONObject jsonObject;
        String[] returnRecipes = {};
        try {
            String response = request.get();
            jsonObject = new JSONObject(response);
            returnRecipes = jsonObject.getString("recipe").split("@");
        } catch (Exception e) {
            Toast.makeText(context, "Что-то пошло не так :c", Toast.LENGTH_LONG);
        }

        return returnRecipes;
    }

    public static String[][] getAllProducts(Context context) {

        NetworkRequests request = new NetworkRequests(context);
        request.execute("http://95.163.181.200/fridge/?query=get_all_food");

        JSONArray jsonArray;
        String[][] returnRecipes = {};
        try {
            String response = request.get();
            jsonArray = new JSONArray(response);
            returnRecipes = new String[2][jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                returnRecipes[0][i] =  jsonArray.getJSONObject(i).getString("id");
                returnRecipes[1][i] =  jsonArray.getJSONObject(i).getString("name");
            }
        } catch (Exception e) {
            Toast.makeText(context, "Что-то пошло не так :c", Toast.LENGTH_LONG);
        }

        return returnRecipes;
    }
    /*public static String[][] loadAvailableRecipes(Context context) {
        String[][] availableProducts = getAvailableProducts(context);
        String[][] availableProductData = new String[2][availableProducts.length];

        for (int i = 0; i < availableProducts.length; ++i) {
            availableProductData[0][i] = availableProducts[i][0];
            availableProductData[1][i] = availableProducts[i][1];
        }

        NetworkRequests request = new NetworkRequests(context);
        request.execute("http://95.163.181.200/fridge/?query=search&count=" + String.valueOf(n) + "&str=");

        JSONArray jsonArray;
        String[][] returnRecipes = {};
        try {
            String response = request.get();
            jsonArray = new JSONArray(response);
            returnRecipes = new String[2][jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                returnRecipes[0][i] =  jsonArray.getJSONObject(i).getString("name");
                returnRecipes[1][i] =  jsonArray.getJSONObject(i).getString("name");
            }
        } catch (Exception e) {
            Toast.makeText(context, "Что-то пошло не так :c", Toast.LENGTH_LONG);
        }

        return returnRecipes;
    }*/


}
