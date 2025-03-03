package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private void handleItem(Item item) {
        switch (item.name) {
            case Const.AGED_BRIE -> handleBrie(item);
            case Const.SULFURAS -> handleSulfuras(item);
            case Const.BACKSTAGE_PASSES -> handleBackstage(item);
            case Const.CONJURED -> handleConjured(item);
            default -> handleStandard(item);
        }

        if(item.quality < 0) {
            item.quality = 0;
        }
    }

    private void handleStandard(Item item) {
        item.quality--;
        item.sellIn--;

        if(item.sellIn < 0) {
            item.quality--;
        }
    }

    private void handleBrie(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
        item.sellIn -= 1;
    }

    private void handleBackstage(Item item) {
        if(item.quality < 50) {
            if (item.sellIn <= 5) {
                item.quality += 3;
            } else if (item.sellIn <= 10) {
                item.quality += 2;
            } else {
                item.quality++;
            }
        }

        item.sellIn --;
        if(item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void handleSulfuras(Item item) {

    }

    private void handleConjured(Item item) {
        item.quality -= 2;
        item.sellIn--;

        if(item.sellIn < 0) {
            item.quality -= 2;
        }
    }

    public void updateQuality() {
        for (Item item : items) {
            handleItem(item);
        }
    }
}
