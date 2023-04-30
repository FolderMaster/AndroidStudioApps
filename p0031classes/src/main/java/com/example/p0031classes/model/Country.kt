package com.example.p0031classes.model;

open class Country {
    var name: String = ""
        get() = field;
        set(value) {
            field = value;
        }

    var capital: String = ""
        get() = field;
        set(value) {
            field = value;
        }

    constructor() {}

    constructor(name: String, capital: String) {
        this.name = name;
        this.capital = capital;
    }

    override fun toString(): String {
        return this.name + "\t" + this.capital;
    }
}