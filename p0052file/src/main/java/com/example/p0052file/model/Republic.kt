package com.example.p0052file.model;

import kotlinx.serialization.Serializable;

@Serializable
class Republic : Country {
    var republicType: RepublicType = RepublicType.Presidential
        get() = field;
        set(value) {
            field = value;
        }

    constructor(): super() {}

    constructor(name: String, capital: String, type: RepublicType): super(name, capital) {
        this.republicType = type;
    }
    override fun toString(): String {
        return this.name + "\t" + this.capital + "\nType:\t" + this.republicType.toString();
    }
}