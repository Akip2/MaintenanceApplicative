package com.gildedrose.items;

import com.gildedrose.Const;

public class BackstageItem extends Item{
    public BackstageItem(int sellIn, int quality) {
        super(Const.BACKSTAGE_PASSES, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if(quality < 50) {
            if (sellIn <= 5) {
                quality += 3;
            } else if (sellIn <= 10) {
                quality += 2;
            } else {
                quality++;
            }
        }

        sellIn--;
        if(sellIn < 0) {
            quality = 0;
        }
    }
}
