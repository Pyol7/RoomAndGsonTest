package com.jeffreyromero.roomandgsontest.utilities;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jeffreyromero.roomandgsontest.models.Material;
import com.jeffreyromero.roomandgsontest.models.materials.CeilingTile;
import com.jeffreyromero.roomandgsontest.models.materials.CeilingPanel;
import com.jeffreyromero.roomandgsontest.models.materials.Stud;
import com.jeffreyromero.roomandgsontest.models.materials.WallAngle;
import com.jeffreyromero.roomandgsontest.models.materials.WallPanel;

import java.lang.reflect.Type;
import java.util.List;

public class GsonTypeConverter {
    private static Gson gson;

    static {
        GsonRuntimeTypeAdapterFactory<Material> MaterialTypeAdapter = GsonRuntimeTypeAdapterFactory
                .of(Material.class, "subType")
                .registerSubtype(CeilingTile.class, "Ceiling Tile")
                .registerSubtype(WallAngle.class, "Wall Angle")
                .registerSubtype(WallPanel.class, "Wall Panel")
                .registerSubtype(CeilingPanel.class, "Ceiling Panel")
                .registerSubtype(Stud.class, "Stud");

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(MaterialTypeAdapter)
                .create();
    }

    @TypeConverter
    public static String toJson(List<Material> materials){
        return gson.toJson(materials);
    }

    @TypeConverter
    public static List<Material> toMaterials(String json) {
        Type type = new TypeToken<List<Material>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
