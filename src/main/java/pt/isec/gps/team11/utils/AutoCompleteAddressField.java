package pt.isec.gps.team11.utils;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.QueryAutocompleteRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceDetails;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;

public class AutoCompleteAddressField extends AutoCompleteTextField
{

    private static final String API_KEY = "AIzaSyDP1feUTnivFyTntFVr722y7SwOxsit7VQ";
    AddressPrediction o1;
    AddressPrediction o2;
    public AutoCompleteAddressField(SortedSet entrySet) {
        super(entrySet);
    }

    public AutoCompleteAddressField()
    {

        super(new TreeSet<>((o1, o2) -> o1.toString().compareTo(o2.toString())));


        textProperty().addListener((ObservableValue<? extends String> o, String oldValue, String newValue) ->
        {
            if (newValue != null && !newValue.isEmpty())
            {
                new Thread(() ->
                {
                    AutocompletePrediction[] predictions = getPredictions(getText());

                    Platform.runLater(() ->
                    {
                        getEntries().clear();
                        for (AutocompletePrediction prediction : predictions)
                        {
                            getEntries().add(new AddressPrediction(prediction));
                        }
                    });
                }).start();

            }
        });
    }

    public class AddressPrediction
    {

        private final AutocompletePrediction prediction;

        public AddressPrediction(AutocompletePrediction prediction) {
            this.prediction = prediction;
        }

        @Override
        public String toString()
        {
            return prediction.description;
        }

        protected AutocompletePrediction getPrediction()
        {
            return this.prediction;
        }

    }

    public static PlaceDetails getPlace(AddressPrediction prediction)
    {
        if (prediction != null && prediction.getPrediction() != null && !prediction.getPrediction().placeId.isEmpty())
        {
            PlaceDetailsRequest query = PlacesApi.placeDetails(new GeoApiContext.Builder().apiKey(API_KEY).build(), prediction.getPrediction().placeId);
            return query.awaitIgnoreError();
        }
        return null;
    }

    public static AutocompletePrediction[] getPredictions(String userInput)
    {

        QueryAutocompleteRequest query = PlacesApi.queryAutocomplete(new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build(), userInput);
        try
        {
            return query.await();
        } catch (ApiException | InterruptedException | IOException ex)
        {
        }
        return new AutocompletePrediction[0];

    }

    public static String getComponentLongName(AddressComponent[] components, AddressComponentType type)
    {

        for (AddressComponent component : components)
        {
            for (AddressComponentType types : component.types)
            {
                if (types.equals(type))
                {
                    return component.longName;
                }
            }

        }
        return "";
    }
}