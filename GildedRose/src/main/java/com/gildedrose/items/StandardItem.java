package com.gildedrose.items;

import com.gildedrose.Const;

public class StandardItem extends Item {
    public StandardItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        quality--;
        sellIn--;

        if(sellIn < 0) {
            quality--;
        }

        if(quality < 0) {
            quality = 0;
        }
    }
}
