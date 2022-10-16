package Models.Address;

import lombok.Getter;

@Getter
public class Address {

    String address;
    String zipCode;
    String city;

    public static final class AddressBuilder {
        private String address;
        private String zipCode;
        private String city;

        AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AddressBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.city = this.city;
            address.zipCode = this.zipCode;
            address.address = this.address;
            return address;
        }
    }
}
