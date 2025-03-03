package com.gildedrose.items;

import com.gildedrose.Const;

public class ConjuredItem extends Item{
    public ConjuredItem(int sellIn, int quality) {
        super(Const.CONJURED, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        quality -= 2;
        sellIn--;

        if(sellIn < 0) {
            quality -= 2;
        }

        if(quality < 0) {
            quality = 0;
        }
    }
}
