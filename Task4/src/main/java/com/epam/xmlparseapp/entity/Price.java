package com.epam.xmlparseapp.entity;

public class Price {
    
    private String currency;
    private float value;
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public float getValue() {
        return value;
    }
    
    public void setValue(float value) {
        this.value = value;
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((currency == null) ? 0 : currency.hashCode());
	result = prime * result + Float.floatToIntBits(value);
	return result;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Price other = (Price) obj;
	if (currency == null) {
	    if (other.currency != null)
		return false;
	} else if (!currency.equals(other.currency))
	    return false;
	if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + "@"
		+ (currency != null ? "currency=" + currency + ", " : "")
		+ "value=" + value;
    }
    
}
