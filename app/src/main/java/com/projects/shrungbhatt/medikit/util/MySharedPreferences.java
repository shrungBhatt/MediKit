package com.projects.shrungbhatt.medikit.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by jigsaw on 7/1/18.
 */

public class MySharedPreferences {

    private static String PREF_RECIPE_ARRAY_JSON = "allRecipeArrayList";
    private static String PREF_FAVRT_RECIPE_ARRAY_JSON = "favrtRecipeArrayList";
    private static String PREF_RECIPE_BYCATEGORY = "recipeByCategory";
    private static String PREF_RECIPE_BYCUISINES = "recipeByCuisines";
    private static final String PREF_LOGIN_STATE = "login state";
    private static final String PREF_ADMIN_LOGIN_STATE = "admin login state";

    private static final String PREF_USERNAME = "username";







    public static String getAllRecipeArrayJson(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_RECIPE_ARRAY_JSON,null);
    }

    public static void setAllRecipeArrayJson(Context context, String arrayJson){
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(PREF_RECIPE_ARRAY_JSON,arrayJson).apply();

    }

    public static String getFavrtRecipeArrayJson(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_FAVRT_RECIPE_ARRAY_JSON,null);
    }

    public static void setFavrtRecipeArrayJson(Context context, String arrayJson){
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(PREF_FAVRT_RECIPE_ARRAY_JSON,arrayJson).apply();

    }

    public static String getRecipeDatasByCategory(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_RECIPE_BYCATEGORY,null);
    }

    public static void setRecipeDatasByCategory(Context context, String arrayJson){
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(PREF_RECIPE_BYCATEGORY,arrayJson).apply();

    }

    public static String getRecipeDatasByCuisines(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_RECIPE_BYCUISINES,null);
    }

    public static void setRecipeDatasByCuisines(Context context, String arrayJson){
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(PREF_RECIPE_BYCUISINES,arrayJson).apply();

    }

    public static Boolean getStoredLoginStatus (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_LOGIN_STATE,false);
    }

    public static void setStoredLoginStatus (Context context, Boolean status){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREF_LOGIN_STATE,status)
                .apply();
    }

    public static String getStoredUsername (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_USERNAME,null);
    }

    public static void setStoredUsername (Context context, String status){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_USERNAME,status)
                .apply();
    }

    public static Boolean isAdminLoggedOn (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_ADMIN_LOGIN_STATE,false);
    }

    public static void setIsAdminLoggedOn (Context context, Boolean status){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREF_ADMIN_LOGIN_STATE,status)
                .apply();
    }



}
