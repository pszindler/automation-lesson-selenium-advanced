package Models.Address;

public class AddressFactory {

    public static Address generateJohnAddress() {
        return new Address.AddressBuilder()
                .withAddress("Orzechowska 31")
                .withCity("Katowice")
                .withZipCode("44-127").build();
    }

}
