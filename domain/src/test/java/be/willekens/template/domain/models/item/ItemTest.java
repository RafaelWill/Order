package be.willekens.template.domain.models.item;

import be.willekens.template.infrastructure.exceptions.InvalidItemNameException;
import be.willekens.template.infrastructure.exceptions.InvalidPriceException;
import be.willekens.template.infrastructure.exceptions.InvalidStockValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void itemWithInvalidNameShouldThrowException() {
        assertThrows(InvalidItemNameException.class, () -> new Item("","test",10.0,1));
    }

    @Test
    void itemWithInvalidPriceShouldThrowException() {
        assertThrows(InvalidPriceException.class, () -> new Item("test","test",0,1));
    }

    @Test
    void itemWithInvalidStockValueShouldThrowException() {
        assertThrows(InvalidStockValue.class, () -> new Item("test","test",10.0,-1));
    }
}