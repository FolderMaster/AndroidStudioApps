package com.example.p0031classes.model

class Monarchy: Country {
    var type: MonarchyType = MonarchyType.Absolute
        get() = field;
        set(value) {
            field = value;
        }

    constructor(): super() {}

    constructor(name: String, capital: String, type: MonarchyType): super(name, capital) {
        this.type = type;
    }

    override fun toString(): String {
        return this.name + "\t" + this.capital + "\nType:\t" + this.type.toString();
    }
}