package com.ritika.pokemonand

class pokemon
{
    var name:String?=null
    var des:Int?=null
    var image:Int?=null
    var power:Double?=null
    var lat:Double?=null
    var long:Double?=null
    var IsCatch:Boolean?=false
    constructor(name:String,des:Int,image:Int, power:Double, lat:Double, long:Double  )
    {
        this.des=des
        this.image=image
        this.lat=lat
        this.long=long
        this.power=power
        this.name=name
        this.IsCatch=false

    }
}