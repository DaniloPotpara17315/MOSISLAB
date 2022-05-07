package elfak.mosis.myplaces

class MyPlace(name:String,description:String) {
    private var name:String = name
    private var description:String = description

    constructor(name:String):this(name,"")  {

    }

    override fun toString(): String {
        return this.name
    }
    fun getName():String{
        return this.name
    }
    fun getDesc():String{
        return this.description
    }
    fun setName(name:String){
        this.name = name
    }
    fun setDesc(desc:String){
        this.description = desc
    }
}