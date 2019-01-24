package com.bes.tao.freedge;

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

    public static String[] recipesText;

    /**
     * Метод делает запрос в локальную ДБ и возвращает двумерный массив  [productID, productName, productWeight, amount, categoryID, addDate]
     **/
    public static String[][] getAvailableProducts(final Context context) {
        /*class DatabaseThread extends AsyncTask<Void, Void, String[][]> {
            @Override
            protected String[][] doInBackground(Void... voids) {*/
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
                    for (int i = 0; i < 6; ++i) {
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
                return returnRequest;/*
            }
        }
        DatabaseThread databaseThread = new DatabaseThread();
        databaseThread.execute();

        String[][] returnElement = {{}, {}, {}, {}, {}, {}};
        try {
            returnElement = databaseThread.get();
        } catch (Exception e) {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        return returnElement;*/
    }

    public static void addProduct(final int id, final String name, final int weight, final String amount, final int catID, final String date, final Context context){
        // TODO: Метод принимает на вход id продукта и вес, после чего добавляет этот продукт в DB
        /*class DatabaseThread extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {*/
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
                insertData.put("amount", amount);
                insertData.put("categoryID", catID);
                insertData.put("addDate", date);
                db.insert(DataBaseHelper.TABLE_NAME, null, insertData);
                db.close();
                /*return null;
            }
        }
        DatabaseThread databaseThread = new DatabaseThread();
        databaseThread.execute();*/
    }

    public static void removeProduct(final int id, final Context context){
        // TODO: Метод принимает на вход id продукта, после чего удаляет его из DB
        /*class DatabaseThread extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {*/
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
                /*return null;
            }
        }
        DatabaseThread databaseThread = new DatabaseThread();
        databaseThread.execute();*/
    }

    /**
     * Геттеры цветов и иконок для RecyclerView
     */

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
        request.execute("http://95.163.181.200/fridge/?query=get_recipe&id=" + String.valueOf(id));

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

    public static String[][] loadAllProducts(Context context) {
        NetworkRequests request = new NetworkRequests(context);
        request.execute("http://95.163.181.200/fridge/?query=get_all_food");

        JSONArray jsonArray;
        String[][] returnRecipes = {};
        try {
            String response = request.get();
            jsonArray = new JSONArray(response);
            returnRecipes = new String[4][jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                returnRecipes[0][i] = jsonArray.getJSONObject(i).getString("id");
                returnRecipes[1][i] = jsonArray.getJSONObject(i).getString("name");
                returnRecipes[2][i] = jsonArray.getJSONObject(i).getString("categories");
                returnRecipes[3][i] = jsonArray.getJSONObject(i).getString("units").equals("0") ? "гр." : "шт.";
            }
        } catch (Exception e) {
            Toast.makeText(context, "Что-то пошло не так :c", Toast.LENGTH_LONG);
        }
        return returnRecipes;
    }

    public static String[][] loadAvailableProducts(Context context) {
        String[][] availableProducts = getAvailableProducts(context);
        String requestURI = "";

        for (int i = 0; i < availableProducts.length; ++i) {
            requestURI += availableProducts[i][0] + "|10000";
            if (i < availableProducts.length - 1) {
                requestURI += "@";
            }
        }

        NetworkRequests request = new NetworkRequests(context);
        request.execute("http://95.163.181.200/fridge/?query=calc&arr=" + requestURI + "&start=14000&end=60000&max=0&str=");

        JSONArray jsonArray;
        String[][] returnRecipes = {};
        try {
            String response = request.get();
            jsonArray = new JSONArray(response);
            returnRecipes = new String[2][jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                returnRecipes[0][i] = jsonArray.getJSONObject(i).getString("id");
                returnRecipes[1][i] = jsonArray.getJSONObject(i).getString("name");
            }
        } catch (Exception e) {
            Toast.makeText(context, "Что-то пошло не так :c", Toast.LENGTH_LONG);
        }
        return returnRecipes;
    }

    public static int getCategoryColorsById(String id) {
        int[] categories = {
                -1, R.color.categoryTeal, R.color.categoryAmber, R.color.categoryBlueGrey,
                R.color.categoryGrey,  R.color.categoryYellow, R.color.categoryDeepPurple,
                R.color.categoryGreen,  R.color.categoryOrange, R.color.categoryAmber,
                R.color.categoryLime,  R.color.categoryBrown, R.color.categoryCyan,
                R.color.categoryLightGreen,  R.color.categoryPurple, R.color.categoryDeepPurple,
                R.color.categoryRed,  R.color.categoryBlue, R.color.categoryDeepOrange,
                R.color.categoryRed,  R.color.categoryGrey, R.color.categoryYellow,
                R.color.categoryYellow,  R.color.categoryBrown, R.color.categoryLightBlue,
                R.color.categoryBlueGrey,  R.color.categoryAmber, R.color.categoryPurple,
                R.color.categoryYellow,  R.color.categoryOrange

        };
        return categories[Integer.valueOf(id)];
    }

    public static int getCategoryIconsById(String id) {
        int[] iconURLs = {-1, R.drawable.category_1, R.drawable.category_2, R.drawable.category_3,
                R.drawable.category_4, R.drawable.category_5, R.drawable.category_6,
                R.drawable.category_7, R.drawable.category_8, -1,
                R.drawable.category_10, R.drawable.category_11, R.drawable.category_12,
                R.drawable.category_13, R.drawable.category_14, R.drawable.category_15,
                R.drawable.category_16, R.drawable.category_17, R.drawable.category_18,
                R.drawable.category_19, R.drawable.category_20, R.drawable.category_21,
                R.drawable.category_22, R.drawable.category_23, R.drawable.category_24,
                R.drawable.category_25, R.drawable.category_26, R.drawable.category_27,
                R.drawable.category_28, R.drawable.category_29
        };
        return iconURLs[Integer.valueOf(id)];
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
