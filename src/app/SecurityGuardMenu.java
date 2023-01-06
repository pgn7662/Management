package app;

import constants.ParcelStatus;
import people.ApartmentComplex;
import people.Parcel;
import people.SecurityGuard;

import java.util.ArrayList;

public class SecurityGuardMenu {
    private Input input = new Input();
    public void displaySecurityGuardMenu(SecurityGuard securityGuard, App app, ApartmentComplex apartmentComplex){
        System.out.println("Enter 1 to view parcels to be received today");
        System.out.println("Enter 2 to view all parcels");
        System.out.println("Enter 3 to notify users who received parcel");
        System.out.println("Enter 4 to update status of the collected parcel");
        System.out.println("Enter 5 to logout");
        loop: while (true){
            switch (input.getInteger()){
                case 1 ->{
                    ArrayList<Parcel> parcelList = new ArrayList<>();
                    for(Parcel parcelToBeChecked:securityGuard.getParcelList(apartmentComplex)){
                        if(true)
                            parcelList.add(parcelToBeChecked);
                    }
                    updateParcelStatus(getParcelFromList(parcelList),securityGuard,app);

                }

                case 2 -> {
                    Parcel selectedParcel = getParcelFromList(securityGuard.getParcelList(apartmentComplex));
                    updateParcelStatus(selectedParcel,securityGuard,app);
                }
                case 3 ->{
                    Parcel receivedParcel;
                    System.out.println("Choose the parcel which has been received:");
                    receivedParcel = getParcelFromList(securityGuard.getParcelList(apartmentComplex));
                    if(receivedParcel.getParcelStatus().equals(ParcelStatus.YET_TO_RECEIVE)){
                        updateParcelStatus(receivedParcel,securityGuard,app);
                    }
                }
                case 4 ->{
                    Parcel collectedParcel;
                    System.out.println("Choose the parcel that has been collected by the user");
                    collectedParcel = getParcelFromList(getReceivedParcelList(securityGuard.getParcelList(apartmentComplex)));
                    if(collectedParcel.getParcelStatus().equals(ParcelStatus.RECEIVED))
                        updateParcelStatus(collectedParcel,securityGuard,app);
                }
            }

        }
    }

    private Parcel getParcelFromList(ArrayList<Parcel> parcelList) {
        Parcel selectedParcel = null;
        if(parcelList.size() == 0)
            return null;
        else {
            loop: for(int counter = 0; counter < parcelList.size(); counter++){
                Parcel parcel = parcelList.get(counter);
                System.out.println((counter+1)+"| "+parcel);
                if(((counter+1)%10)==0 || counter == parcelList.size()-1){
                    System.out.println("Enter 1 to go to next page");
                    System.out.println("Enter 2 to go to previous page");
                    System.out.println("Enter 3 to select from above list");
                    while (true){
                        switch (input.getInteger()) {
                            case 1 -> {
                                if (counter == parcelList.size() - 1)
                                    System.out.println("This is the last page");
                                else
                                    continue loop;

                            }
                            case 2 -> {
                                if (counter < 10)
                                    System.out.println("This is the first page");
                                else if (counter == parcelList.size() - 1) {
                                    counter -= ((parcelList.size() - 1) + 10);
                                    continue loop;
                                }
                                else {
                                    counter -= 20;
                                    continue loop;
                                }
                            }
                            case 3 -> {
                                break loop;
                            }
                            default -> System.out.print("Enter a valid choice:");
                        }
                    }
                }
            }
            System.out.print("Enter the serial number to select the parcel from the above list:");
            int index = input.getInteger();
            if(parcelList.size()<=index-1)
                selectedParcel = parcelList.get(index);
            return selectedParcel;
        }
    }

    private ArrayList<Parcel> getReceivedParcelList(ArrayList<Parcel> parcelList){
        ArrayList<Parcel> receivedParcelList = new ArrayList<>();
        if(parcelList.size() != 0){
            for (Parcel parcelToBeChecked : parcelList) {
                if (parcelToBeChecked.getParcelStatus().equals(ParcelStatus.RECEIVED))
                    receivedParcelList.add(parcelToBeChecked);
            }
        }
        return receivedParcelList;
    }

    private void updateParcelStatus(Parcel parcelToBeUpdated,SecurityGuard securityGuard,App app){
        while (true) {
            System.out.println("Enter 1 to update the selected parcel");
            System.out.println("Enter any other number to go back");
            if (input.getInteger() == 1){
                if (parcelToBeUpdated.getParcelStatus().equals(ParcelStatus.RECEIVED)) {
                    app.changeParcelStatus(parcelToBeUpdated,securityGuard,ParcelStatus.COLLECTED_BY_USER);
                    System.out.println("ParcelStatus has been set as COLLECTED");
                }
                else if (parcelToBeUpdated.getParcelStatus().equals(ParcelStatus.YET_TO_RECEIVE)) {
                    app.changeParcelStatus(parcelToBeUpdated,securityGuard,ParcelStatus.RECEIVED);
                    System.out.println("ParcelStatus has been set as RECEIVED");
                }
                else
                    System.out.println("THE PARCEL IS ALREADY COLLECTED BY THE USER");
            }
            else
                break;
        }
    }
}