package org.bougainvilleas.ilg.designpattern

class Boeing797 {
    def wingspan = '80.8 m'
    def capacity = 1000
    def speed = '1046 km/h'
    def range = '14400 km'
}

class Airbus380 {
    def wingspan = '79.8 m'
    def capacity = 555
    def speed = '912 km/h'
    def range = '10370 km'
}

class FlyweightFactory {
    static instances = [797: new Boeing797(), 380: new Airbus380()]
}

class Aircraft {
    private type
    private assetNumber
    private bought

    Aircraft(typeCode, assetNumber, bought) {
        type = FlyweightFactory.instances[typeCode]
        this.assetNumber = assetNumber
        this.bought = bought
    }

    def describe() {
        println """
        Asset Number: $assetNumber
        Capacity: $type.capacity people
        Speed: $type.speed
        Range: $type.range
        Bought: $bought
        Wingspan: $type.wingspan
        """
    }
}

def fleet = [
        new Aircraft(380, 1001, '10-May-2007'),
        new Aircraft(380, 1002, '10-Nov-2007'),
        new Aircraft(797, 1003, '10-May-2007'),
        new Aircraft(797, 1004, '10-Nov-2007'),
]
fleet.each { p -> p.describe() }