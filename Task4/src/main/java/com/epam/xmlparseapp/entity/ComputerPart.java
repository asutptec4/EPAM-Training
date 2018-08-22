package com.epam.xmlparseapp.entity;

public class ComputerPart extends Device {

    private String critical;
    private Specification specification;

    public String getCritical() {
	return critical;
    }

    public void setCritical(String critical) {
	this.critical = critical;
    }

    public Specification getSpecification() {
	return specification;
    }

    public void setSpecification(Specification specification) {
	this.specification = specification;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((critical == null) ? 0 : critical.hashCode());
	result = prime * result
		+ ((specification == null) ? 0 : specification.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ComputerPart other = (ComputerPart) obj;
	if (critical == null) {
	    if (other.critical != null)
		return false;
	} else if (!critical.equals(other.critical))
	    return false;
	if (specification == null) {
	    if (other.specification != null)
		return false;
	} else if (!specification.equals(other.specification))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return super.toString()
		+ (critical != null ? "critical=" + critical + ", " : "")
		+ (specification != null ? "specification=" + specification
			: "")
		+ "]";
    }

}
