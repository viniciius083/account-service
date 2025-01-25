package br.com.account.dto;

import br.com.account.model.Address;
import br.com.account.model.enumeration.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {

    private String street;
    private String number;
    private String neighborhood;
    private String city;

    @Enumerated(EnumType.STRING)
    private State state;

    private String zipCode;
    private String complement;

    public AddressDTO(Address address) {
        this.setStreet(address.getStreet());
        this.setNumber(address.getNumber());
        this.setNeighborhood(address.getNeighborhood());
        this.setCity(address.getCity());
        this.setState(address.getState());
        this.setZipCode(address.getZipCode());
        this.setComplement(address.getComplement());
    }
}
