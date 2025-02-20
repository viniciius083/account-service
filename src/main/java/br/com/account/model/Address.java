package br.com.account.model;

import br.com.account.dto.AddressDTO;
import br.com.account.model.enumeration.State;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String number;
    private String neighborhood;
    private String city;
    @Enumerated(EnumType.STRING)
    private State state;

    private String zipCode;
    private String complement;

    public Address(AddressDTO address) {
        this.setStreet(address.getStreet());
        this.setNumber(address.getNumber());
        this.setNeighborhood(address.getNeighborhood());
        this.setCity(address.getCity());
        this.setState(address.getState());
        this.setZipCode(address.getZipCode());
        this.setComplement(address.getComplement());
    }
}
