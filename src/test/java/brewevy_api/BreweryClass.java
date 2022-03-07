package brewevy_api;

import io.restassured.response.Response;
import pojo.BranchBreweryModel;
import pojo.BreweryModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class BreweryClass {


    private Response getBreweryList() {
        return given().
                queryParam("query", "lagunitas").
                when().
                get("https://api.openbrewerydb.org/breweries/autocomplete");
    }

    public List<BreweryModel> fetchBreweryList() {
        return Arrays.asList(
                getBreweryList().
                        getBody().
                        as(BreweryModel[].class));
    }

    public void getIdFromABreweryList(List<BreweryModel> breweryModels, List<String> breweriesId) {
        breweryModels.forEach(model -> breweriesId.add(model.getId()));
    }

    private List<BranchBreweryModel> getListBreweryModel(String url, String id){
       return Arrays.asList(
                given().
                        queryParam("id", id).
                        when().
                        get(url).
                        getBody().
                        as(BranchBreweryModel[].class)
        );
    }
    private void addBranchBrewery(BranchBreweryModel branchBreweryModel, List<BranchBreweryModel> branchBreweryModelTotal){
        if (Objects.equals(branchBreweryModel.state, "California")){
            branchBreweryModelTotal.add(branchBreweryModel);
        }
    }

    public List<BranchBreweryModel>  getBreweryListByIdList(List<String> breweryId, String url){
        List<BranchBreweryModel> branchBreweryModelTotal = new ArrayList<>();
        for (String id : breweryId) {
            List<BranchBreweryModel>  breweryIdList;
            breweryIdList =  getListBreweryModel(url,id);
            for(BranchBreweryModel branchBreweryModel: breweryIdList){
                addBranchBrewery(branchBreweryModel, branchBreweryModelTotal);
           }
        }
        return branchBreweryModelTotal;
    }

    public List<BreweryModel> getBreweryListByName(List<BreweryModel> breweryList, String name) {
        return breweryList.
                stream().
                filter(brewery -> brewery.getName().
                        equals(name)).collect(Collectors.toList());
    }

    public void printTotalValues(List<BranchBreweryModel> breweryModels){
      breweryModels.forEach(brewery -> System.out.println(brewery.state));
    }
}
