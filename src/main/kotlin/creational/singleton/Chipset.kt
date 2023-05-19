package creational.singleton

// Singleton is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.
// Case: Only one chipset in motherboard can exist, other part of motherboard (ex: PCI-e, SATA, USB, Audio Card, etc) communicate with CPU by chipset lane.

class Chipset private constructor() {

    companion object {
        private var ins: Chipset? = null
        fun instance(): Chipset? {
            if (ins == null) {
                ins = Chipset()
            }
            return ins
        }
    }
}

class PCI(private val busLane: Chipset, private val bandwidth: Float) {
    fun interact() {
        println("PCI-e lane interact with CPU via $busLane with speed $bandwidth/s")
    }
}

class USB(private val busLane: Chipset, private val bandwidth: Float) {
    fun connect() {
        println("USB lane handle data transfer from external flash drive with CPU via $busLane with speed $bandwidth/s")
    }
}

fun main() {
    // Take a look, other part on motherboard use the same chipset to communicate with CPU, and the chipset can be accessed from everywhere
    // because it gives the global access to it's instance
    Chipset?.instance()?.let { PCI(it, 3200000f).interact() }
    Chipset?.instance()?.let { USB(it, 600000f).connect() }
    Chipset?.instance()?.let { USB(it, 600000f).connect() }
}