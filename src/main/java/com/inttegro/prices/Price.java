package com.inttegro.prices;

import com.inttegro.money.Currency;

/** An inline price returned by the API. Its amount fields are embedded on the wire. */
public class Price {
    public Currency currency;
    public Long value;
}
