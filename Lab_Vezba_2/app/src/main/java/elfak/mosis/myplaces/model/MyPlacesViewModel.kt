package elfak.mosis.myplaces.model

import androidx.lifecycle.ViewModel
import elfak.mosis.myplaces.data.MyPlace

class MyPlacesViewModel : ViewModel() {
    var myPlacesList = ArrayList<MyPlace>()


    fun addPlace(newPlaceName: String, newPlaceDescription: String) {
        myPlacesList.add(MyPlace(newPlaceName, newPlaceDescription))
    }
}