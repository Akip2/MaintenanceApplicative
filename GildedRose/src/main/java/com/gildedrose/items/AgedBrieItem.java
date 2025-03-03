package com.gildedrose.items;

import com.gildedrose.Const;

public class AgedBrieItem extends Item{
    public AgedBrieItem(int sellIn, int quality) {
        super(Const.AGED_BRIE, sellIn, quality);
    }


    @Override
    public void updateQuality() {
        if (quality < 50) {
            quality++;
        }
        sellIn--;
    }
}
