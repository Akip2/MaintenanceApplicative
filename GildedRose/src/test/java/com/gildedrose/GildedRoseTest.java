package com.gildedrose;

import com.gildedrose.items.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("Update quality of Standard Item")
    void testStandardUpdateQuality1() {
        Item[] items = new Item[] { new StandardItem("foo", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Standard Item with sellIn 0")
    void testStandardUpdateQuality2() {
        Item[] items = new Item[] { new StandardItem("foo", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Standard Item with sellIn 0 and quality 0")
    void testStandardUpdateQuality3() {
        Item[] items = new Item[] { new StandardItem("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Aged Brie")
    void testAgedBrieUpdateQuality1() {
        Item[] items = new Item[] { new AgedBrieItem(1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Aged Brie with quality above 50")
    void testAgedBrieUpdateQuality2() {
        Item[] items = new Item[] { new AgedBrieItem(5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Backstage passes with sellIn below 50")
    void testBackStageUpdateQuality1() {
        Item[] items = new Item[] { new BackstageItem(50, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Backstage passes with sellIn below 10")
    void testBackStageUpdateQuality2() {
        Item[] items = new Item[] { new BackstageItem( 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Backstage passes with sellIn below 5")
    void testBackStageUpdateQuality3() {
        Item[] items = new Item[] { new BackstageItem(5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Backstage passes with sellIn 0")
    void testBackStageUpdateQuality4() {
        Item[] items = new Item[] { new BackstageItem(0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Backstage passes with quality above 50")
    void testBackStageUpdateQuality5() {
        Item[] items = new Item[] { new BackstageItem(5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Sulfuras")
    void testSulfurasUpdateQuality() {
        Item[] items = new Item[] { new SulfurasItem(5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Conjured")
    void testConjuredUpdateQuality1() {
        Item[] items = new Item[] { new ConjuredItem(5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(48, app.items[0].quality);
    }

    @Test
    @DisplayName("Update quality of Conjured with sellIn 0")
    void testConjuredUpdateQuality2() {
        Item[] items = new Item[] { new ConjuredItem(0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(46, app.items[0].quality);
    }
}
