package elfak.mosis.myplaces

object MyPlacesData {
    private var myPlaces:ArrayList<MyPlace> = ArrayList<MyPlace>()

    init {

    }

    fun getMyPlaces():ArrayList<MyPlace>{
        return this.myPlaces
    }
    fun addNewPlace(newPlace:MyPlace){
        this.myPlaces.add(newPlace)
    }
    fun getPlace(index:Int):MyPlace{
        return this.myPlaces.get(index)
    }
    fun deletePlace(index:Int){
        this.myPlaces.removeAt(index)
    }
}