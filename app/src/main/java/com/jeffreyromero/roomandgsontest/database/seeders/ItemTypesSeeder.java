package com.jeffreyromero.roomandgsontest.database.seeders;


import com.jeffreyromero.roomandgsontest.database.daos.ItemDao;
import com.jeffreyromero.roomandgsontest.models.Item;
import com.jeffreyromero.roomandgsontest.models.Material;
import com.jeffreyromero.roomandgsontest.models.itemTypes.DroppedCeiling;
import com.jeffreyromero.roomandgsontest.models.itemTypes.DrywallCeiling;
import com.jeffreyromero.roomandgsontest.models.materials.CeilingTile;
import com.jeffreyromero.roomandgsontest.models.materials.WallAngle;
import java.util.ArrayList;
import java.util.List;

public class ItemTypesSeeder {
    private ItemDao itemDao;

    public ItemTypesSeeder(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void run(){

        List<Material> materials = new ArrayList<>();
        materials.add(new CeilingTile(2, 2));
        materials.add(new WallAngle(10));

        Item item1 = new DroppedCeiling("Kitchen");
        item1.setLength(12);
        item1.setWidth(10);
        item1.setMaterials(materials);

        Item item2 = new DrywallCeiling("Bedroom");
        item2.setLength(15);
        item2.setWidth(12);
        item2.setMaterials(materials);

        itemDao.insert(item1);
        itemDao.insert(item2);
    }

}
