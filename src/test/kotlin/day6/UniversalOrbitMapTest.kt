package day6

import org.junit.Assert.assertEquals
import org.junit.Test

class UniversalOrbitMapTest {
    @Test
    fun `Universal Orbit Map Test 1`() {
        val orbitPairs = mutableListOf<OrbitPair>()

        orbitPairs.add(OrbitPair("B", "COM"))
        orbitPairs.add(OrbitPair("C", "B"))
        orbitPairs.add(OrbitPair("D", "C"))
        orbitPairs.add(OrbitPair("E", "D"))
        orbitPairs.add(OrbitPair("F", "E"))
        orbitPairs.add(OrbitPair("G", "B"))
        orbitPairs.add(OrbitPair("H", "G"))
        orbitPairs.add(OrbitPair("I", "D"))
        orbitPairs.add(OrbitPair("J", "E"))
        orbitPairs.add(OrbitPair("K", "J"))
        orbitPairs.add(OrbitPair("L", "K"))

        val result = UniversalOrbitMap().doIt(orbitPairs)

        assertEquals(42, result)
    }

    @Test
    fun `Universal Orbit Smaller Data Set`() {
        val orbitPairs = mutableListOf<OrbitPair>()

        orbitPairs.add(OrbitPair("B", "COM"))
        orbitPairs.add(OrbitPair("C", "B"))
        orbitPairs.add(OrbitPair("D", "C"))

        val result = UniversalOrbitMap().doIt(orbitPairs)

        assertEquals(6, result)
    }
}