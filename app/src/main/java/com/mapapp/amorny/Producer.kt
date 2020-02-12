package com.mapapp.amorny

class Producer {
    var id: String?  = null
    var producer:String?  = null
    var weight:String?  = null
    var measuringUnit:String?  = null
    constructor(){

    }
//    constructor(id: String?, producer: String, weight: String, unit: String) {
//
//    }

    constructor(id: String?, producer:String, weight:String, measuringUnit:String)  {

        this.id = id
        this.producer = producer
        this.weight = weight
        this.measuringUnit = measuringUnit


   }

}