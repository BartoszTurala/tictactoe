package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlayerTest {

    @Test
    void getMarker_returnsConstructorValueX() {
        Player player = new Player('X');
        assertEquals('X', player.getMarker());
    }

    @Test
    void getMarker_returnsConstructorValueO() {
        Player player = new Player('O');
        assertEquals('O', player.getMarker());
    }

    @Test
    void getMarker_doesNotReturnDifferentMarker() {
        Player player = new Player('X');
        assertNotEquals('O', player.getMarker());
    }

    @Test
    void getMarker_supportsArbitraryCharacter() {
        Player player = new Player('A');
        assertEquals('A', player.getMarker());
    }
}