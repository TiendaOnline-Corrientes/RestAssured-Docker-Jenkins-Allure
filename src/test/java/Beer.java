import brewevy_api.BreweryClass;
import org.testng.annotations.Test;
import pojo.BranchBreweryModel;
import pojo.BreweryModel;

import java.util.ArrayList;
import java.util.List;

public class Beer {
    BreweryClass breweryClass = new BreweryClass();
    String urlBranchBreweries= "https://api.openbrewerydb.org/breweries/";
    String firstQuery= "Lagunitas Brewing Co";
    @Test
    public void testExercise(){
        // task 1 and 2
        List<BreweryModel> breweryClassList =breweryClass.getBreweryListByName(breweryClass.fetchBreweryList(),firstQuery);

        //task 3
        List<String> id= new ArrayList<>();
        breweryClass.getIdFromABreweryList(breweryClassList,id);
        breweryClass.printTotalValues(breweryClass.getBreweryListByIdList(id,urlBranchBreweries));
    }
}
