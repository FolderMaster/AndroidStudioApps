package com.example.p0031classes.model

class Republic : Country {
    var type: RepublicType = RepublicType.Presidential
        get() = field;
        set(value) {
            field = value;
        }

    constructor(): super() {}

    constructor(name: String, capital: String, type: RepublicType): super(name, capital) {
        this.type = type;
    }
    override fun toString(): String {
        return this.name + "\t" + this.capital + "\nType:\t" + this.type.toString();
    }
}