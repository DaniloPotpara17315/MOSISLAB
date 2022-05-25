package elfak.mosis.myplaces.model

import androidx.lifecycle.ViewModel
import elfak.mosis.myplaces.data.MyPlace

class MyPlacesViewModel : ViewModel() {
    var myPlacesList = ArrayList<MyPlace>()
    var selected : MyPlace? = null

    fun addPlace(newPlaceName: String, newPlaceDescription: String) {
        myPlacesList.add(MyPlace(newPlaceName, newPlaceDescription))
    }
}