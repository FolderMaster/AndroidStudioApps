package com.example.p0052file.model

import kotlinx.serialization.Serializable;

@Serializable
class Monarchy: Country {
    var monarchyType: MonarchyType = MonarchyType.Absolute
        get() = field;
        set(value) {
            field = value;
        }

    constructor(): super() {}

    constructor(name: String, capital: String, type: MonarchyType): super(name, capital) {
        this.monarchyType = type;
    }

    override fun toString(): String {
        return this.name + "\t" + this.capital + "\nType:\t" + this.monarchyType.toString();
    }
}