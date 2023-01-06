package people;

import constants.ParcelStatus;

import java.util.ArrayList;

public class SecurityGuard extends Employee {

    public SecurityGuard(String name, String userName, String password, String id, int age, long phoneNumber, String location) {
        super(name, userName, password, id, age, phoneNumber, location);
    }

    public ArrayList<Parcel> getParcelList(ApartmentComplex apartmentComplex) {
        return apartmentComplex.getParcelList();
    }

    public void updateParcelStatus(Parcel parcelToBeUpdated, ParcelStatus parcelStatus) {
        parcelToBeUpdated.setParcelStatus(parcelStatus,this);
    }

}
